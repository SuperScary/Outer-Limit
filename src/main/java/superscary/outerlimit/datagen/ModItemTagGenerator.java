package superscary.outerlimit.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static superscary.outerlimit.OuterLimitMod.MODID;

public class ModItemTagGenerator extends ItemTagsProvider
{

    public ModItemTagGenerator (PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> completableFuture, ExistingFileHelper existingFileHelper)
    {
        super(packOutput, future, completableFuture, MODID, existingFileHelper);
    }

    @Override
    protected void addTags (HolderLookup.Provider provider)
    {

    }

}
