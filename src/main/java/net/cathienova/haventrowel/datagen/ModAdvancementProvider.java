package net.cathienova.haventrowel.datagen;

import net.cathienova.haventrowel.HavenTrowel;
import net.cathienova.haventrowel.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider
{

    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
        super(output, registries, fileHelper, List.of(new ModAdvancements()));
    }

    public static class ModAdvancements implements AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
            AdvancementHolder trowel = Advancement.Builder.advancement()
                    .display(ModItems.trowel.get(),
                            Component.translatable("advancements.haventrowel.trowel.title"),
                            Component.translatable("advancements.haventrowel.trowel.description"),
                            null,
                            AdvancementType.TASK, true, true, false)
                    .addCriterion("trowel", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item().of(ModItems.trowel.get()).build()))
                    .save(consumer, ResourceLocation.fromNamespaceAndPath(HavenTrowel.MOD_ID, "trowel").toString());
        }
    }
}
