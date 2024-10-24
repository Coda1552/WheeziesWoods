package codyhuh.wheezieswoods.core.registry;

import codyhuh.wheezieswoods.WheeziesWoods;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ModFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(WheeziesWoods.MOD_ID, "aspen"));

}
