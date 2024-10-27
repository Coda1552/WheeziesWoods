package codyhuh.wheezieswoods.client;

import codyhuh.wheezieswoods.WheeziesWoods;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {

    public static final ModelLayerLocation OX_LAYER = create("ox");
    public static final ModelLayerLocation COVERED_WAGON_LAYER = create("covered_wagon");
    public static final ModelLayerLocation ASPEN_BOAT_LAYER = create("boat/aspen");
    public static final ModelLayerLocation ASPEN_CHEST_BOAT_LAYER = create("chest_boat/aspen");

    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(WheeziesWoods.MOD_ID, name), "main");
    }
}
