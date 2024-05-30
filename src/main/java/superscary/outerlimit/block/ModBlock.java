package superscary.outerlimit.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlock extends Block
{

    public ModBlock (Properties pProperties)
    {
        super(pProperties);
    }

    public ModBlock (Block block)
    {
        this (block.properties());
    }

}
