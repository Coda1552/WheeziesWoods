package codyhuh.wheezieswoods.core.data;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.core.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
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
        
        axisBlock(((RotatedPillarBlock) ModBlocks.ASPEN_WOOD.get()), blockTexture(ModBlocks.ASPEN_LOG.get()), blockTexture(ModBlocks.ASPEN_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_ASPEN_LOG.get()), blockTexture(ModBlocks.STRIPPED_ASPEN_LOG.get()), new ResourceLocation(WheeziesWoods.MOD_ID, "block/stripped_aspen_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_ASPEN_WOOD.get()), blockTexture(ModBlocks.STRIPPED_ASPEN_LOG.get()), blockTexture(ModBlocks.STRIPPED_ASPEN_LOG.get()));

        blockItem(ModBlocks.STRIPPED_ASPEN_LOG);
        blockItem(ModBlocks.ASPEN_WOOD);
        blockItem(ModBlocks.STRIPPED_ASPEN_WOOD);

        simpleBlockWithItem(ModBlocks.POTTED_ASPEN_SAPLING.get(), models().singleTexture("potted_aspen_sapling", new ResourceLocation("flower_pot_cross"), "plant", blockTexture(ModBlocks.ASPEN_SAPLING.get())).renderType("cutout"));

        signBlock(((StandingSignBlock) ModBlocks.ASPEN_SIGN.get()), ((WallSignBlock) ModBlocks.ASPEN_WALL_SIGN.get()),
                blockTexture(ModBlocks.ASPEN_PLANKS.get()));
        hangingSignBlock(ModBlocks.ASPEN_HANGING_SIGN.get(), ModBlocks.ASPEN_WALL_HANGING_SIGN.get(),
                blockTexture(ModBlocks.ASPEN_PLANKS.get()));
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

<<<<<<< HEAD
    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(WheeziesWoods.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
    
    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject
                .get()));
=======
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
>>>>>>> master
    }
}
