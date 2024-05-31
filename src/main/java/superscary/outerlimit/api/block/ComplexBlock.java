package superscary.outerlimit.api.block;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import superscary.outerlimit.api.block.blockentity.ComplexBlockEntity;

public abstract class ComplexBlock extends OLBlock implements EntityBlock
{

    public static final BlockBehaviour.Properties DEFAULT_BEHAVIOR = BlockBehaviour.Properties.of().strength(3.5f).requiresCorrectToolForDrops().sound(SoundType.METAL);

    public ComplexBlock (Properties properties)
    {
        super(properties);
    }

    public ComplexBlock ()
    {
        this(DEFAULT_BEHAVIOR);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker (Level level, BlockState state, BlockEntityType<T> type)
    {
        if (level.isClientSide)
        {
            return null;
        } else
        {
            return (lvl, pos, st, blockEntity) -> {
                if (blockEntity instanceof ComplexBlockEntity be)
                {
                    be.tick();
                }
            };
        }
    }

}
