package superscary.outerlimit.worldgen.noise;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import superscary.outerlimit.OL;
import superscary.outerlimit.api.world.noise.ModNoiseRouters;
import superscary.outerlimit.block.ModBlocks;

public class ModNoiseGeneratorSettings
{

    public static final ResourceKey<NoiseGeneratorSettings> LUNAR_SURFACE = ResourceKey.create(Registries.NOISE_SETTINGS, OL.getResource("lunar_surface"));

    protected static final NoiseSettings LUNAR_SURFACE_NOISE_SETTINGS = NoiseSettings.create(-64, 384, 1, 2);

    private static final SurfaceRules.RuleSource MOON_DUST = makeStateRule(ModBlocks.MOON_DUST_BLOCK.get());

    public static void bootstrap (BootstrapContext<NoiseGeneratorSettings> pContext)
    {
        pContext.register(LUNAR_SURFACE, lunarSurface(pContext, false, false));
    }

    private static SurfaceRules.RuleSource makeStateRule (Block pBlock)
    {
        return SurfaceRules.state(pBlock.defaultBlockState());
    }

    public static NoiseGeneratorSettings lunarSurface (BootstrapContext<?> pContext, boolean pLarge, boolean pAmplified)
    {
        return new NoiseGeneratorSettings(
                LUNAR_SURFACE_NOISE_SETTINGS,
                ModBlocks.MOON_DUST_BLOCK.get().defaultBlockState(),
                Blocks.AIR.defaultBlockState(),
                // WHAT THE FUCK IS THIS SHIT
                ModNoiseRouters.lunar(pContext.lookup(Registries.DENSITY_FUNCTION), pContext.lookup(Registries.NOISE), pAmplified, pLarge),
                // ok this is some bs but at least it makes sense
                lunar(),
                // nvm fuck u
                new OverworldBiomeBuilder().spawnTarget(),
                63,
                true,
                false,
                true,
                false
        );
    }

    public static SurfaceRules.RuleSource lunar ()
    {
        return MOON_DUST;
    }

}
