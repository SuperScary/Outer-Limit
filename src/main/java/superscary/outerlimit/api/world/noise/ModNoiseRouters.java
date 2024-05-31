package superscary.outerlimit.api.world.noise;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.stream.Stream;

public class ModNoiseRouters
{

    private static final ResourceKey<DensityFunction> Y = createKey("y");
    private static final ResourceKey<DensityFunction> SHIFT_X = createKey("shift_x");
    private static final ResourceKey<DensityFunction> SHIFT_Z = createKey("shift_z");
    public static final ResourceKey<DensityFunction> CONTINENTS = createKey("overworld/continents");
    public static final ResourceKey<DensityFunction> EROSION = createKey("overworld/erosion");
    public static final ResourceKey<DensityFunction> RIDGES = createKey("overworld/ridges");
    public static final ResourceKey<DensityFunction> FACTOR = createKey("overworld/factor");
    public static final ResourceKey<DensityFunction> DEPTH = createKey("overworld/depth");
    private static final ResourceKey<DensityFunction> SLOPED_CHEESE = createKey("overworld/sloped_cheese");
    public static final ResourceKey<DensityFunction> CONTINENTS_LARGE = createKey("overworld_large_biomes/continents");
    public static final ResourceKey<DensityFunction> EROSION_LARGE = createKey("overworld_large_biomes/erosion");
    private static final ResourceKey<DensityFunction> FACTOR_LARGE = createKey("overworld_large_biomes/factor");
    private static final ResourceKey<DensityFunction> DEPTH_LARGE = createKey("overworld_large_biomes/depth");
    private static final ResourceKey<DensityFunction> SLOPED_CHEESE_LARGE = createKey("overworld_large_biomes/sloped_cheese");
    private static final ResourceKey<DensityFunction> FACTOR_AMPLIFIED = createKey("overworld_amplified/factor");
    private static final ResourceKey<DensityFunction> DEPTH_AMPLIFIED = createKey("overworld_amplified/depth");
    private static final ResourceKey<DensityFunction> SLOPED_CHEESE_AMPLIFIED = createKey("overworld_amplified/sloped_cheese");
    private static final ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION = createKey("overworld/caves/spaghetti_roughness_function");
    private static final ResourceKey<DensityFunction> ENTRANCES = createKey("overworld/caves/entrances");
    private static final ResourceKey<DensityFunction> NOODLE = createKey("overworld/caves/noodle");
    private static final ResourceKey<DensityFunction> PILLARS = createKey("overworld/caves/pillars");
    private static final ResourceKey<DensityFunction> SPAGHETTI_2D = createKey("overworld/caves/spaghetti_2d");

