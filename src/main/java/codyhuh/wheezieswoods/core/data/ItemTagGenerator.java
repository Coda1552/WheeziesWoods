package codyhuh.wheezieswoods.core.data;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.core.registry.ModBlocks;
import codyhuh.wheezieswoods.core.registry.ModItems;
import codyhuh.wheezieswoods.core.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
    
    public ItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, WheeziesWoods.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {


        this.copy(ModTags.Blocks.ASPEN_LOG_BLOCK, ModTags.Items.ASPEN_LOG_ITEM);

        this.tag(ItemTags.LOGS_THAT_BURN).addTag(
                ModTags.Items.ASPEN_LOG_ITEM);

        this.tag(ItemTags.PLANKS).add(
                ModBlocks.ASPEN_PLANKS.get().asItem()
        );

        this.tag(ItemTags.LEAVES).add(
                ModBlocks.ASPEN_LEAVES.get().asItem()
        );
        this.tag(ItemTags.WOODEN_FENCES).add(
                ModBlocks.ASPEN_FENCE.get().asItem()
        );
        this.tag(ItemTags.FENCE_GATES).add(
                ModBlocks.ASPEN_FENCE_GATE.get().asItem()
        );

        this.tag(ItemTags.WOODEN_DOORS).add(
                ModBlocks.ASPEN_DOOR.get().asItem()
        );

        this.tag(ItemTags.WOODEN_TRAPDOORS).add(
                ModBlocks.ASPEN_TRAPDOOR.get().asItem()
        );

        this.tag(ItemTags.WOODEN_STAIRS).add(
                ModBlocks.ASPEN_STAIRS.get().asItem()
        );
        this.tag(ItemTags.WOODEN_SLABS).add(
                ModBlocks.ASPEN_SLAB.get().asItem()
        );
        this.tag(ItemTags.WOODEN_BUTTONS).add(
                ModBlocks.ASPEN_BUTTON.get().asItem()
        );
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(
                ModBlocks.ASPEN_PRESSURE_PLATE.get().asItem()
        );

        this.tag(ItemTags.SIGNS).add(
                ModItems.ASPEN_SIGN.get()
        );

        this.tag(ItemTags.HANGING_SIGNS).add(
                ModItems.ASPEN_HANGING_SIGN.get()
        );
    }
}
