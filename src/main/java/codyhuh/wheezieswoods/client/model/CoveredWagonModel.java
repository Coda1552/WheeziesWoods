package codyhuh.wheezieswoods.client.model;

import codyhuh.wheezieswoods.common.entity.CoveredWagonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CoveredWagonModel<T extends CoveredWagonEntity> extends EntityModel<T> {
	private final ModelPart wagon;
	private final ModelPart backWheels;
	private final ModelPart frontWheels;

	public CoveredWagonModel(ModelPart root) {
		this.wagon = root.getChild("wagon");
		this.backWheels = this.wagon.getChild("backWheels");
		this.frontWheels = this.wagon.getChild("frontWheels");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition wagon = partdefinition.addOrReplaceChild("wagon", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, -2.35F, -28.0F, 26.0F, 5.0F, 51.0F, new CubeDeformation(0.0F))
		.texOffs(0, 56).addBox(-13.0F, -30.35F, -19.0F, 26.0F, 28.0F, 42.0F, new CubeDeformation(0.0F))
		.texOffs(0, 126).addBox(-10.0F, 2.65F, -21.0F, 20.0F, 10.0F, 42.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.45F, 0.0F));

		PartDefinition backWheels = wagon.addOrReplaceChild("backWheels", CubeListBuilder.create().texOffs(124, 126).addBox(-13.0F, -1.0F, -1.0F, 26.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.65F, 16.0F));

		PartDefinition cube_r1 = backWheels.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(136, 56).addBox(0.0F, -7.0F, -7.0F, 0.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r2 = backWheels.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(136, 56).addBox(0.0F, -7.0F, -7.0F, 0.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition frontWheels = wagon.addOrReplaceChild("frontWheels", CubeListBuilder.create().texOffs(124, 126).addBox(-13.0F, -1.0F, -1.0F, 26.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.65F, -16.0F));

		PartDefinition cube_r3 = frontWheels.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(136, 56).addBox(0.0F, -7.0F, -7.0F, 0.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r4 = frontWheels.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(136, 56).addBox(0.0F, -7.0F, -7.0F, 0.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//this.frontWheels.xRot = ageInTicks * 0.035F;
		//this.backWheels.xRot = ageInTicks * 0.035F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		wagon.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}