    public static NoiseRouter lunar (HolderGetter<DensityFunction> pDensityFunctions, HolderGetter<NormalNoise.NoiseParameters> pNoiseParameters, boolean pLarge, boolean pAmplified)
    {
        DensityFunction barrierNoise = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.AQUIFER_BARRIER), 0.5);
        DensityFunction fluidLevelFloodednessNoise = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67);
        DensityFunction fluidLevelSpreadNoise = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143);
        DensityFunction lavaNoise = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.AQUIFER_LAVA));
        DensityFunction densityfunction4 = getFunction(pDensityFunctions, SHIFT_X);
        DensityFunction densityfunction5 = getFunction(pDensityFunctions, SHIFT_Z);
        DensityFunction temperature = DensityFunctions.shiftedNoise2d(
                densityfunction4, densityfunction5, 0.25, pNoiseParameters.getOrThrow(pLarge ? Noises.TEMPERATURE_LARGE : Noises.TEMPERATURE)
        );
        DensityFunction vegetation = DensityFunctions.shiftedNoise2d(
                densityfunction4, densityfunction5, 0.25, pNoiseParameters.getOrThrow(pLarge ? Noises.VEGETATION_LARGE : Noises.VEGETATION)
        );
        DensityFunction densityfunction8 = getFunction(pDensityFunctions, pLarge ? FACTOR_LARGE : (pAmplified ? FACTOR_AMPLIFIED : FACTOR));
        DensityFunction depth = getFunction(pDensityFunctions, pLarge ? DEPTH_LARGE : (pAmplified ? DEPTH_AMPLIFIED : DEPTH));
        DensityFunction densityfunction10 = noiseGradientDensity(DensityFunctions.cache2d(densityfunction8), depth);
        DensityFunction densityfunction11 = getFunction(pDensityFunctions, pLarge ? SLOPED_CHEESE_LARGE : (pAmplified ? SLOPED_CHEESE_AMPLIFIED : SLOPED_CHEESE));
        DensityFunction densityfunction12 = DensityFunctions.min(
                densityfunction11, DensityFunctions.mul(DensityFunctions.constant(5.0), getFunction(pDensityFunctions, ENTRANCES))
        );
        DensityFunction densityfunction13 = DensityFunctions.rangeChoice(
                densityfunction11, -1000000.0, 1.5625, densityfunction12, underground(pDensityFunctions, pNoiseParameters, densityfunction11)
        );
        DensityFunction finalDensity = DensityFunctions.min(postProcess(slideOverworld(pAmplified, densityfunction13)), getFunction(pDensityFunctions, NOODLE));
        DensityFunction densityfunction15 = getFunction(pDensityFunctions, Y);
        int i = Stream.of(VeinType.values()).mapToInt(p_224495_ -> p_224495_.minY).min().orElse(-DimensionType.MIN_Y * 2);
        int j = Stream.of(VeinType.values()).mapToInt(p_224457_ -> p_224457_.maxY).max().orElse(-DimensionType.MIN_Y * 2);
        DensityFunction veinToggle = yLimitedInterpolatable(
                densityfunction15, DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.ORE_VEININESS), 1.5, 1.5), i, j, 0
        );
        float f = 4.0F;
        DensityFunction densityfunction17 = yLimitedInterpolatable(
                densityfunction15, DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.ORE_VEIN_A), 4.0, 4.0), i, j, 0
        )
                .abs();
        DensityFunction densityfunction18 = yLimitedInterpolatable(
                densityfunction15, DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.ORE_VEIN_B), 4.0, 4.0), i, j, 0
        )
                .abs();
        DensityFunction veinRidged = DensityFunctions.add(DensityFunctions.constant(-0.08F), DensityFunctions.max(densityfunction17, densityfunction18));
        DensityFunction veinGap = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.ORE_GAP));
        return new NoiseRouter(
                barrierNoise,
                fluidLevelFloodednessNoise,
                fluidLevelSpreadNoise,
                lavaNoise,
                temperature,
                vegetation,
                getFunction(pDensityFunctions, pLarge ? CONTINENTS_LARGE : CONTINENTS), // continents
                getFunction(pDensityFunctions, pLarge ? EROSION_LARGE : EROSION), // erosion
                depth,
                getFunction(pDensityFunctions, RIDGES), // ridges
                slideOverworld(pAmplified, DensityFunctions.add(densityfunction10, DensityFunctions.constant(-0.703125)).clamp(-64.0, 64.0)), // initialDensityWithoutJaggedness
                finalDensity,
                veinToggle,
                veinRidged,
                veinGap
        );
    }

    private static ResourceKey<DensityFunction> createKey (String pLocation)
    {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(pLocation));
    }

    private static DensityFunction getFunction (HolderGetter<DensityFunction> pDensityFunctions, ResourceKey<DensityFunction> pKey)
    {
        return new DensityFunctions.HolderHolder(pDensityFunctions.getOrThrow(pKey));
    }

    private static DensityFunction noiseGradientDensity (DensityFunction pMinFunction, DensityFunction pMaxFunction)
    {
        DensityFunction densityfunction = DensityFunctions.mul(pMaxFunction, pMinFunction);
        return DensityFunctions.mul(DensityFunctions.constant(4.0), densityfunction.quarterNegative());
    }

    private static DensityFunction underground (HolderGetter<DensityFunction> pDensityFunctions, HolderGetter<NormalNoise.NoiseParameters> pNoiseParameters, DensityFunction p_256658_)
    {
        DensityFunction densityfunction = getFunction(pDensityFunctions, SPAGHETTI_2D);
        DensityFunction densityfunction1 = getFunction(pDensityFunctions, SPAGHETTI_ROUGHNESS_FUNCTION);
        DensityFunction densityfunction2 = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.CAVE_LAYER), 8.0);
        DensityFunction densityfunction3 = DensityFunctions.mul(DensityFunctions.constant(4.0), densityfunction2.square());
        DensityFunction densityfunction4 = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.CAVE_CHEESE), 0.6666666666666666);
        DensityFunction densityfunction5 = DensityFunctions.add(
                DensityFunctions.add(DensityFunctions.constant(0.27), densityfunction4).clamp(-1.0, 1.0),
                DensityFunctions.add(DensityFunctions.constant(1.5), DensityFunctions.mul(DensityFunctions.constant(-0.64), p_256658_)).clamp(0.0, 0.5)
        );
        DensityFunction densityfunction6 = DensityFunctions.add(densityfunction3, densityfunction5);
        DensityFunction densityfunction7 = DensityFunctions.min(
                DensityFunctions.min(densityfunction6, getFunction(pDensityFunctions, ENTRANCES)), DensityFunctions.add(densityfunction, densityfunction1)
        );
        DensityFunction densityfunction8 = getFunction(pDensityFunctions, PILLARS);
        DensityFunction densityfunction9 = DensityFunctions.rangeChoice(
                densityfunction8, -1000000.0, 0.03, DensityFunctions.constant(-1000000.0), densityfunction8
        );
        return DensityFunctions.max(densityfunction7, densityfunction9);
    }

    private static DensityFunction postProcess (DensityFunction pDensityFunction)
    {
        DensityFunction densityfunction = DensityFunctions.blendDensity(pDensityFunction);
        return DensityFunctions.mul(DensityFunctions.interpolated(densityfunction), DensityFunctions.constant(0.64)).squeeze();
    }

    private static DensityFunction slideOverworld (boolean pAmplified, DensityFunction pDensityFunction)
    {
        return slide(pDensityFunction, -64, 384, pAmplified ? 16 : 80, pAmplified ? 0 : 64, -0.078125, 0, 24, pAmplified ? 0.4 : 0.1171875);
    }

    private static DensityFunction slide (DensityFunction pDensityFunction, int pMinY, int pMaxY, int p_224447_, int p_224448_, double p_224449_, int p_224450_, int p_224451_, double p_224452_)
    {
        DensityFunction densityfunction1 = DensityFunctions.yClampedGradient(pMinY + pMaxY - p_224447_, pMinY + pMaxY - p_224448_, 1.0, 0.0);
        DensityFunction $$9 = DensityFunctions.lerp(densityfunction1, p_224449_, pDensityFunction);
        DensityFunction densityfunction2 = DensityFunctions.yClampedGradient(pMinY + p_224450_, pMinY + p_224451_, 0.0, 1.0);
        return DensityFunctions.lerp(densityfunction2, p_224452_, $$9);
    }

    private static DensityFunction yLimitedInterpolatable (DensityFunction p_209472_, DensityFunction p_209473_, int p_209474_, int p_209475_, int p_209476_)
    {
        return DensityFunctions.interpolated(
                DensityFunctions.rangeChoice(p_209472_, (double) p_209474_, (double) (p_209475_ + 1), p_209473_, DensityFunctions.constant((double) p_209476_))
        );
    }

    protected static enum VeinType
    {
        COPPER(Blocks.COPPER_ORE.defaultBlockState(), Blocks.RAW_COPPER_BLOCK.defaultBlockState(), Blocks.GRANITE.defaultBlockState(), 0, 50),
        IRON(Blocks.DEEPSLATE_IRON_ORE.defaultBlockState(), Blocks.RAW_IRON_BLOCK.defaultBlockState(), Blocks.TUFF.defaultBlockState(), -60, -8);

        final BlockState ore;
        final BlockState rawOreBlock;
        final BlockState filler;
        protected final int minY;
        protected final int maxY;

        private VeinType (BlockState pOre, BlockState pRawOreBlock, BlockState pFiller, int pMinY, int pMaxY)
        {
            this.ore = pOre;
            this.rawOreBlock = pRawOreBlock;
            this.filler = pFiller;
            this.minY = pMinY;
            this.maxY = pMaxY;
        }
    }

}
