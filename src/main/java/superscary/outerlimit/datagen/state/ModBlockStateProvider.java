package superscary.outerlimit.datagen.state;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import superscary.outerlimit.block.ModBlocks;
import superscary.outerlimit.api.data.IOuterLimitDataProvider;

import static superscary.outerlimit.OL.MODID;

public class ModBlockStateProvider extends BlockStateProvider implements IOuterLimitDataProvider
{

    public ModBlockStateProvider (PackOutput packOutput, ExistingFileHelper existingFileHelper)
    {
        super(packOutput, MODID, existingFileHelper);
    }

    @Override
    public void registerStatesAndModels ()
    {
        blockWithItem(ModBlocks.MAGNESIUM_MOON_ORE);
        blockWithItem(ModBlocks.MAGNESIUM_ORE);
        blockWithItem(ModBlocks.MAGNESIUM_NETHER_ORE);
        blockWithItem(ModBlocks.MAGNESIUM_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.MOON_DUST_BLOCK);
        blockWithItem(ModBlocks.MAGNESIUM_BLOCK);
        blockWithItem(ModBlocks.MAGNESIUM_BLOCK_RAW);
    }

    private void leavesBlock (DeferredBlock<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().cubeAll(Registries.BLOCK.registry().getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void saplingBlock (DeferredBlock<Block> blockRegistryObject)
    {
        simpleBlock(blockRegistryObject.get(),
                models().cross(Registries.BLOCK.registry().getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem (DeferredBlock<Block> blockRegistryObject, String appendix)
    {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("outerlimit:block/" + Registries.BLOCK.registry().getPath() + appendix));
    }

    private void blockItem (DeferredBlock<Block> blockRegistryObject)
    {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("outerlimit:block/" + Registries.BLOCK.registry().getPath()));
    }

    private void blockWithItem (DeferredBlock<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockWithItem (Block block)
    {
        simpleBlockWithItem(block, cubeAll(block));
    }

}
