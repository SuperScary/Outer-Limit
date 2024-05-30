package superscary.outerlimit.datagen.recipe.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import superscary.outerlimit.block.ModBlocks;
import superscary.outerlimit.datagen.IOuterLimitDataProvider;
import superscary.outerlimit.datagen.recipe.ModRecipeProvider;
import superscary.outerlimit.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class NormalCraftingTableRecipeProvider extends ModRecipeProvider implements IOuterLimitDataProvider
{

    public NormalCraftingTableRecipeProvider (PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries)
    {
        super(pOutput, pRegistries);
    }

    public void build (RecipeOutput consumer)
    {
        buildShapeless(consumer);
        buildShaped(consumer);
    }

    protected void buildShapeless (RecipeOutput consumer)
    {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAGNESIUM_INGOT.get(), 9)
                .requires(ModBlocks.MAGNESIUM_BLOCK.get())
                .unlockedBy("has_item", has(ModBlocks.MAGNESIUM_BLOCK.get()))
                .save(consumer, getModId("magnesium_ingot_from_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAGNESIUM_RAW.get(), 9)
                .requires(ModBlocks.MAGNESIUM_BLOCK.get())
                .unlockedBy("has_item", has(ModBlocks.MAGNESIUM_BLOCK_RAW.get()))
                .save(consumer, getModId("magnesium_raw_from_raw_block"));
    }

    protected void buildShaped (RecipeOutput consumer)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOON_DUST_BLOCK.get(), 1)
                .pattern("AA")
                .pattern("AA")
                .define('A', ModItems.MOON_DUST.get())
                .unlockedBy("has_item", has(ModItems.MOON_DUST.get()))
                .save(consumer, getModId("moon_dust_to_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MAGNESIUM_BLOCK.get(), 1)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.MAGNESIUM_INGOT.get())
                .unlockedBy("has_item", has(ModItems.MAGNESIUM_INGOT.get()))
                .save(consumer, getModId("magnesium_ingot_to_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MAGNESIUM_BLOCK_RAW.get(), 1)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.MAGNESIUM_RAW.get())
                .unlockedBy("has_item", has(ModItems.MAGNESIUM_RAW.get()))
                .save(consumer, getModId("magnesium_block_raw_from_raw_magnesium"));

    }

}
