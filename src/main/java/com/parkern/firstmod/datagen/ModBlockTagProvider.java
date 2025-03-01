package com.parkern.firstmod.datagen;

import com.parkern.firstmod.FirstMod;
import com.parkern.firstmod.block.ModBlocks;
import com.parkern.firstmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FirstMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SHALE_BLOCK.get())
                .add(ModBlocks.TITANITE_ORE.get())
                .add(ModBlocks.NETHER_TITANITE_ORE.get())
                .add(ModBlocks.END_TITANITE_ORE.get())
                .add(ModBlocks.WITHERER.get())
                .add(ModBlocks.ICICLE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.TITANITE_ORE.get())
                .add(ModBlocks.NETHER_TITANITE_ORE.get())
                .add(ModBlocks.END_TITANITE_ORE.get());

        tag(BlockTags.ICE).add(ModBlocks.ICICLE.get());

        tag(BlockTags.FENCES).add(ModBlocks.SHALE_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.SHALE_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.SHALE_WALL.get());

        tag(ModTags.Blocks.NEEDS_TITANITE_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_TITANITE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_TITANITE_TOOL);

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.BLOODWOOD_LOG.get())
                .add(ModBlocks.BLOODWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_BLOODWOOD_WOOD.get());
    }
}
