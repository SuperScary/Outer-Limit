package superscary.outerlimit.worldgen.dimension;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import superscary.outerlimit.api.dimensions.DimensionBuilder;
import superscary.outerlimit.api.dimensions.MoonDimension;

public class ModDimensions
{

    public static final MoonDimension MOON_DIMENSION = new MoonDimension();

    public static void bootstrapType (BootstrapContext<DimensionType> context)
    {
        for (DimensionBuilder builder : DimensionBuilder.BUILDER)
        {
            builder.bootstrapType(context);
        }
    }

    public static void bootstrapStem (BootstrapContext<LevelStem> context)
    {
        for (DimensionBuilder builder : DimensionBuilder.BUILDER)
        {
            builder.bootstrapStem(context);
        }
    }

}
