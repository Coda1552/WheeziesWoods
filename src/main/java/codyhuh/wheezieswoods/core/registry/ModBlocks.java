package codyhuh.wheezieswoods.core.registry;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.common.blocks.*;
import codyhuh.wheezieswoods.common.level.tree.growers.AspenTreeGrower;
import codyhuh.wheezieswoods.core.util.ModWoodTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
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

    public static final RegistryObject<Block> ASPEN_SAPLING = register("aspen_sapling",
            () -> new SaplingBlock(new AspenTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)
                    .mapColor(MapColor.COLOR_LIGHT_GREEN).instabreak().ignitedByLava().noCollission().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> POTTED_ASPEN_SAPLING = registerExcludeFromTab("potted_aspen_sapling",
            ()-> new FlowerPotBlock(()-> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.ASPEN_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final RegistryObject<Block> ASPEN_LOG = register ("aspen_log",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_ASPEN_LOG = register ("stripped_aspen_log",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ASPEN_WOOD = register ("aspen_wood",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_ASPEN_WOOD = register ("stripped_aspen_wood",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ASPEN_LEAVES = register("aspen_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_LEAVES).ignitedByLava()));

    
    //planks
    public static final RegistryObject<Block> ASPEN_PLANKS = register("aspen_planks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> ASPEN_STAIRS = register("aspen_stairs",
            ()-> new StairBlock(() -> ModBlocks.ASPEN_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> ASPEN_SLAB = register("aspen_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> ASPEN_BUTTON = register("aspen_button",
            ()-> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 25, true){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> ASPEN_PRESSURE_PLATE = register("aspen_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> ASPEN_FENCE = register("aspen_fence",
            ()-> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> ASPEN_FENCE_GATE = register("aspen_fence_gate",
            ()-> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });

    //doors
    public static final RegistryObject<Block> ASPEN_DOOR = register("aspen_door",
            ()-> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).noOcclusion(), BlockSetType.OAK));
    public static final RegistryObject<Block> ASPEN_TRAPDOOR = register("aspen_trapdoor",
            ()-> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).noOcclusion(),BlockSetType.OAK));
    
    
    public static final RegistryObject<Block> ASPEN_SIGN = BLOCKS.register("aspen_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SIGN), ModWoodTypes.ASPEN));

    public static final RegistryObject<Block> ASPEN_WALL_SIGN = BLOCKS.register("aspen_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_SIGN), ModWoodTypes.ASPEN));

    public static final RegistryObject<Block> ASPEN_HANGING_SIGN = BLOCKS.register("aspen_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_HANGING_SIGN), ModWoodTypes.ASPEN));

    public static final RegistryObject<Block> ASPEN_WALL_HANGING_SIGN = BLOCKS.register("aspen_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_HANGING_SIGN), ModWoodTypes.ASPEN));


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

    private static <T extends Block> RegistryObject<T> registerExcludeFromTab(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        //registerBlockItem(name, toReturn);
        return toReturn;
    }

//    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name,RegistryObject<T> block){
//        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
//    }

}
