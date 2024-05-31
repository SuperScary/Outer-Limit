package superscary.outerlimit.datagen.recipe.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import superscary.outerlimit.block.ModBlocks;
import superscary.outerlimit.api.data.IOuterLimitDataProvider;
import superscary.outerlimit.datagen.recipe.ModRecipeProvider;
import superscary.outerlimit.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class FurnaceRecipeProvider extends ModRecipeProvider implements IOuterLimitDataProvider
{

    private final PackOutput packOutput;
    private final CompletableFuture<HolderLookup.Provider> provider;

    public FurnaceRecipeProvider (PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries)
    {
        super(pOutput, pRegistries);
        this.packOutput = pOutput;
        this.provider = pRegistries;
    }

    public void build (RecipeOutput consumer)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.MAGNESIUM_RAW.get()), RecipeCategory.MISC, ModItems.MAGNESIUM_INGOT, 0.6F, 300)
                .unlockedBy("has_item", has(ModItems.MAGNESIUM_RAW.get()))
                .save(consumer, getModId("raw_magnesium_smelting"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.MAGNESIUM_MOON_ORE.get()), RecipeCategory.MISC, ModItems.MAGNESIUM_INGOT, 1.5F, 300)
                .unlockedBy("has_item", has(ModBlocks.MAGNESIUM_MOON_ORE.get()))
                .save(consumer, getModId("raw_magnesium_from_moon_ore_smelting"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.MAGNESIUM_NETHER_ORE.get()), RecipeCategory.MISC, ModItems.MAGNESIUM_INGOT, 1.5F, 300)
                .unlockedBy("has_item", has(ModBlocks.MAGNESIUM_NETHER_ORE.get()))
                .save(consumer, getModId("raw_magnesium_from_nether_ore_smelting"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.MAGNESIUM_DEEPSLATE_ORE.get()), RecipeCategory.MISC, ModItems.MAGNESIUM_INGOT, 1.5F, 300)
                .unlockedBy("has_item", has(ModBlocks.MAGNESIUM_DEEPSLATE_ORE.get()))
                .save(consumer, getModId("raw_magnesium_from_deepslate_ore_smelting"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.MAGNESIUM_ORE.get()), RecipeCategory.MISC, ModItems.MAGNESIUM_INGOT, 1.5F, 300)
                .unlockedBy("has_item", has(ModBlocks.MAGNESIUM_ORE.get()))
                .save(consumer, getModId("raw_magnesium_from_ore_smelting"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.MAGNESIUM_BLOCK_RAW.get()), RecipeCategory.MISC, ModBlocks.MAGNESIUM_BLOCK, 13.5F, 2700)
                .unlockedBy("has_item", has(ModBlocks.MAGNESIUM_BLOCK_RAW.get()))
                .save(consumer, getModId("magnesium_block_from_raw_block_smelting"));

    }

}
