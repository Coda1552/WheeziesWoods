package codyhuh.wheezieswoods.core.data;

import codyhuh.wheezieswoods.WheeziesWoods;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = WheeziesWoods.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new ModBlockStateGenerator(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ItemModelGenerator(packOutput, existingFileHelper));

        BlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new BlockTagGenerator(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(),new ItemTagGenerator(packOutput, lookupProvider,
                blockTagGenerator.contentsGetter(), existingFileHelper));

//        generator.addProvider(true, new ModEntityTagsGenerator(packOutput, lookupProvider, existingFileHelper));
//
//        generator.addProvider(true, new ModBiomeTagGenerator(packOutput, lookupProvider, existingFileHelper));
//
//        generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));
    }

}
