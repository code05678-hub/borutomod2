package com.borutomod;

import com.borutomod.item.ChakraScrollItem;
import com.borutomod.item.RasenganItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Block CHAKRA_CRYSTAL_BLOCK = new Block(
            AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).luminance(s -> 8));

    public static final Item CHAKRA_CRYSTAL_BLOCK_ITEM = new BlockItem(CHAKRA_CRYSTAL_BLOCK, new Item.Settings());
    public static final Item KUNAI = new SwordItem(ToolMaterials.IRON, 1, -1.2f, new Item.Settings());
    public static final Item RASENGAN = new RasenganItem(new Item.Settings().maxCount(1));
    public static final Item CHAKRA_SCROLL = new ChakraScrollItem(new Item.Settings().maxCount(1));

    public static final ItemGroup TAB = FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.borutomod"))
            .icon(() -> new ItemStack(RASENGAN))
            .entries((ctx, entries) -> {
                entries.add(KUNAI);
                entries.add(RASENGAN);
                entries.add(CHAKRA_SCROLL);
                entries.add(CHAKRA_CRYSTAL_BLOCK_ITEM);
            })
            .build();

    private static Identifier id(String path) {
        return new Identifier(BorutoMod.MOD_ID, path);
    }

    public static void register() {
        Registry.register(Registries.BLOCK, id("chakra_crystal_block"), CHAKRA_CRYSTAL_BLOCK);
        Registry.register(Registries.ITEM, id("chakra_crystal_block"), CHAKRA_CRYSTAL_BLOCK_ITEM);
        Registry.register(Registries.ITEM, id("kunai"), KUNAI);
        Registry.register(Registries.ITEM, id("rasengan"), RASENGAN);
        Registry.register(Registries.ITEM, id("chakra_scroll"), CHAKRA_SCROLL);
        Registry.register(Registries.ITEM_GROUP, id("main"), TAB);
    }
}
