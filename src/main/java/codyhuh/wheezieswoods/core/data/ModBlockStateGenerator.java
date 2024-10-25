package codyhuh.wheezieswoods.core.data;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.core.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateGenerator extends BlockStateProvider {

    public ModBlockStateGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, WheeziesWoods.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(ModBlocks.ASPEN_PLANKS);
        stairsBlock(((StairBlock) ModBlocks.ASPEN_STAIRS.get()), blockTexture(ModBlocks.ASPEN_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.ASPEN_SLAB.get()), blockTexture(ModBlocks.ASPEN_PLANKS.get()), blockTexture(ModBlocks.ASPEN_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.ASPEN_BUTTON.get()), blockTexture(ModBlocks.ASPEN_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.ASPEN_PRESSURE_PLATE.get()), blockTexture(ModBlocks.ASPEN_PLANKS.get()));
        fenceBlock(((FenceBlock) ModBlocks.ASPEN_FENCE.get()), blockTexture(ModBlocks.ASPEN_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.ASPEN_FENCE_GATE.get()), blockTexture(ModBlocks.ASPEN_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.ASPEN_DOOR.get()), modLoc("block/aspen_door_bottom"), modLoc("block/aspen_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.ASPEN_TRAPDOOR.get()), modLoc("block/aspen_trapdoor"), true, "cutout");

    }


    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject
                .get()));
    }
}
