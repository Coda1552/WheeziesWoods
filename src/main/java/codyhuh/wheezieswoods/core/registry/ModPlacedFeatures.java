package codyhuh.wheezieswoods.core.registry;

import codyhuh.wheezieswoods.WheeziesWoods;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
  public static final ResourceKey<PlacedFeature> TREES_ASPEN = createKey("trees_aspen");
  public static final ResourceKey<PlacedFeature> PATCH_TALL_GRASS = createKey("patch_tall_grass");
  public static final ResourceKey<PlacedFeature> PATCH_FLOWERS_ASPEN = createKey("patch_flowers_aspen");
  public static final ResourceKey<PlacedFeature> HUGE_MUSHROOM_ASPEN = createKey("huge_mushroom_aspen");

  public static void bootstrap(BootstapContext<PlacedFeature> context) {
    register(context, TREES_ASPEN, ModConfiguredFeatures.ASPEN_TREE, VegetationPlacements.treePlacement(CountPlacement.of(25), ModBlocks.ASPEN_SAPLING.get()));
    register(context, PATCH_TALL_GRASS, VegetationFeatures.PATCH_TALL_GRASS, CountPlacement.of(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
    register(context, PATCH_FLOWERS_ASPEN, ModConfiguredFeatures.ASPEN_GROVE_FLOWERS, CountPlacement.of(3), RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    register(context, HUGE_MUSHROOM_ASPEN, TreeFeatures.HUGE_BROWN_MUSHROOM, CountPlacement.of(1), RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
  }

  private static ResourceKey<PlacedFeature> createKey(String name) {
    return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(WheeziesWoods.MOD_ID, name));
  }

  private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers) {
    HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
    PlacementUtils.register(context, key, holderGetter.getOrThrow(configuredFeature), modifiers);
  }

  private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers) {
    HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
    PlacementUtils.register(context, key, holderGetter.getOrThrow(configuredFeature), modifiers);
  }
}