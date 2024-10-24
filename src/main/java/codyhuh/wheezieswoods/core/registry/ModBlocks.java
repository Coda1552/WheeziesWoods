package codyhuh.wheezieswoods.core.registry;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.common.level.tree.growers.AspenTreeGrower;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WheeziesWoods.MOD_ID);

    public static final RegistryObject<Block> ASPEN_SAPLING = register("aspen_sapling", () -> new SaplingBlock(new AspenTreeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).instabreak().ignitedByLava().noCollission().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> ASPEN_LOG = registerRotatedPillar("aspen_log", () -> Blocks.STRIPPED_BIRCH_LOG, BlockBehaviour.Properties.copy(Blocks.BIRCH_LOG).ignitedByLava());
    public static final RegistryObject<Block> ASPEN_LEAVES = register("aspen_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_LEAVES).ignitedByLava()));

    private static RegistryObject<Block> registerRotatedPillar(String name, Supplier<Block> stripped, BlockBehaviour.Properties properties) {
        return register(name, () -> new RotatedPillarBlock(properties) {
            @Override
            public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
                if (toolAction == ToolActions.AXE_STRIP) {
                    return stripped.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                }
                return super.getToolModifiedState(state, context, toolAction, simulate);
            }
        });
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        return register(name, block, new Item.Properties());
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Item.Properties itemProperties) {
        return register(name, block, BlockItem::new, itemProperties);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, BiFunction<Block, Item.Properties, BlockItem> item, Item.Properties itemProperties) {
        final RegistryObject<T> registryObject = BLOCKS.register(name, block);
        if (itemProperties != null)
            ModItems.ITEMS.register(name, () -> item == null ? new BlockItem(registryObject.get(), itemProperties) : item.apply(registryObject.get(), itemProperties));
        return registryObject;
    }
}
