package superscary.outerlimit.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import superscary.outerlimit.OuterLimitMod;
import superscary.outerlimit.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures
{

    protected static ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_MAGNESIUM_ORE = createKey("overworld_magnesium_ore");

    public static void bootstrap (BootstrapContext<ConfiguredFeature<?, ?>> context)
    {
        RuleTest stoneReplacement = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacement = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplacement = new TagMatchTest(BlockTags.NETHER_CARVER_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> magnesiumOre = List.of(
                OreConfiguration.target(stoneReplacement, ModBlocks.MAGNESIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplacement, ModBlocks.MAGNESIUM_DEEPSLATE_ORE.get().defaultBlockState())
        );

        register(context, OVERWORLD_MAGNESIUM_ORE, Feature.ORE, new OreConfiguration(magnesiumOre, 6));

    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey (String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, OuterLimitMod.getResource(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register (BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config)
    {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }

}
