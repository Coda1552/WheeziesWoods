package codyhuh.wheezieswoods.client.model;

import codyhuh.wheezieswoods.common.entity.OxEntity;
import net.minecraft.client.model.AgeableHierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class OxModel<T extends OxEntity> extends AgeableHierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftBackLeg;
	private final ModelPart rightBackLeg;
	private final ModelPart head;
	private final ModelPart tail;

	public OxModel(ModelPart root) {
		super(0.5F, 24.0F);
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.leftFrontLeg = this.root.getChild("leftFrontLeg");
		this.rightFrontLeg = this.root.getChild("rightFrontLeg");
		this.leftBackLeg = this.root.getChild("leftBackLeg");
		this.rightBackLeg = this.root.getChild("rightBackLeg");
		this.head = this.root.getChild("head");
		this.tail = this.root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, -4.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-6.0F, -10.0F, 8.0F, 12.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 70).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftFrontLeg = root.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(0, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, -5.0F));

		PartDefinition rightFrontLeg = root.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(16, 50).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 0.0F, -5.0F));

		PartDefinition leftBackLeg = root.addOrReplaceChild("leftBackLeg", CubeListBuilder.create().texOffs(32, 56).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, 12.0F));

		PartDefinition rightBackLeg = root.addOrReplaceChild("rightBackLeg", CubeListBuilder.create().texOffs(48, 56).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 0.0F, 12.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 32).addBox(-4.0F, -2.0F, -10.0F, 8.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 64).addBox(-9.0F, -2.0F, -2.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(64, 0).addBox(4.0F, -2.0F, -2.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 50).addBox(-9.0F, -4.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(64, 4).addBox(7.0F, -4.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -8.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(40, 47).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 14.0F, -1.2217F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.head.xRot += headPitch * ((float)Math.PI / 180F);
		this.head.yRot += netHeadYaw * ((float)Math.PI / 180F);
		this.rightBackLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftBackLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public ModelPart root() {
		return root;
	}
}