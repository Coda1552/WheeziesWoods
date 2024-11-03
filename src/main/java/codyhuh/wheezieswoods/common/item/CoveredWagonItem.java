package codyhuh.wheezieswoods.common.item;

import codyhuh.wheezieswoods.common.entity.CoveredWagonEntity;
import codyhuh.wheezieswoods.common.entity.ModBoatEntity;
import codyhuh.wheezieswoods.common.entity.ModChestBoatEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class CoveredWagonItem extends Item {

    public CoveredWagonItem(Properties pProperties) {
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        HitResult hitresult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.ANY);
        if (hitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            if (hitresult.getType() == HitResult.Type.BLOCK) {
                CoveredWagonEntity wagon = this.newWagon(pLevel, hitresult);

                wagon.setYRot(pPlayer.getYRot());
                wagon.moveTo(hitresult.getLocation().x, hitresult.getLocation().y, hitresult.getLocation().z);

                if (!pLevel.noCollision(wagon, wagon.getBoundingBox())) {
                    return InteractionResultHolder.fail(itemstack);
                }
                else {
                    if (!pLevel.isClientSide) {
                        pLevel.addFreshEntity(wagon);
                        pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, hitresult.getLocation());
                        if (!pPlayer.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                    }

                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
                }
            } else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }

    private CoveredWagonEntity newWagon(Level p_220017_, HitResult p_220018_) {
        return new CoveredWagonEntity(p_220017_, p_220018_.getLocation().x, p_220018_.getLocation().y, p_220018_.getLocation().z);
    }
}
