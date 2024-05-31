package superscary.outerlimit.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import superscary.outerlimit.datagen.lang.ModEnLangProvider;
import superscary.outerlimit.datagen.loot.ModBlockLootTables;
import superscary.outerlimit.datagen.model.ModItemModelProvider;
import superscary.outerlimit.datagen.recipe.ModRecipeProvider;
import superscary.outerlimit.datagen.state.ModBlockStateProvider;
import superscary.outerlimit.datagen.tags.ModBlockTagGenerator;
import superscary.outerlimit.datagen.tags.ModItemTagGenerator;
import superscary.outerlimit.datagen.world.ModWorldGenProvider;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

import static superscary.outerlimit.OL.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataGenerator
{

    @SubscribeEvent
    public static void gather (GatherDataEvent event)
    {
        var generator = event.getGenerator();
        var registries = event.getLookupProvider();
        var pack = generator.getVanillaPack(true);
        var existingFileHelper = event.getExistingFileHelper();
        var localization = new ModEnLangProvider(generator);

        // WORLD GEN
        pack.addProvider(output -> new ModWorldGenProvider(output, registries));

        // LOOT TABLE
        pack.addProvider(bindRegistries(ModBlockLootTables::new, registries));

        // TAGS
        var blockTagsProvider = pack.addProvider(pOutput -> new ModBlockTagGenerator(pOutput, registries, existingFileHelper));
        pack.addProvider(pOutput -> new ModItemTagGenerator(pOutput, registries, blockTagsProvider.contentsGetter(), existingFileHelper));

        // STATE PROVIDER
        pack.addProvider(pOutput -> new ModBlockStateProvider(pOutput, existingFileHelper));

        // MODELS
        pack.addProvider(pOutput -> new ModItemModelProvider(pOutput, existingFileHelper));

        // RECIPES
        pack.addProvider(pOutput -> new ModRecipeProvider(pOutput, registries));

        // LOCALIZATION MUST RUN LAST
        pack.addProvider(output -> localization);

    }

    private static <T extends DataProvider> DataProvider.Factory<T> bindRegistries (BiFunction<PackOutput, CompletableFuture<HolderLookup.Provider>, T> factory, CompletableFuture<HolderLookup.Provider> factories)
    {
        return pOutput -> factory.apply(pOutput, factories);
    }

}
