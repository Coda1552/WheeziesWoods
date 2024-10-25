package codyhuh.wheezieswoods.core.data;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.core.registry.ModBlocks;
import codyhuh.wheezieswoods.core.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {


    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WheeziesWoods.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                ModBlocks.ASPEN_PLANKS.get(),
                ModBlocks.ASPEN_STAIRS.get(),
                ModBlocks.ASPEN_SLAB.get(),
                ModBlocks.ASPEN_BUTTON.get(),
                ModBlocks.ASPEN_PRESSURE_PLATE.get(),
                ModBlocks.ASPEN_FENCE.get(),
                ModBlocks.ASPEN_FENCE_GATE.get(),
                ModBlocks.ASPEN_DOOR.get(),
                ModBlocks.ASPEN_TRAPDOOR.get(),
                ModBlocks.ASPEN_LOG.get()
//                ,ModBlocks.STRIPPED_ASPEN_LOG.get(),
//                ModBlocks.ASPEN_WOOD.get(),
//                ModBlocks.STRIPPED_ASPEN_WOOD.get(),
//                ModBlocks.ASPEN_SIGN.get(),
//                ModBlocks.ASPEN_WALL_SIGN.get(),
//                ModBlocks.ASPEN_HANGING_SIGN.get(),
//                ModBlocks.ASPEN_WALL_HANGING_SIGN.get()
        );

        this.tag(BlockTags.PLANKS).add(
                ModBlocks.ASPEN_PLANKS.get()
        );

        this.tag(ModTags.Blocks.ASPEN_LOG_BLOCK).add(
                ModBlocks.ASPEN_LOG.get()
//                ,ModBlocks.STRIPPED_ASPEN_LOG.get(),
//                ModBlocks.ASPEN_WOOD.get(),
//                ModBlocks.STRIPPED_ASPEN_WOOD.get()
        );

        this.tag(BlockTags.LOGS_THAT_BURN).addTag(
                ModTags.Blocks.ASPEN_LOG_BLOCK
        );

        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                ModBlocks.ASPEN_LEAVES.get()
        );

        this.tag(BlockTags.LEAVES).add(
                ModBlocks.ASPEN_LEAVES.get()
        );

        this.tag(BlockTags.WOODEN_FENCES).add(
                ModBlocks.ASPEN_FENCE.get()
        );

        this.tag(BlockTags.FENCE_GATES).add(
                ModBlocks.ASPEN_FENCE_GATE.get()
        );

        this.tag(BlockTags.WOODEN_DOORS).add(
                ModBlocks.ASPEN_DOOR.get()
        );

        this.tag(BlockTags.WOODEN_TRAPDOORS).add(
                ModBlocks.ASPEN_TRAPDOOR.get()
        );

        this.tag(BlockTags.WOODEN_STAIRS).add(
                ModBlocks.ASPEN_STAIRS.get()
        );
        this.tag(BlockTags.WOODEN_SLABS).add(
                ModBlocks.ASPEN_SLAB.get()
        );
        this.tag(BlockTags.WOODEN_BUTTONS).add(
                ModBlocks.ASPEN_BUTTON.get()
        );
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(
                ModBlocks.ASPEN_PRESSURE_PLATE.get()
        );

//        this.tag(BlockTags.SIGNS).add(
//                ModBlocks.ASPEN_SIGN.get()
//        );

//        this.tag(BlockTags.WALL_SIGNS).add(
//                ModBlocks.ASPEN_WALL_SIGN.get()
//        );

//        this.tag(BlockTags.CEILING_HANGING_SIGNS).add(
//                ModBlocks.ASPEN_HANGING_SIGN.get()
//        );
//
//        this.tag(BlockTags.WALL_HANGING_SIGNS).add(
//                ModBlocks.ASPEN_WALL_HANGING_SIGN.get()
//        );

    }
}
