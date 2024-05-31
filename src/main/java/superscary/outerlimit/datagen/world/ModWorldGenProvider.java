package superscary.outerlimit.datagen.world;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import superscary.outerlimit.api.data.IOuterLimitDataProvider;
import superscary.outerlimit.worldgen.ModBiomeModifier;
import superscary.outerlimit.worldgen.ModConfiguredFeatures;
import superscary.outerlimit.worldgen.ModPlacedFeatures;
import superscary.outerlimit.worldgen.biomes.ModBiomes;
import superscary.outerlimit.worldgen.dimension.ModDimensions;
import superscary.outerlimit.worldgen.noise.ModNoiseGeneratorSettings;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static superscary.outerlimit.OL.MODID;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider implements IOuterLimitDataProvider
{

    public ModWorldGenProvider (PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(packOutput, registries, new RegistrySetBuilder().add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifier::bootstrap)
                .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)
                .add(Registries.NOISE_SETTINGS, ModNoiseGeneratorSettings::bootstrap)
                .add(Registries.BIOME, ModBiomes::bootstrap)
                .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType),
                Set.of(MODID));
    }

}
