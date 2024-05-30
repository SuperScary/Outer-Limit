package superscary.outerlimit.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import superscary.outerlimit.OuterLimitMod;
import superscary.outerlimit.ModTabs;

import java.util.function.Supplier;

public class ModItems
{

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(OuterLimitMod.MODID);

    public static final DeferredItem<Item> MAGNESIUM_RAW = reg("magnesium_raw", ModItem::new);
    public static final DeferredItem<Item> MAGNESIUM_INGOT = reg("magnesium_ingot", ModItem::new);
    public static final DeferredItem<Item> MAGNESIUM_NUGGET = reg("magnesium_nugget", ModItem::new);
    public static final DeferredItem<Item> MAGNESIUM_PLATE = reg("magnesium_plate", ModItem::new);

    public static final DeferredItem<Item> MOON_DUST = reg("moon_dust", ModItem::new);

    public static <T extends Item> DeferredItem<T> reg (final String name, final Supplier<? extends T> supplier)
    {
        return ModTabs.addItemToTab(ITEMS.register(name, supplier));
    }

}
