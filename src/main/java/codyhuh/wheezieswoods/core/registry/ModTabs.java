package codyhuh.wheezieswoods.core.registry;

import codyhuh.wheezieswoods.WheeziesWoods;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WheeziesWoods.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_TABS.register("wheezies_woods_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + WheeziesWoods.MOD_ID))
            .icon(ModBlocks.ASPEN_SAPLING.get().asItem()::getDefaultInstance)
            .displayItems((itemDisplayParameters, output) -> {
                ModItems.ITEMS.getEntries().forEach(itemRegistryObject -> output.accept(itemRegistryObject.get()));
            })
            .build()
    );

}
