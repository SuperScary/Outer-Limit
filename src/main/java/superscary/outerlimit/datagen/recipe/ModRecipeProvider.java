package superscary.outerlimit.datagen.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import superscary.outerlimit.OuterLimitMod;
import superscary.outerlimit.datagen.recipe.provider.FurnaceRecipeProvider;
import superscary.outerlimit.datagen.recipe.provider.NormalCraftingTableRecipeProvider;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider
{

    private final PackOutput packOutput;
    private final CompletableFuture<HolderLookup.Provider> provider;

    public ModRecipeProvider (PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries)
    {
        super(pOutput, pRegistries);
        this.packOutput = pOutput;
        this.provider = pRegistries;
    }

    @Override
    protected void buildRecipes (@NotNull RecipeOutput recipeOutput)
    {
        new FurnaceRecipeProvider(getPackOutput(), getProvider()).build(recipeOutput);
        new NormalCraftingTableRecipeProvider(getPackOutput(), getProvider()).build(recipeOutput);
    }

    public ResourceLocation getModId (String path)
    {
        return OuterLimitMod.getResource(path);
    }

    public PackOutput getPackOutput ()
    {
        return packOutput;
    }

    public CompletableFuture<HolderLookup.Provider> getProvider ()
    {
        return provider;
    }

}
