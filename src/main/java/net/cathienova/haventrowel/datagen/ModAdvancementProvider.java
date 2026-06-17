package net.cathienova.haventrowel.datagen;

import net.cathienova.haventrowel.HavenTrowel;
import net.cathienova.haventrowel.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider
{
    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, List.of(new ModAdvancements()));
    }

    public static class ModAdvancements implements AdvancementSubProvider
    {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer)
        {
            Advancement.Builder.advancement()
                    .display(ModItems.trowel.get(),
                            Component.translatable("advancements.haventrowel.trowel.title"),
                            Component.translatable("advancements.haventrowel.trowel.description"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("trowel", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.trowel.get()))
                    .save(consumer, HavenTrowel.loc("trowel"));
        }
    }
}