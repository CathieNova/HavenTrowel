package net.cathienova.haventrowel.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.cathienova.haventrowel.HavenTrowel;
import net.cathienova.haventrowel.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class JEIHavenTrowelPlugin implements IModPlugin
{
    @Override
    public ResourceLocation getPluginUid()
    {
        return HavenTrowel.loc("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        Component[] trowelDescription = new Component[] {
                Component.translatable("jei.haventrowel.trowel.desc.line1")
        };

        ItemStack trowel = new ItemStack(ModItems.trowel.get());
        registration.addIngredientInfo(trowel, VanillaTypes.ITEM_STACK, trowelDescription);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {

    }
}
