package superscary.outerlimit.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import superscary.outerlimit.item.ModItems;

import java.util.function.Supplier;

import static superscary.outerlimit.OuterLimitMod.MODID;

public class ModBlocks
{

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final DeferredBlock<Block> MAGNESIUM_MOON_ORE = reg("magnesium_moon_ore", () -> new ModExperienceBlock(Blocks.IRON_ORE));
    public static final DeferredBlock<Block> MAGNESIUM_ORE = reg("magnesium_ore", () -> new ModExperienceBlock(Blocks.IRON_ORE));
    public static final DeferredBlock<Block> MAGNESIUM_DEEPSLATE_ORE = reg("magnesium_deepslate_ore", () -> new ModExperienceBlock(Blocks.DEEPSLATE_IRON_ORE));
    public static final DeferredBlock<Block> MAGNESIUM_NETHER_ORE = reg("magnesium_nether_ore", () -> new ModExperienceBlock(Blocks.DEEPSLATE_IRON_ORE));
    public static final DeferredBlock<Block> MAGNESIUM_BLOCK = reg("magnesium_block", () -> new ModBlock(Blocks.IRON_BLOCK));

    public static final DeferredBlock<Block> MOON_DUST_BLOCK = reg("moon_dust_block", () -> new ModBlock(Blocks.STONE));

    public static <T extends Block> DeferredBlock<T> reg (final String name, final Supplier<? extends T> supplier)
    {
        DeferredBlock<T> obj = BLOCKS.register(name, supplier);
        ModItems.reg(name, () -> new BlockItem(obj.get(), new Item.Properties()));
        return obj;
    }

}
