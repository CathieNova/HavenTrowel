package net.cathienova.haventrowel.datagen.recipes;

import net.cathienova.haventrowel.HavenTrowel;
import net.cathienova.haventrowel.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider
{
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output)
    {
        super(registries, output);
    }

    @Override
    protected void buildRecipes()
    {
        TrowelRecipe(ModItems.trowel.get(), Items.IRON_INGOT);
    }

    protected void TrowelRecipe(ItemLike result, ItemLike ingredient)
    {
        shaped(RecipeCategory.MISC, result, 1)
                .pattern(" II")
                .pattern(" SI")
                .define('I', ingredient)
                .define('S', Items.STICK)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(output, ResourceKey.create(Registries.RECIPE, HavenTrowel.loc("craft/" + getItemName(result))));
    }

    public static class Runner extends RecipeProvider.Runner
    {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
        {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output)
        {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName()
        {
            return "Haven Trowel Recipes";
        }
    }
}