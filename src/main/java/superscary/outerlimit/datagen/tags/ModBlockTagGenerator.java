package superscary.outerlimit.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import superscary.outerlimit.block.ModBlocks;
import superscary.outerlimit.api.data.IOuterLimitDataProvider;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static superscary.outerlimit.OL.MODID;

public class ModBlockTagGenerator extends BlockTagsProvider implements IOuterLimitDataProvider
{

    public ModBlockTagGenerator (PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags (HolderLookup.@NotNull Provider provider)
    {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MOON_DUST_BLOCK.get())
                .add(ModBlocks.MAGNESIUM_MOON_ORE.get())
                .add(ModBlocks.MAGNESIUM_ORE.get())
                .add(ModBlocks.MAGNESIUM_NETHER_ORE.get())
                .add(ModBlocks.MAGNESIUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.MAGNESIUM_BLOCK_RAW.get())
                .add(ModBlocks.MAGNESIUM_BLOCK.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.MAGNESIUM_MOON_ORE.get())
                .add(ModBlocks.MAGNESIUM_ORE.get())
                .add(ModBlocks.MAGNESIUM_NETHER_ORE.get())
                .add(ModBlocks.MAGNESIUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.MAGNESIUM_BLOCK.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.MOON_DUST_BLOCK.get());

    }

}
