package superscary.outerlimit.api.world.biomes;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.*;

public class BiomesList
{

    public static Biome lunarBiome (BootstrapContext<Biome> context)
    {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0)
                .temperature(100)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x000000)
                        .waterFogColor(0x000000)
                        .skyColor(0x000000)
                        .grassColorOverride(0x000000)
                        .foliageColorOverride(0x000000)
                        .fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.END).build()).build();

    }

}
