package codyhuh.wheezieswoods;

import codyhuh.wheezieswoods.common.blocks.entity.ModBlockEntities;
import codyhuh.wheezieswoods.core.data.server.ModModdedBiomeSlices;
import codyhuh.wheezieswoods.core.registry.*;
import codyhuh.wheezieswoods.core.util.ModWoodTypes;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod(WheeziesWoods.MOD_ID)
public class WheeziesWoods {
    public static final String MOD_ID = "wheezieswoods";

    public WheeziesWoods() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModTabs.CREATIVE_TABS.register(bus);
        ModBlockEntities.register(bus);
        bus.addListener(this::dataSetup);
        bus.addListener(this::commonSetup);
    }

    private void dataSetup(final GatherDataEvent event) {
        final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
                .add(Registries.BIOME, ModBiomes::bootstrap)
                .add(BlueprintDataPackRegistries.MODDED_BIOME_SLICES, ModModdedBiomeSlices::bootstrap);
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        boolean server = event.includeServer();

        DatapackBuiltinEntriesProvider datapackBuiltinEntriesProvider = new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, BUILDER, Set.of(MOD_ID));
        generator.addProvider(server, datapackBuiltinEntriesProvider);
        //generator.addProvider(server, new ModChunkGeneratorModifierProvider(packOutput, lookupProvider));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()->{
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.ASPEN_SAPLING.getId(), ModBlocks.POTTED_ASPEN_SAPLING);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.ASPEN_SAPLING.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.ASPEN_LEAVES.get().asItem(), 0.4F);
        });
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Sheets.addWoodType(ModWoodTypes.ASPEN);
        }
    }
}
