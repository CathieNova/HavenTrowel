package net.cathienova.haventrowel.datagen;

import net.cathienova.haventrowel.HavenTrowel;
import net.cathienova.haventrowel.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider
{
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HavenTrowel.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        handHeldItem(ModItems.trowel);
    }


    private ItemModelBuilder handHeldItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(HavenTrowel.MOD_ID,"item/" + item.getId().getPath()));
    }
}
