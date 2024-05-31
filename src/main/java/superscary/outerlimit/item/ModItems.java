package superscary.outerlimit.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import superscary.outerlimit.OL;
import superscary.outerlimit.ModTabs;
import superscary.outerlimit.api.item.OLItem;

import java.util.function.Supplier;

public class ModItems
{

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(OL.MODID);

    public static final DeferredItem<Item> MAGNESIUM_RAW = reg("magnesium_raw", OLItem::new);
    public static final DeferredItem<Item> MAGNESIUM_INGOT = reg("magnesium_ingot", OLItem::new);
    public static final DeferredItem<Item> MAGNESIUM_NUGGET = reg("magnesium_nugget", OLItem::new);
    public static final DeferredItem<Item> MAGNESIUM_PLATE = reg("magnesium_plate", OLItem::new);

    public static final DeferredItem<Item> MOON_DUST = reg("moon_dust", OLItem::new);

    public static <T extends Item> DeferredItem<T> reg (final String name, final Supplier<? extends T> supplier)
    {
        return ModTabs.addItemToTab(ITEMS.register(name, supplier));
    }

}
