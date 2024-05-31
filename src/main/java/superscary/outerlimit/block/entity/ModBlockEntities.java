package superscary.outerlimit.block.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import superscary.outerlimit.OL;
import superscary.outerlimit.block.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities
{

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, OL.MODID);

    //public static final Supplier<BlockEntityType<ComplexBlockEntity>> COMPLEX_BLOCK_ENTITY = BLOCK_ENTITIES.register("complex_block", () -> BlockEntityType.Builder.of(ComplexBlockEntity::new, ModBlocks.COMPLEX_BLOCK.get()).build(null));

}
