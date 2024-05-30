package superscary.outerlimit.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import superscary.outerlimit.OuterLimitMod;

import java.util.List;

public class ModPlacedFeatures
{

    protected static ResourceKey<PlacedFeature> MAGNESIUM_ORE = createKey("magnesium_ore");

    public static void bootstrap (BootstrapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> holder = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_MAGNESIUM_ORE);

        register(context, MAGNESIUM_ORE, holder, ModOrePlacement.commonOrePlacements(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(20))));
    }

    private static ResourceKey<PlacedFeature> createKey (String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, OuterLimitMod.getResource(name));
    }

    private static void register (BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placements)
    {
        context.register(key, new PlacedFeature(feature, placements));
    }


}
