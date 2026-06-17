package net.cathienova.haventrowel.datagen;

import net.cathienova.haventrowel.datagen.recipes.ModRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class DataGenerators
{
    public static void gatherData(GatherDataEvent.Client event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new ModEngLangProvider(output));
        event.addProvider(new ModItemModelProvider(output));
        event.addProvider(new ModRecipeProvider.Runner(output, lookupProvider));
        event.addProvider(new ModAdvancementProvider(output, lookupProvider));
    }
}