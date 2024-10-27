package codyhuh.wheezieswoods.common.entity;

import codyhuh.wheezieswoods.core.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class OxEntity extends Animal implements NeutralMob {
    private static final EntityDataAccessor<Integer> REMAINING_ANGER_TIME = SynchedEntityData.defineId(OxEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OxEntity.class, EntityDataSerializers.INT);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(15, 29);
    @Nullable
    private UUID persistentAngerTarget;

    public OxEntity(EntityType<? extends Animal> p_28285_, Level p_28286_) {
        super(p_28285_, p_28286_);
        setMaxUpStep(1.05F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 2.0D, true));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.HANGING_ROOTS), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ResetUniversalAngerTargetGoal<>(this, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_28306_) {
        return SoundEvents.COW_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    public static AttributeSupplier.Builder createOxAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.22F).add(Attributes.ATTACK_DAMAGE, 4.0D).add(Attributes.ATTACK_KNOCKBACK, 3.0D);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level().isClientSide && stack.is(Items.OXEYE_DAISY) && getVariant() == 0) {
            playSound(SoundEvents.HORSE_EAT);

            Vec3 pos = getYawVec(yBodyRot, 0.0D, 1.25D).add(position()).add(0.0D, 0.75D, 0.0D);
            if (level() instanceof ServerLevel server) {
                for (int i = 0; i < 5; i++) {
                    server.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), pos.x, pos.y, pos.z, 0, 0.0D, 0.0D, 0.0D, 0.0D);
                }
            }
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }

            double random = getRandom().nextDouble();

            if (random > 0.85F) {
                if (level() instanceof ServerLevel server) {
                    for (int i = 0; i < 100; i++) {
                        server.sendParticles(new DustParticleOptions(Vec3.fromRGB24(0xf7f7f7).toVector3f(), 2.0F), getRandomX(1.0D), getRandomY(), getRandomZ(1.0D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
                        server.sendParticles(new DustParticleOptions(Vec3.fromRGB24(0xfed639).toVector3f(), 2.0F), getRandomX(1.0D), getRandomY(), getRandomZ(1.0D), 0, 0.0D, 0.0D, 0.0D, 0.0D);
                    }
                }
                level().playSound(this, blockPosition(), SoundEvents.MOOSHROOM_CONVERT, SoundSource.NEUTRAL, 1.0F, 1.0F);
                setVariant(1);
            }
            return InteractionResult.SUCCESS;
        }

        if (stack.is(Items.BUCKET) && !this.isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack itemstack1 = ItemUtils.createFilledResult(stack, player, Items.MILK_BUCKET.getDefaultInstance());
            player.setItemInHand(hand, itemstack1);
            //setTarget(player);
            setPersistentAngerTarget(player.getUUID());
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (isSprinting() && level() instanceof ServerLevel serverLevel && getDeltaMovement() != Vec3.ZERO) {
            for (int i = 0; i < 10; i++) {

                Vec3 pos = getYawVec(yBodyRot, 0.0D, -1.5D).add(position());
                serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.x(), pos.y() + 0.2F, pos.z(), 0, 0.0D, 0.0D, 0.0D, 0.0D);
            }
        }
        setSprinting(isAggressive() && !isSprinting());
    }

    private static Vec3 getYawVec(float yaw, double xOffset, double zOffset) {
        return new Vec3(xOffset, 0, zOffset).yRot(-yaw * ((float) Math.PI / 180f));
    }

    @Nullable
    @Override
    public OxEntity getBreedOffspring(ServerLevel level, AgeableMob p_148891_) {
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
        this.entityData.define(REMAINING_ANGER_TIME, 0);
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
        readPersistentAngerSaveData(level(), compound);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
        addPersistentAngerSaveData(compound);
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


    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_21673_) {
        this.entityData.set(REMAINING_ANGER_TIME, p_21673_);
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID p_27791_) {
        this.persistentAngerTarget = p_27791_;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }
}
