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
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import superscary.outerlimit.block.ModBlocks;
import superscary.outerlimit.block.entity.ModBlockEntities;
import superscary.outerlimit.item.ModItems;

@Mod(OL.MODID)
public class OL
{

    public static final String MODID = "outerlimit";
    private static final Logger LOGGER = LogUtils.getLogger();

    public OL (IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModTabs.CREATIVE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::registerCapabilities);
    }

    private void commonSetup (final FMLCommonSetupEvent event)
    {
    }

    private void registerCapabilities (RegisterCapabilitiesEvent event)
    {
        //event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.COMPLEX_BLOCK_ENTITY.get(), (o, direction) -> o.getItemHandler());
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

    public static String getModid ()
    {
        return MODID;
    }

}
