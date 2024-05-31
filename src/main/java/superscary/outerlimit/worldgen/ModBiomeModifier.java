package superscary.outerlimit.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import superscary.outerlimit.OL;

public class ModBiomeModifier
{

    protected static ResourceKey<BiomeModifier> ADD_MAGNESIUM_ORE = registerKey("add_magnesium_ore");
    protected static ResourceKey<BiomeModifier> ADD_MAGNESIUM_ORE_NETHER = registerKey("add_magnesium_ore_nether");

    public static void bootstrap (BootstrapContext<BiomeModifier> context)
    {
        HolderGetter<PlacedFeature> placedFeature = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biome = context.lookup(Registries.BIOME);

        context.register(ADD_MAGNESIUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(biome.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.MAGNESIUM_ORE_OVERWORLD)), GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_MAGNESIUM_ORE_NETHER, new BiomeModifiers.AddFeaturesBiomeModifier(biome.getOrThrow(BiomeTags.IS_NETHER), HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.MAGNESIUM_ORE_NETHER)), GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey (String name)
    {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, OL.getResource(name));
    }

}
