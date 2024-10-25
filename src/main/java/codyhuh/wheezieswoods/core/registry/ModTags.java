package codyhuh.wheezieswoods.core.registry;

import codyhuh.wheezieswoods.WheeziesWoods;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    
    public static class Blocks {
        public static final TagKey<Block> ASPEN_LOG_BLOCK = tag("aspen_log_block");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(WheeziesWoods.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> ASPEN_LOG_ITEM = tag("aspen_log_item");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(WheeziesWoods.MOD_ID, name));
        }
    }
    
}
