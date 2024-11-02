package codyhuh.wheezieswoods.client.renderer;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.client.ModModelLayers;
import codyhuh.wheezieswoods.client.model.CoveredWagonModel;
import codyhuh.wheezieswoods.common.entity.CoveredWagonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class CoveredWagonRenderer<T extends CoveredWagonEntity> extends EntityRenderer<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(WheeziesWoods.MOD_ID,"textures/entity/covered_wagon.png");
    private final CoveredWagonModel<T> model;

    public CoveredWagonRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        model = new CoveredWagonModel<>(ctx.bakeLayer(ModModelLayers.COVERED_WAGON_LAYER));
    }

    @Override
    public void render(T entity, float pEntityYaw, float pPartialTick, PoseStack poseStack, MultiBufferSource buffer, int pPackedLight) {
        poseStack.pushPose();

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));

        poseStack.translate(0.0F, 1.5D, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - pEntityYaw));

        float f = (float)entity.getHurtTime() - pPartialTick;
        float f1 = entity.getDamage() - pPartialTick;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)entity.getHurtDir()));
        }

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        model.setupAnim(entity, pPartialTick, 0.0F, entity.level().nextSubTickCount(), 0.0F, 0.0F);
        model.renderToBuffer(poseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
        super.render(entity, pEntityYaw, pPartialTick, poseStack, buffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return TEXTURE;
    }
}