package codyhuh.wheezieswoods.client.renderer;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.client.ModModelLayers;
import codyhuh.wheezieswoods.client.model.OxModel;
import codyhuh.wheezieswoods.common.entity.OxEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OxRenderer extends MobRenderer<OxEntity, OxModel<OxEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(WheeziesWoods.MOD_ID,"textures/entity/ox/ox.png");
    private static final ResourceLocation DAISY_TEXTURE = new ResourceLocation(WheeziesWoods.MOD_ID,"textures/entity/ox/daisy_covered_ox.png");

    public OxRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new OxModel<>(ctx.bakeLayer(ModModelLayers.OX_LAYER)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(OxEntity entity) {
        return entity.isDaisyCovered() ? DAISY_TEXTURE : TEXTURE;
    }
}
