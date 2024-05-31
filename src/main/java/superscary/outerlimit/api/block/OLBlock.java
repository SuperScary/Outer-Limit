package superscary.outerlimit.api.block;

import net.minecraft.world.level.block.Block;

public class OLBlock extends Block
{

    public OLBlock (Properties pProperties)
    {
        super(pProperties);
    }

    public OLBlock (Block block)
    {
        this (block.properties());
    }

}
