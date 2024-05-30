package superscary.outerlimit.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import superscary.outerlimit.OuterLimitMod;
import superscary.outerlimit.block.ModBlocks;
import superscary.outerlimit.item.ModItems;

import static superscary.outerlimit.OuterLimitMod.MODID;

public class ModItemModelProvider extends ItemModelProvider
{

    public ModItemModelProvider (PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels ()
    {
        simpleItem(ModItems.MOON_DUST);
        simpleBlockItem(ModBlocks.MOON_DUST_BLOCK);

        simpleItem(ModItems.MAGNESIUM_RAW);
        simpleItem(ModItems.MAGNESIUM_INGOT);
        simpleItem(ModItems.MAGNESIUM_NUGGET);
        simpleItem(ModItems.MAGNESIUM_PLATE);
        simpleBlockItem(ModBlocks.MAGNESIUM_MOON_ORE);
        simpleBlockItem(ModBlocks.MAGNESIUM_ORE);
        simpleBlockItem(ModBlocks.MAGNESIUM_NETHER_ORE);
        simpleBlockItem(ModBlocks.MAGNESIUM_DEEPSLATE_ORE);
        simpleBlockItem(ModBlocks.MOON_DUST_BLOCK);
    }

    private ItemModelBuilder simpleItem (DeferredItem<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                OuterLimitMod.getResource("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem (DeferredBlock<Block> item)
    {
        return withExistingParent(Registries.BLOCK.registry().getPath(), OuterLimitMod.getResource("block/" + Registries.BLOCK.registry().getPath()));
    }

}
