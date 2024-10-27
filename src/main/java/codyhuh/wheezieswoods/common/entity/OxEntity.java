package codyhuh.wheezieswoods.common.entity;

import codyhuh.wheezieswoods.core.registry.ModEntities;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import javax.annotation.Nullable;

public class OxEntity extends Cow {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OxEntity.class, EntityDataSerializers.INT);

    public OxEntity(EntityType<? extends Cow> p_28285_, Level p_28286_) {
        super(p_28285_, p_28286_);
    }

    public static AttributeSupplier.Builder createOxAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.22F);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level().isClientSide && stack.is(Items.OXEYE_DAISY) && getVariant() == 0) {
            playSound(SoundEvents.HORSE_EAT);
            //player.swing(hand);

            Vec3 pos = getYawVec(yBodyRot, 0.0D, 1.25D).add(position()).add(0.0D, 0.75D, 0.0D);
            if (level() instanceof ServerLevel server) {
                for (int i = 0; i < 5; i++) {
                    server.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), pos.x, pos.y, pos.z, 0,0.0D, 0.0D, 0.0D, 0.0D);
                }
            }
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }

            double random = getRandom().nextDouble();

            if (random > 0.85F) {
                if (level() instanceof ServerLevel server) {
                    for (int i = 0; i < 100; i++) {
                        server.sendParticles(new DustParticleOptions(Vec3.fromRGB24(0xf7f7f7).toVector3f(), 1.0F), getRandomX(1.0D), getRandomY(), getRandomZ(1.0D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
                        server.sendParticles(new DustParticleOptions(Vec3.fromRGB24(0xfed639).toVector3f(), 1.0F), getRandomX(1.0D), getRandomY(), getRandomZ(1.0D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
                    }
                }
                level().playSound(this, blockPosition(), SoundEvents.MOOSHROOM_CONVERT, SoundSource.NEUTRAL, 1.0F, 1.0F);
                setVariant(1);
            }
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    private static Vec3 getYawVec(float yaw, double xOffset, double zOffset) {
        return new Vec3(xOffset, 0, zOffset).yRot(-yaw * ((float) Math.PI / 180f));
    }

    @Nullable
    @Override
    public Cow getBreedOffspring(ServerLevel level, AgeableMob p_148891_) {
        return ModEntities.OX.get().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.HANGING_ROOTS);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (spawnDataIn == null && reason != MobSpawnType.BREEDING) {
            if (random.nextDouble() > 0.95D) {
                setVariant(1);
            }
            else {
                setVariant(0);
            }
        }

        return spawnDataIn;
    }


}
