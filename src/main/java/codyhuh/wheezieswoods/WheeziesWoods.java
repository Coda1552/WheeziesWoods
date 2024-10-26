package codyhuh.wheezieswoods;

import codyhuh.wheezieswoods.common.blocks.entity.ModBlockEntities;
import codyhuh.wheezieswoods.core.registry.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WheeziesWoods.MOD_ID)
public class WheeziesWoods {
    public static final String MOD_ID = "wheezieswoods";

    public WheeziesWoods() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModTabs.CREATIVE_TABS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
        bus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.ASPEN_SAPLING.getId(), ModBlocks.POTTED_ASPEN_SAPLING);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.ASPEN_SAPLING.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.ASPEN_LEAVES.get().asItem(), 0.4F);
        });
    }
}