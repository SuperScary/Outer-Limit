package superscary.outerlimit;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModTabs
{

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OuterLimitMod.MODID);
    public static final List<Supplier<? extends ItemLike>> TAB_ITEMS = new ArrayList<>();

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_TABS.register("outerlimittab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.outerlimit"))
                    .icon(() -> new ItemStack(Items.NAME_TAG))
                    .displayItems((displayParams, output) -> {
                        TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get()));
                    })
                    .build()
    );

    public static <T extends Item> DeferredItem<T> addItemToTab (DeferredItem<T> itemLike)
    {
        TAB_ITEMS.add(itemLike);
        return itemLike;
    }

}
