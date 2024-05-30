package superscary.outerlimit.datagen.lang;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import superscary.outerlimit.block.ModBlocks;
import superscary.outerlimit.item.ModItems;

import static superscary.outerlimit.OuterLimitMod.MODID;

public class ModEnLangProvider extends LanguageProvider
{

    public ModEnLangProvider (DataGenerator generator)
    {
        super(generator.getPackOutput(), MODID, "en_us");
    }

    @Override
    protected void addTranslations ()
    {
        // ITEMS
        add(ModItems.MAGNESIUM_INGOT.asItem(), "Magnesium Ingot");
        add(ModItems.MAGNESIUM_RAW.asItem(), "Raw Magnesium");
        add(ModItems.MAGNESIUM_NUGGET.asItem(), "Magnesium Nugget");
        add(ModItems.MAGNESIUM_PLATE.asItem(), "Magnesium Plate");
        add(ModItems.MOON_DUST.asItem(), "Moon Dust");

        // BLOCKS
        add(ModBlocks.MAGNESIUM_MOON_ORE.get(), "Magnesium Moon Ore");
        add(ModBlocks.MAGNESIUM_ORE.get(), "Magnesium Ore");
        add(ModBlocks.MAGNESIUM_NETHER_ORE.get(), "Magnesium Nether Ore");
        add(ModBlocks.MAGNESIUM_DEEPSLATE_ORE.get(), "Magnesium Deepslate Ore");
        add(ModBlocks.MAGNESIUM_BLOCK_RAW.get(), "Block of Raw Magnesium");
        add(ModBlocks.MAGNESIUM_BLOCK.get(), "Magnesium Block");
        add(ModBlocks.MOON_DUST_BLOCK.get(), "Moon Dust Block");

        // Creative Tab
        add("itemGroup.outerlimit", "Outer Limit");

    }

}
