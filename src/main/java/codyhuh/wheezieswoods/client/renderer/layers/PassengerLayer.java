package codyhuh.wheezieswoods.client.renderer.layers;

import codyhuh.wheezieswoods.client.model.CoveredWagonModel;
import codyhuh.wheezieswoods.core.util.EntityRenderUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PassengerLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public PassengerLayer(RenderLayerParent<T, M> render) {
        super(render);
    }

    @Override
    public void render(@NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLightIn, T pEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pEntity.isVehicle() || pEntity.getPassengers().isEmpty()) {
            return;
        }
        synchronized (EntityRenderUtil.hiddenEntities) {
            pEntity.getPassengers().stream().filter(passenger -> !EntityRenderUtil.shouldSkipRendering(true, passenger)).forEach(passenger -> {
                UUID uuid = passenger.getUUID();
                EntityRenderUtil.hiddenEntities.remove(uuid);
                pMatrixStack.pushPose();


                if (getParentModel() instanceof CoveredWagonModel<?> wagon) {
                    wagon.passenger.translateAndRotate(pMatrixStack);
                }

                pMatrixStack.mulPose(Axis.XP.rotationDegrees(180F));
                pMatrixStack.translate(0.0F, -1.0F, 0.0F);
                pMatrixStack.mulPose(Axis.YP.rotation(0F));

                EntityRenderUtil.renderEntity(passenger, pPartialTicks, pMatrixStack, pBuffer, pPackedLightIn);
                pMatrixStack.popPose();
                EntityRenderUtil.hiddenEntities.add(uuid);
            });
        }
    }

}