package net.cathienova.haventrowel.datagen;

import net.cathienova.haventrowel.HavenTrowel;
import net.cathienova.haventrowel.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModItemModelProvider extends ModelProvider
{
    public ModItemModelProvider(PackOutput output)
    {
        super(output, HavenTrowel.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels)
    {
        itemModels.generateFlatItem(ModItems.trowel.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    }
}