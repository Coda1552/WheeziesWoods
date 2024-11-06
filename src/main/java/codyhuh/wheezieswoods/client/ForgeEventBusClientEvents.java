package codyhuh.wheezieswoods.client;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.core.util.EntityRenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WheeziesWoods.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEventBusClientEvents {

    @SubscribeEvent
    public static <T extends LivingEntity, M extends EntityModel<T>> void onRenderLivingPre(RenderLivingEvent.Pre<T, M> pEvent) {
        LivingEntity entity = pEvent.getEntity();
        RenderLivingEvent.Post<T, M> event = new RenderLivingEvent.Post<>(entity, pEvent.getRenderer(), pEvent.getPartialTick(), pEvent.getPoseStack(), pEvent.getMultiBufferSource(), pEvent.getPackedLight());

        synchronized (EntityRenderUtil.hiddenEntities) {
            if (EntityRenderUtil.hiddenEntities.remove(entity.getUUID()) && EntityRenderUtil.shouldSkipRendering(false, Minecraft.getInstance().getCameraEntity())) {
                MinecraftForge.EVENT_BUS.post(event);
                pEvent.setCanceled(true);
            }
        }
    }
}
