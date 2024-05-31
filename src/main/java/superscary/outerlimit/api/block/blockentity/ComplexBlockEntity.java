package superscary.outerlimit.api.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import superscary.outerlimit.api.InventoryTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ComplexBlockEntity extends BlockEntity
{

    private final int slotCount;
    private final int energyMaxCapacity;
    private final int energyMaxReceive;

    public static int SLOT = 0;

    private final ItemStackHandler items = createItemHandler();
    private final Lazy<IItemHandler> itemHandler = Lazy.of(() -> items);

    private final EnergyStorage energy = createEnergyStorage();
    private final Lazy<IEnergyStorage> energyHandler = Lazy.of(() -> energy);

    public ComplexBlockEntity (BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int slotCount, int energyCapacity, int energyMaxReceive)
    {
        super(pType, pPos, pBlockState);
        this.slotCount = slotCount;
        this.energyMaxCapacity = energyCapacity;
        this.energyMaxReceive = energyMaxReceive;
    }

    @Nonnull
    private ItemStackHandler createItemHandler ()
    {
        return new ItemStackHandler(slotCount)
        {
            @Override
            protected void onContentsChanged (int slot)
            {
                setChanged();
                assert level != null;
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        };
    }

    @Nonnull
    private EnergyStorage createEnergyStorage ()
    {
        return new EnergyStorage(energyMaxCapacity, energyMaxReceive);
    }

    @Override
    protected void saveAdditional (@NotNull CompoundTag pTag, HolderLookup.@NotNull Provider pRegistries)
    {
        super.saveAdditional(pTag, pRegistries);
        saveClientData(pTag, pRegistries);
    }

    private void saveClientData (CompoundTag tag, HolderLookup.Provider pRegistries)
    {
        tag.put(InventoryTags.INVENTORY, items.serializeNBT(pRegistries));
        tag.put(InventoryTags.ENERGY, energy.serializeNBT(pRegistries));
    }

    @Override
    public void loadAdditional (@NotNull CompoundTag pTag, HolderLookup.@NotNull Provider pRegistries)
    {
        super.loadAdditional(pTag, pRegistries);
        loadClientData(pTag, pRegistries);
    }

    private void loadClientData (CompoundTag pTag, HolderLookup.Provider pRegistries)
    {
        if (pTag.contains(InventoryTags.INVENTORY)) items.deserializeNBT(pRegistries, pTag.getCompound(InventoryTags.INVENTORY));
        else if (pTag.contains(InventoryTags.ENERGY)) energy.deserializeNBT(pRegistries, pTag.getCompound(InventoryTags.ENERGY));
    }

    @Override
    public @NotNull CompoundTag getUpdateTag (HolderLookup.@NotNull Provider pRegistries)
    {
        CompoundTag tag = super.getUpdateTag(pRegistries);
        saveClientData(tag, pRegistries);
        return tag;
    }

    @Override
    public void handleUpdateTag (@NotNull CompoundTag tag, HolderLookup.@NotNull Provider pRegistries)
    {
        loadClientData(tag, pRegistries);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket ()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket (@NotNull Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.@NotNull Provider lookupProvider)
    {
        CompoundTag tag = pkt.getTag();
        handleUpdateTag(tag, lookupProvider);
    }

    public abstract void tick ();

    private void ejectItem ()
    {
        BlockPos pos = worldPosition.relative(Direction.UP);
        assert level != null;
        Block.popResource(level, pos, items.extractItem(SLOT, 1, false));
    }

    public IItemHandler getItemHandler ()
    {
        return itemHandler.get();
    }

    public IEnergyStorage getEnergyStorage ()
    {
        return energyHandler.get();
    }

    public int getSlotCount ()
    {
        return slotCount;
    }

}
