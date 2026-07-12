package net.mcreator.havahsadventure.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;

import net.mcreator.havahsadventure.procedures.FinelKeyItemIsCraftedsmeltedProcedure;

public class FinelKeyItem extends Item {
	public FinelKeyItem() {
		super(new Item.Properties().rarity(Rarity.EPIC));
	}

	@Override
	public void onCraftedBy(ItemStack itemstack, Level world, Player entity) {
		super.onCraftedBy(itemstack, world, entity);
		FinelKeyItemIsCraftedsmeltedProcedure.execute(entity);
	}
}