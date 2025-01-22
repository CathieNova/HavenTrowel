package net.cathienova.haventrowel.datagen;

import net.cathienova.haventrowel.HavenTrowel;
import net.cathienova.haventrowel.item.ModCreativeModTabs;
import net.cathienova.haventrowel.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEngLangProvider extends LanguageProvider
{
    public ModEngLangProvider(PackOutput output)
    {
        super(output, HavenTrowel.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        add(ModCreativeModTabs.HavenTrowel_tab_title, "Haven Trowel");

        addItem(ModItems.trowel, "Trowel");
        add("item.haventrowel.trowel.tooltip.hotbar", "§bRight click§r to place random blocks from the hotbar.");
        add("item.haventrowel.trowel.tooltip.inventory", "§bRight click§r to place random blocks from the inventory and hotbar.");

        add("jei.haventrowel.trowel.desc.line1", "Trowels are used to place random blocks from the hotbar. Right click to place a block.");

        add("advancements.haventrowel.trowel.title", "Trowel");
        add("advancements.haventrowel.trowel.description", "A tool for placing random blocks from the hotbar.");
    }
}
