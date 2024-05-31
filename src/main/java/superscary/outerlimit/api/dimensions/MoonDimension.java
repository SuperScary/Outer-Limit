package superscary.outerlimit.api.dimensions;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import superscary.outerlimit.worldgen.biomes.ModBiomes;
import superscary.outerlimit.worldgen.noise.ModNoiseGeneratorSettings;

import java.util.OptionalLong;

public class MoonDimension extends DimensionBuilder
{

    public MoonDimension ()
    {
        super("moon");
    }


    @Override
    public void bootstrapType (BootstrapContext<DimensionType> context)
    {
        context.register(getDimensionType(), new DimensionType(
                OptionalLong.empty(),
                false,
                false,
                true,
                false,
                1.0,
                true,
                true,
                -64,
                256,
                256,
                BlockTags.INFINIBURN_END,
                BuiltinDimensionTypes.END_EFFECTS,
                0.5f,
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
        ));
    }

    @Override
    public void bootstrapStem (BootstrapContext<LevelStem> context)
    {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.LUNAR_SURFACE)),
                noiseGenSettings.getOrThrow(ModNoiseGeneratorSettings.LUNAR_SURFACE)
        );

        context.register(getDimensionKey(), new LevelStem(dimTypes.getOrThrow(getDimensionType()), noiseBasedChunkGenerator));
    }


}
