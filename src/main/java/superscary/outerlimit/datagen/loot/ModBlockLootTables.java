package superscary.outerlimit.datagen.loot;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;
import superscary.outerlimit.block.ModBlocks;
import superscary.outerlimit.api.data.IOuterLimitDataProvider;
import superscary.outerlimit.item.ModItems;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static superscary.outerlimit.OL.MODID;

public class ModBlockLootTables extends BlockLootSubProvider implements IOuterLimitDataProvider
{

    private final Map<Block, Function<Block, LootTable.Builder>> overrides = createOverrides();
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final Path outputFolder;

    public ModBlockLootTables (PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        this.outputFolder = packOutput.getOutputFolder();
        this.registries = registries;
    }

    @NotNull
    private ImmutableMap<Block, Function<Block, LootTable.Builder>> createOverrides ()
    {
        return ImmutableMap.<Block, Function<Block, LootTable.Builder>>builder()
                .put(ModBlocks.MOON_DUST_BLOCK.get(), moonDustBlock())
                .put(ModBlocks.MAGNESIUM_ORE.get(), oreBlock(ModBlocks.MAGNESIUM_MOON_ORE.get(), ModItems.MAGNESIUM_RAW.get()))
                .put(ModBlocks.MAGNESIUM_NETHER_ORE.get(), oreBlock(ModBlocks.MAGNESIUM_NETHER_ORE.get(), ModItems.MAGNESIUM_RAW.get()))
                .put(ModBlocks.MAGNESIUM_DEEPSLATE_ORE.get(), oreBlock(ModBlocks.MAGNESIUM_DEEPSLATE_ORE.get(), ModItems.MAGNESIUM_RAW.get()))
                .put(ModBlocks.MAGNESIUM_MOON_ORE.get(), oreBlock(ModBlocks.MAGNESIUM_MOON_ORE.get(), ModItems.MAGNESIUM_RAW.get()))
                .build();
    }

    @Override
    public void generate ()
    {
    }

    private Function<Block, LootTable.Builder> oreBlock (Block block, Item itemDropped)
    {
        return b -> createSilkTouchDispatchTable(block,
                LootItem.lootTableItem(itemDropped)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.FORTUNE))
                        .apply(ApplyExplosionDecay.explosionDecay()));
    }

    private Function<Block, LootTable.Builder> moonDustBlock ()
    {
        return b -> createSilkTouchDispatchTable(ModBlocks.MOON_DUST_BLOCK.get(),
                LootItem.lootTableItem(ModItems.MOON_DUST.get())
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.FORTUNE))
                        .apply(ApplyExplosionDecay.explosionDecay()));
    }

    @Override
    public @NotNull CompletableFuture<?> run (@NotNull CachedOutput pOutput)
    {
        return this.registries.thenCompose(registries -> run(pOutput, registries));
    }

    public CompletableFuture<?> run (CachedOutput cache, HolderLookup.Provider registries)
    {
        var futures = new ArrayList<CompletableFuture<?>>();

        for (var entry : BuiltInRegistries.BLOCK.entrySet())
        {
            LootTable.Builder builder;
            if (entry.getKey().location().getNamespace().equals(MODID))
            {
                builder = overrides.getOrDefault(entry.getValue(), this::defaultBuilder).apply(entry.getValue());
                futures.add(DataProvider.saveStable(cache, registries, LootTable.CODEC, finishedBuilding(builder), getPath(outputFolder, entry.getKey().location())));
            }
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private LootTable.Builder defaultBuilder (Block block)
    {
        LootPoolEntryContainer.Builder<?> entry = LootItem.lootTableItem(block);
        LootPool.Builder pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(entry)
                .when(ExplosionCondition.survivesExplosion());

        return LootTable.lootTable().withPool(pool);
    }

    private Path getPath (Path root, ResourceLocation id)
    {
        return root.resolve("data/" + id.getNamespace() + "/loot_tables/blocks/" + id.getPath() + ".json");
    }

    public Holder<LootTable> finishedBuilding (LootTable.Builder builder)
    {
        return Holder.direct(builder.setParamSet(LootContextParamSets.BLOCK).build());
    }

    @Override
    public @NotNull String getName ()
    {
        return "Outer Limit Block Drops";
    }
}
