package superscary.outerlimit.datagen.world;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import superscary.outerlimit.worldgen.ModBiomeModifier;
import superscary.outerlimit.worldgen.ModConfiguredFeatures;
import superscary.outerlimit.worldgen.ModPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static superscary.outerlimit.OuterLimitMod.MODID;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider
{

    public ModWorldGenProvider (PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(packOutput, registries, new RegistrySetBuilder().add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap).add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap).add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifier::bootstrap), Set.of(MODID));
    }

}
