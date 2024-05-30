package superscary.outerlimit.datagen.table;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import superscary.outerlimit.block.ModBlocks;
import superscary.outerlimit.item.ModItems;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static superscary.outerlimit.OuterLimitMod.MODID;

public class ModBlockLootTables extends BlockLootSubProvider
{

    public ModBlockLootTables ()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate ()
    {
        this.add(ModBlocks.MAGNESIUM_MOON_ORE.get(), block -> createOre(ModBlocks.MAGNESIUM_MOON_ORE.get(), ModItems.MAGNESIUM_RAW.get()));
        this.add(ModBlocks.MAGNESIUM_ORE.get(), block -> createOre(ModBlocks.MAGNESIUM_ORE.get(), ModItems.MAGNESIUM_RAW.get()));
        this.add(ModBlocks.MAGNESIUM_NETHER_ORE.get(), block -> createOre(ModBlocks.MAGNESIUM_NETHER_ORE.get(), ModItems.MAGNESIUM_RAW.get()));
        this.add(ModBlocks.MAGNESIUM_DEEPSLATE_ORE.get(), block -> createOre(ModBlocks.MAGNESIUM_DEEPSLATE_ORE.get(), ModItems.MAGNESIUM_RAW.get()));
        this.add(ModBlocks.MOON_DUST_BLOCK.get(), block -> createOre(ModBlocks.MOON_DUST_BLOCK.get(), ModItems.MOON_DUST.get()));
        this.dropSelf(ModBlocks.MAGNESIUM_BLOCK.get());
    }

    @Override
    public @NotNull Iterable<Block> getKnownBlocks ()
    {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> Optional.of(BuiltInRegistries.BLOCK.getKey(block))
                        .filter(key -> key.getNamespace().equals(MODID))
                        .isPresent())
                .collect(Collectors.toSet());
    }

    protected LootTable.Builder createOre (Block pBlock, Item item)
    {
        return createSilkTouchDispatchTable(
                pBlock,
                (LootPoolEntryContainer.Builder<?>) this.applyExplosionDecay(
                        pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F)))
                )
        );
    }

}
