package codyhuh.wheezieswoods.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;

public class OxEntity extends Cow {

    public OxEntity(EntityType<? extends Cow> p_28285_, Level p_28286_) {
        super(p_28285_, p_28286_);
    }

    public static AttributeSupplier.Builder createOxAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.22F);
    }

    public boolean isDaisyCovered() {
        return false;
    }

}
