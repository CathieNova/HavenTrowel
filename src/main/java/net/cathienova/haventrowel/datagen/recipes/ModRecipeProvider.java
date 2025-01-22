package net.cathienova.haventrowel.datagen.recipes;

import net.cathienova.haventrowel.HavenTrowel;
import net.cathienova.haventrowel.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(pOutput, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        TrowelRecipe(output, ModItems.trowel.get(), Items.IRON_INGOT);
    }

    protected static void TrowelRecipe(RecipeOutput consumer, ItemLike result, ItemLike ingredient)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                .pattern(" II")
                .pattern(" SI")
                .define('I', ingredient)
                .define('S', Items.STICK)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer, HavenTrowel.MOD_ID + ":craft/" + getItemName(result));
    }
}
