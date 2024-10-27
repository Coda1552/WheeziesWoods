package codyhuh.wheezieswoods.common.entity.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;

public class ChargeBannerGoal extends MoveToBlockGoal {
    private final PathfinderMob mob;

    public ChargeBannerGoal(PathfinderMob goalOwner, double speed, int searchRange) {
        super(goalOwner, speed, searchRange);
        this.mob = goalOwner;
    }

    @Override
    public void start() {
        super.start();
        mob.setSprinting(true);
        mob.setAggressive(true);
    }

    @Override
    public void tick() {
        super.tick();

        if (isReachedTarget()) {
            mob.playSound(SoundEvents.RAVAGER_ROAR);
            stop();
        }
    }

    @Override
    public void stop() {
        super.stop();
        mob.setSprinting(false);
        mob.setAggressive(false);
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.RED_BANNER);
    }
}
