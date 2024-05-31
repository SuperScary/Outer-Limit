package superscary.outerlimit.datagen.model;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import superscary.outerlimit.OL;
import superscary.outerlimit.api.data.IOuterLimitDataProvider;
import superscary.outerlimit.item.ModItems;

import static superscary.outerlimit.OL.MODID;

public class ModItemModelProvider extends ItemModelProvider implements IOuterLimitDataProvider
{

    public ModItemModelProvider (PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels ()
    {
        simpleItem(ModItems.MOON_DUST);

        simpleItem(ModItems.MAGNESIUM_RAW);
        simpleItem(ModItems.MAGNESIUM_INGOT);
        simpleItem(ModItems.MAGNESIUM_NUGGET);
        simpleItem(ModItems.MAGNESIUM_PLATE);
    }

    private void simpleItem (DeferredItem<Item> item)
    {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                OL.getResource("item/" + item.getId().getPath()));
    }

}
