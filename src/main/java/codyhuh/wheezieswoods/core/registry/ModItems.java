package codyhuh.wheezieswoods.core.registry;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.common.entity.ModBoatEntity;
import codyhuh.wheezieswoods.common.item.ModBoatItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WheeziesWoods.MOD_ID);


    public static final RegistryObject<Item> ASPEN_SIGN = ITEMS.register("aspen_sign",
            ()-> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.ASPEN_SIGN.get(), ModBlocks.ASPEN_WALL_SIGN.get()));

    public static final RegistryObject<Item> ASPEN_BOAT = ITEMS.register("aspen_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.ASPEN, new Item.Properties()));

    public static final RegistryObject<Item> ASPEN_CHEST_BOAT = ITEMS.register("aspen_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.ASPEN, new Item.Properties()));

    public static final RegistryObject<Item> ASPEN_HANGING_SIGN = ITEMS.register("aspen_hanging_sign",
            ()-> new HangingSignItem(ModBlocks.ASPEN_HANGING_SIGN.get(), ModBlocks.ASPEN_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));

}