package com.darkwolfie.silentvalkyrie.datagen;

import com.darkwolfie.silentvalkyrie.Main;
import com.darkwolfie.silentvalkyrie.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Main.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.TestKey);
        simpleItem(ModItems.PlutoSteel);
        simpleItem(ModItems.MOLTEN_PLUTOSTEEL);
        simpleItem(ModItems.HARDENED_PLUTOSTEEL);
        simpleItem(ModItems.valkyrium);
        simpleItem(ModItems.MOLTEN_valkyrium);
        simpleItem(ModItems.HARDENED_valkyrium);


    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
        new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Main.MODID,"item/" + item.getId().getPath()));

    }
}
