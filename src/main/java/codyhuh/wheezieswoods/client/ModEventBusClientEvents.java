package codyhuh.wheezieswoods.client;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.client.model.CoveredWagonModel;
import codyhuh.wheezieswoods.client.model.OxModel;
import codyhuh.wheezieswoods.client.renderer.CoveredWagonRenderer;
import codyhuh.wheezieswoods.client.renderer.ModBoatRenderer;
import codyhuh.wheezieswoods.client.renderer.OxRenderer;
import codyhuh.wheezieswoods.common.blocks.entity.ModBlockEntities;
import codyhuh.wheezieswoods.core.registry.ModEntities;
import codyhuh.wheezieswoods.core.util.ModWoodTypes;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WheeziesWoods.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        Sheets.addWoodType(ModWoodTypes.ASPEN);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.OX_LAYER, OxModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.COVERED_WAGON_LAYER, CoveredWagonModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ASPEN_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.ASPEN_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.OX.get(), OxRenderer::new);
        event.registerEntityRenderer(ModEntities.COVERED_WAGON.get(), CoveredWagonRenderer::new);
        event.registerEntityRenderer(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
        event.registerEntityRenderer(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));

        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
