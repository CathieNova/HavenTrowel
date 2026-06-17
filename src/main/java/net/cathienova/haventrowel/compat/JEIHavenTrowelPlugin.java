package net.cathienova.haventrowel.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.cathienova.haventrowel.HavenTrowel;
import net.cathienova.haventrowel.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

@JeiPlugin
public class JEIHavenTrowelPlugin implements IModPlugin
{
    @Override
    public Identifier getPluginUid()
    {
        return Identifier.fromNamespaceAndPath(HavenTrowel.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        registration.addIngredientInfo(ModItems.trowel.get(),
                Component.translatable("jei.haventrowel.trowel.desc.line"));
    }
}