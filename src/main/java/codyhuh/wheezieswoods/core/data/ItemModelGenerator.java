package codyhuh.wheezieswoods.core.data;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.core.registry.ModBlocks;
import codyhuh.wheezieswoods.core.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ItemModelGenerator extends ItemModelProvider {

    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WheeziesWoods.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(ModBlocks.ASPEN_DOOR);
        trapdoorItem(ModBlocks.ASPEN_TRAPDOOR);

        evenSimplerBlockItem(ModBlocks.ASPEN_STAIRS);
        evenSimplerBlockItem(ModBlocks.ASPEN_SLAB);
        evenSimplerBlockItem(ModBlocks.ASPEN_FENCE_GATE);
        evenSimplerBlockItem(ModBlocks.ASPEN_PRESSURE_PLATE);

        fenceItem(ModBlocks.ASPEN_FENCE, ModBlocks.ASPEN_PLANKS);
        buttonItem(ModBlocks.ASPEN_BUTTON, ModBlocks.ASPEN_PLANKS);

        simpleItem(ModItems.ASPEN_SIGN);
        simpleItem(ModItems.ASPEN_HANGING_SIGN);

        simpleItem(ModItems.ASPEN_BOAT);
        simpleItem(ModItems.ASPEN_CHEST_BOAT);

        simpleItem(ModItems.COVERED_WAGON);
        spawnEgg(ModItems.OX_SPAWN_EGG);
    }

    private ItemModelBuilder spawnEgg(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/template_spawn_egg"));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(WheeziesWoods.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(WheeziesWoods.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(WheeziesWoods.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(WheeziesWoods.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(WheeziesWoods.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(WheeziesWoods.MOD_ID,"block/" + item.getId().getPath()));
    }
}
