package codyhuh.wheezieswoods.core.registry;

import codyhuh.wheezieswoods.WheeziesWoods;
import codyhuh.wheezieswoods.common.entity.CoveredWagonEntity;
import codyhuh.wheezieswoods.common.entity.ModBoatEntity;
import codyhuh.wheezieswoods.common.entity.ModChestBoatEntity;
import codyhuh.wheezieswoods.common.entity.OxEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WheeziesWoods.MOD_ID);

    public static final RegistryObject<EntityType<OxEntity>> OX =
            ENTITY_TYPES.register("ox", () -> EntityType.Builder.of(OxEntity::new, MobCategory.CREATURE)
                    .sized(1.25f, 1.35f).build("ox"));

    public static final RegistryObject<EntityType<CoveredWagonEntity>> COVERED_WAGON =
            ENTITY_TYPES.register("covered_wagon", () -> EntityType.Builder.<CoveredWagonEntity>of(CoveredWagonEntity::new, MobCategory.MISC)
                    .sized(2.5f, 2.5f).build("covered_wagon"));

    public static final RegistryObject<EntityType<ModBoatEntity>> MOD_BOAT =
            ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_boat"));

    public static final RegistryObject<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =
            ENTITY_TYPES.register("mod_chest_boat", () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_chest_boat"));

}
