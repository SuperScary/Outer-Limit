package superscary.outerlimit.worldgen.biomes;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import superscary.outerlimit.OL;
import superscary.outerlimit.api.world.biomes.BiomesList;

public class ModBiomes
{

    public static final ResourceKey<Biome> LUNAR_SURFACE = register("lunar_surface");

    public static void bootstrap (BootstrapContext<Biome> context)
    {
        context.register(LUNAR_SURFACE, BiomesList.lunarBiome(context));
    }

    private static ResourceKey<Biome> register(String pKey)
    {
        return ResourceKey.create(Registries.BIOME, OL.getResource(pKey));
    }

}
