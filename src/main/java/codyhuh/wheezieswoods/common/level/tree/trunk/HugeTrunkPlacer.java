package codyhuh.wheezieswoods.common.level.tree.trunk;

import codyhuh.wheezieswoods.core.registry.ModTrunkPlacers;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class HugeTrunkPlacer extends TrunkPlacer {
    public static final Codec<HugeTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70090_) -> {
        return trunkPlacerParts(p_70090_).apply(p_70090_, HugeTrunkPlacer::new);
    });

    public HugeTrunkPlacer(int base, int randA, int randB) {
        super(base, randA, randB);
    }

    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacers.HUGE_TRUNK_PLACER.get();
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> entry, RandomSource rand, int height, BlockPos pos, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        BlockPos blockpos = pos.below();
        setDirtAt(level, entry, rand, blockpos, config);
        setDirtAt(level, entry, rand, blockpos.east(), config);
        setDirtAt(level, entry, rand, blockpos.south(), config);
        setDirtAt(level, entry, rand, blockpos.south().east(), config);
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
        int i = height - rand.nextInt(4);
        int j = 2 - rand.nextInt(3);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int j1 = x;
        int k1 = z;
        int l1 = y + height - 1;

        for(int i2 = 0; i2 < height; ++i2) {
            if (i2 >= i && j > 0) {
                j1 += direction.getStepX();
                k1 += direction.getStepZ();
                --j;
            }

            int j2 = y + i2;
            BlockPos blockpos1 = new BlockPos(j1, j2, k1);
            if (TreeFeature.isAirOrLeaves(level, blockpos1)) {
                for (int k = -4; k < 4; k++) {
                    for (int l = -4; l < 4; l++) {
                        this.placeLog(level, entry, rand, blockpos1, config);
                        this.placeLog(level, entry, rand, blockpos1.east(k), config);
                        this.placeLog(level, entry, rand, blockpos1.south(l), config);
                        this.placeLog(level, entry, rand, blockpos1.east(k).south(l), config);
                    }
                }
            }
        }

        list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(j1, l1, k1), 0, true));

        for(int l2 = -1; l2 <= 2; ++l2) {
            for(int i3 = -1; i3 <= 2; ++i3) {
                if ((l2 < 0 || l2 > 1 || i3 < 0 || i3 > 1) && rand.nextInt(3) <= 0) {
                    int j3 = rand.nextInt(3) + 2;

                    for(int k2 = 0; k2 < j3; ++k2) {
                        this.placeLog(level, entry, rand, new BlockPos(x + l2, l1 - k2 - 1, z + i3), config);
                    }

                    list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(j1 + l2, l1, k1 + i3), 0, false));
                }
            }
        }

        return list;
    }
}