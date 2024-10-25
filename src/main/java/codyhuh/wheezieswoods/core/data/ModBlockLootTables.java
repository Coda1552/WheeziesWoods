package codyhuh.wheezieswoods.core.data;

import codyhuh.wheezieswoods.core.registry.ModBlocks;
import codyhuh.wheezieswoods.core.registry.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    //keep an eye on this, was asked to make public but was protected
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ASPEN_PLANKS.get());
        this.dropSelf(ModBlocks.ASPEN_STAIRS.get());
        this.dropSelf(ModBlocks.ASPEN_FENCE.get());
        this.dropSelf(ModBlocks.ASPEN_FENCE_GATE.get());
        this.dropSelf(ModBlocks.ASPEN_BUTTON.get());
        this.dropSelf(ModBlocks.ASPEN_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.ASPEN_TRAPDOOR.get());

        this.add(ModBlocks.ASPEN_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.ASPEN_SLAB.get()));

        this.add(ModBlocks.ASPEN_DOOR.get(),
                block -> createDoorTable(ModBlocks.ASPEN_DOOR.get()));

        this.add(ModBlocks.ASPEN_SIGN.get(),
                block -> createSingleItemTable(ModItems.ASPEN_SIGN.get()));
        this.add(ModBlocks.ASPEN_WALL_SIGN.get(),
                block -> createSingleItemTable(ModItems.ASPEN_SIGN.get()));
        this.add(ModBlocks.ASPEN_HANGING_SIGN.get(),
                block -> createSingleItemTable(ModItems.ASPEN_HANGING_SIGN.get()));
        this.add(ModBlocks.ASPEN_WALL_HANGING_SIGN.get(),
                block -> createSingleItemTable(ModItems.ASPEN_HANGING_SIGN.get()));

        this.dropSelf(ModBlocks.ASPEN_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_ASPEN_LOG.get());
        this.dropSelf(ModBlocks.ASPEN_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_ASPEN_WOOD.get());

        this.dropSelf(ModBlocks.ASPEN_SAPLING.get());
        this.add(ModBlocks.POTTED_ASPEN_SAPLING.get(),
                createPotFlowerItemTable(ModBlocks.ASPEN_SAPLING.get()));

        this.add(ModBlocks.ASPEN_LEAVES.get(), (p_280937_) -> {
            return this.createLeavesDrops(p_280937_, ModBlocks.ASPEN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        });

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

}
