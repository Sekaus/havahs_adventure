/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.havahsadventure.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.havahsadventure.HavahsAdventureMod;

@EventBusSubscriber
public class HavahsAdventureModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HavahsAdventureMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> HAVAHS_ADVENTURE = REGISTRY.register("havahs_adventure",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.havahs_adventure.havahs_adventure")).icon(() -> new ItemStack(HavahsAdventureModItems.FINEL_KEY.get())).displayItems((parameters, tabData) -> {
				tabData.accept(HavahsAdventureModBlocks.ABOVE_BLOCK.get().asItem());
				tabData.accept(HavahsAdventureModItems.FINEL_KEY.get());
				tabData.accept(HavahsAdventureModBlocks.ABOVE_BRICK_BLOCK.get().asItem());
				tabData.accept(HavahsAdventureModBlocks.LIGHT_CRYSTAL_BLOCK.get().asItem());
				tabData.accept(HavahsAdventureModItems.LIGHT_CRYSTAL.get());
				tabData.accept(HavahsAdventureModBlocks.ABOVE_BRICK_STAIR_BLOCK.get().asItem());
				tabData.accept(HavahsAdventureModBlocks.ABOVE_BRICK_SLAB_BLOCK.get().asItem());
				tabData.accept(HavahsAdventureModItems.ABOVE.get());
				tabData.accept(HavahsAdventureModItems.PEGASUS_SPAWN_EGG.get());
				tabData.accept(HavahsAdventureModItems.RAW_PEGASUS_MEAT.get());
				tabData.accept(HavahsAdventureModItems.COOKED_PEGASUS_MEAT.get());
				tabData.accept(HavahsAdventureModBlocks.SOFT_ABOVE_BLOCK.get().asItem());
				tabData.accept(HavahsAdventureModItems.GUARD_SPAWN_EGG.get());
				tabData.accept(HavahsAdventureModItems.EYE_BANNER_PATTERN.get());
				tabData.accept(HavahsAdventureModBlocks.ABOVE_SAND.get().asItem());
				tabData.accept(HavahsAdventureModBlocks.ABOVE_GRASS_BLOCK.get().asItem());
				tabData.accept(HavahsAdventureModBlocks.ABOVE_DIRT.get().asItem());
				tabData.accept(HavahsAdventureModBlocks.SKYROOT.get().asItem());
				tabData.accept(HavahsAdventureModItems.SKYROOT_FRUIT.get());
				tabData.accept(HavahsAdventureModBlocks.LIGHT_SPIRAL.get().asItem());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(HavahsAdventureModBlocks.ABOVE_BLOCK.get().asItem());
			tabData.accept(HavahsAdventureModBlocks.LIGHT_CRYSTAL_BLOCK.get().asItem());
			tabData.accept(HavahsAdventureModBlocks.SOFT_ABOVE_BLOCK.get().asItem());
			tabData.accept(HavahsAdventureModBlocks.ABOVE_SAND.get().asItem());
			tabData.accept(HavahsAdventureModBlocks.ABOVE_GRASS_BLOCK.get().asItem());
			tabData.accept(HavahsAdventureModBlocks.ABOVE_DIRT.get().asItem());
			tabData.accept(HavahsAdventureModBlocks.SKYROOT.get().asItem());
			tabData.accept(HavahsAdventureModBlocks.LIGHT_SPIRAL.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(HavahsAdventureModBlocks.ABOVE_BRICK_BLOCK.get().asItem());
			tabData.accept(HavahsAdventureModBlocks.ABOVE_BRICK_STAIR_BLOCK.get().asItem());
			tabData.accept(HavahsAdventureModBlocks.ABOVE_BRICK_SLAB_BLOCK.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			tabData.accept(HavahsAdventureModBlocks.LIGHT_CRYSTAL_BLOCK.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			tabData.accept(HavahsAdventureModItems.LIGHT_CRYSTAL.get());
			tabData.accept(HavahsAdventureModItems.EYE_BANNER_PATTERN.get());
			tabData.accept(HavahsAdventureModItems.SKYROOT_FRUIT.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(HavahsAdventureModItems.ABOVE.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(HavahsAdventureModItems.PEGASUS_SPAWN_EGG.get());
			tabData.accept(HavahsAdventureModItems.GUARD_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			tabData.accept(HavahsAdventureModItems.RAW_PEGASUS_MEAT.get());
			tabData.accept(HavahsAdventureModItems.COOKED_PEGASUS_MEAT.get());
			tabData.accept(HavahsAdventureModItems.SKYROOT_FRUIT.get());
		}
	}
}