package superscary.outerlimit.api.dimensions;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import superscary.outerlimit.OL;

import java.util.ArrayList;

public abstract class DimensionBuilder
{

    private final String key;
    private final ResourceKey<LevelStem> dimensionKey;
    private final ResourceKey<Level> dimensionLevelKey;
    private final ResourceKey<DimensionType> dimensionType;

    public static ArrayList<DimensionBuilder> BUILDER = new ArrayList<>();

    public DimensionBuilder (String key)
    {
        this.key = key;
        this.dimensionKey = ResourceKey.create(Registries.LEVEL_STEM, OL.getResource(key));
        this.dimensionLevelKey = ResourceKey.create(Registries.DIMENSION, OL.getResource(key));
        this.dimensionType = ResourceKey.create(Registries.DIMENSION_TYPE, OL.getResource(key + "_type"));

        BUILDER.add(this);
    }

    public abstract void bootstrapType (BootstrapContext<DimensionType> context);

    public abstract void bootstrapStem (BootstrapContext<LevelStem> context);

    public String getKey ()
    {
        return key;
    }

    public ResourceKey<LevelStem> getDimensionKey ()
    {
        return dimensionKey;
    }

    public ResourceKey<Level> getDimensionLevelKey ()
    {
        return dimensionLevelKey;
    }

    public ResourceKey<DimensionType> getDimensionType ()
    {
        return dimensionType;
    }
}
