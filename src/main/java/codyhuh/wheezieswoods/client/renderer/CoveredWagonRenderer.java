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
import net.minecraft.world.entity.Entity;

public class CoveredWagonRenderer<T extends CoveredWagonEntity> extends EntityRenderer<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(WheeziesWoods.MOD_ID,"textures/entity/covered_wagon.png");
    private final CoveredWagonModel<T> model;

    public CoveredWagonRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        model = new CoveredWagonModel<>(ctx.bakeLayer(ModModelLayers.COVERED_WAGON_LAYER));
    }

    @Override
    public void render(T entity, float pEntityYaw, float pPartialTick, PoseStack poseStack, MultiBufferSource buffer, int pPackedLight) {
        super.render(entity, pEntityYaw, pPartialTick, poseStack, buffer, pPackedLight);

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));

        poseStack.pushPose();

        poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
        poseStack.translate(0.0D, -1.5D, 0.0D);

        model.setupAnim(entity, entity.tickCount * 0.5F, 0.3F, entity.tickCount * 0.5F, 0.0F, 0.0F);
        model.renderToBuffer(poseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return TEXTURE;
    }
}
