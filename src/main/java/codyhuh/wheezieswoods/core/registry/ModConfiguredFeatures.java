package codyhuh.wheezieswoods.core.registry;

import codyhuh.wheezieswoods.WheeziesWoods;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class ModConfiguredFeatures {
  public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_TREE = createKey("aspen_tree");
  public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_GROVE_FLOWERS = createKey("aspen_grove_flowers");

  public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
    register(context, ASPEN_TREE, Feature.TREE, TreeFeatureConfigs.ASPEN);
    register(context, ASPEN_GROVE_FLOWERS, Feature.FLOWER, RandomPatchFeatureConfigs.ASPEN_GROVE_FLOWERS);
  }

  private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
    return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(WheeziesWoods.MOD_ID, name));
  }

  private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
    context.register(key, new ConfiguredFeature<>(feature, config));
  }

  private static class TreeFeatureConfigs {
    public static final TreeConfiguration ASPEN = tree(new StraightTrunkPlacer(6, 2, 2), new RandomSpreadFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), UniformInt.of(4, 5), 200), ModBlocks.ASPEN_LOG.get(), ModBlocks.ASPEN_LEAVES.get(), new TwoLayersFeatureSize(2, 1, 2));

    private static TreeConfiguration tree(TrunkPlacer trunkPlacer, FoliagePlacer foliagePlacer, Block log, Block leaves, FeatureSize featureSize) {
      return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), trunkPlacer,
              BlockStateProvider.simple(leaves),
              foliagePlacer, featureSize).ignoreVines().decorators(ImmutableList.of(new BeehiveDecorator(0.025F))).build();
    }
  }

  private static class RandomPatchFeatureConfigs {
    public static final RandomPatchConfiguration ASPEN_GROVE_FLOWERS = new RandomPatchConfiguration(96, 6, 2,PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(5550L, new NormalNoise.NoiseParameters(0, 1.0D), 0.020833334F, List.of(Blocks.DANDELION.defaultBlockState(), Blocks.AZURE_BLUET.defaultBlockState(), Blocks.OXEYE_DAISY.defaultBlockState())))));
  }
}
