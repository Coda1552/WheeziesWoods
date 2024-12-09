package codyhuh.wheezieswoods.core.registry;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.common.level.tree.trunk.HugeTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacers {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, WheeziesWoods.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<?>> HUGE_TRUNK_PLACER = TRUNK_PLACERS.register("huge", () -> new TrunkPlacerType<>(HugeTrunkPlacer.CODEC));
}
