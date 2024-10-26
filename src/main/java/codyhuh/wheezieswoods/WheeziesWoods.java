package codyhuh.wheezieswoods;

import codyhuh.wheezieswoods.common.blocks.entity.ModBlockEntities;
import codyhuh.wheezieswoods.core.registry.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WheeziesWoods.MOD_ID)
public class WheeziesWoods {
    public static final String MOD_ID = "wheezieswoods";

    public WheeziesWoods() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModTabs.CREATIVE_TABS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
    }

}