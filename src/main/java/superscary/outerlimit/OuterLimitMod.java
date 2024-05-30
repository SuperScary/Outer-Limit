package superscary.outerlimit;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import superscary.outerlimit.block.ModBlocks;
import superscary.outerlimit.item.ModItems;

@Mod(OuterLimitMod.MODID)
public class OuterLimitMod
{

    public static final String MODID = "outerlimit";
    private static final Logger LOGGER = LogUtils.getLogger();

    public OuterLimitMod (IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTabs.CREATIVE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup (final FMLCommonSetupEvent event)
    {
    }

    @SubscribeEvent
    public void onServerStarting (ServerStartingEvent event)
    {
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup (FMLClientSetupEvent event)
        {
        }
    }

    public static ResourceLocation getResource (String path)
    {
        return new ResourceLocation(MODID, path);
    }

}
