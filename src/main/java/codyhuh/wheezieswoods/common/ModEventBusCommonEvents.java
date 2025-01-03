package codyhuh.wheezieswoods.common;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.common.entity.OxEntity;
import codyhuh.wheezieswoods.core.registry.ModBlocks;
import codyhuh.wheezieswoods.core.registry.ModEntities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = WheeziesWoods.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusCommonEvents {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.OX.get(), OxEntity.createOxAttributes().build());
        event.put(ModEntities.COVERED_WAGON.get(), LivingEntity.createLivingAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent e) {
        e.register(ModEntities.OX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, OxEntity::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.ASPEN_SAPLING.getId(), ModBlocks.POTTED_ASPEN_SAPLING);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.ASPEN_SAPLING.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.ASPEN_LEAVES.get().asItem(), 0.4F);
        });
    }
}
