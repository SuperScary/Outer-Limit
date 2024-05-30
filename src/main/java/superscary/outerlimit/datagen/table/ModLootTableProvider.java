package superscary.outerlimit.datagen.table;

import com.google.common.collect.Sets;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static superscary.outerlimit.OuterLimitMod.MODID;

public class ModLootTableProvider extends LootTableProvider
{

    public ModLootTableProvider (PackOutput pOutput, Set<ResourceKey<LootTable>> pRequiredTables, List<SubProviderEntry> pSubProviders, CompletableFuture<HolderLookup.Provider> pRegistries)
    {
        super(pOutput, pRequiredTables, pSubProviders, pRegistries);
    }

    public static LootTableProvider create (PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future)
    {
        return new LootTableProvider(packOutput, Set.of(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSet.builder().build())), future);
    }

    @Override
    public void validate (WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector)
    {
        var modLootTablesId = BuiltInLootTables.all()
                .stream()
                .filter(id -> id.registry().getNamespace().equals(MODID))
                .collect(Collectors.toSet());

        for (var id : Sets.difference(modLootTablesId, writableregistry.entrySet()))
        {
            validationcontext.reportProblem("Missing mod loot table: " + id);
        }

        writableregistry.holders().forEach(table -> {
            table.value().validate(validationcontext.setParams(table.value().getParamSet()).enterElement("{" + table.key().location() + "}", table.key()));
        });

    }

}
