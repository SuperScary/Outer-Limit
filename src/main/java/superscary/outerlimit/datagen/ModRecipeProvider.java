package superscary.outerlimit.datagen;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import superscary.outerlimit.OuterLimitMod;
import superscary.outerlimit.block.ModBlocks;
import superscary.outerlimit.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{

    public ModRecipeProvider (PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries)
    {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes (RecipeOutput recipeOutput)
    {
        addBlockRecipes(recipeOutput);
    }

    protected static void addBlockRecipes (RecipeOutput recipeOutput)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.MOON_DUST_BLOCK.get())
                .pattern("AA")
                .pattern("AA")
                .define('A', ModItems.MOON_DUST.get()).unlockedBy("has_moon_dust", CriteriaTriggers.INVENTORY_CHANGED.createCriterion(null)).save(recipeOutput, OuterLimitMod.getResource("moon_dust_block_from_moon_dust"));
    }

}
