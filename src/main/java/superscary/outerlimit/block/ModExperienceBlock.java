package superscary.outerlimit.block;

import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModExperienceBlock extends DropExperienceBlock
{

    public ModExperienceBlock (Properties pProperties, IntProvider xp)
    {
        super(xp, pProperties);
    }

    public ModExperienceBlock (Block block)
    {
        this (block.properties().requiresCorrectToolForDrops(), UniformInt.of(3, 6));
    }

    public ModExperienceBlock (Block block, int min, int max)
    {
        this(block.properties().requiresCorrectToolForDrops(), UniformInt.of(min, max));
    }

}
