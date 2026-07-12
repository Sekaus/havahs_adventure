package net.mcreator.havahsadventure.procedures;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;

import net.mcreator.havahsadventure.entity.PegasusEntity;

@EventBusSubscriber
public class PegasusRightclickedOnEntityProcedure {

    @SubscribeEvent(priority = net.neoforged.bus.api.EventPriority.HIGH)
    public static void onRightClick(PlayerInteractEvent.EntityInteract event) {
        Entity entity = event.getTarget();
        Player sourceentity = event.getEntity();

        if (!(entity instanceof PegasusEntity pegasus)) return;

        ItemStack selectedItem = sourceentity.getItemInHand(event.getHand());
        boolean hasSaddle = pegasus.getEntityData().get(PegasusEntity.DATA_has_a_saddle_on);

        // 1. FEEDING & BREEDING MECHANISM
        if (selectedItem.is(Items.APPLE) || selectedItem.is(Items.GOLDEN_APPLE)) {
            // Heal the mob
            pegasus.heal(2.0f);
            
            // 1. FEEDING MECHANISM (Adults breed, Babies grow)
	        if (selectedItem.is(Items.APPLE) || selectedItem.is(Items.GOLDEN_APPLE)) {
	            pegasus.heal(2.0f);
	            
	            if (pegasus.isBaby()) {
	                // Reduce age by 10% (Vanilla standard for horses)
	                pegasus.ageUp((int)((float)(-pegasus.getAge() / 20) * 0.1F), true);
	                
	                // Spawn "Happy" green particles for growth
	                if (pegasus.level() instanceof ServerLevel _level) {
	                    _level.sendParticles(ParticleTypes.HAPPY_VILLAGER, 
	                        pegasus.getX(), pegasus.getY() + 0.5, pegasus.getZ(), 
	                        5, 0.2, 0.2, 0.2, 0.05);
	                }
	            } else if (pegasus.getInLoveTime() <= 0) {
	                // Adult breeding logic
	                pegasus.setInLove(sourceentity);
	                if (pegasus.level() instanceof ServerLevel _level) {
	                    _level.sendParticles(ParticleTypes.HEART, 
	                        pegasus.getX(), pegasus.getY() + 0.5, pegasus.getZ(), 
	                        7, 0.3, 0.3, 0.3, 0.05);
	                }
	            }

            	if (!sourceentity.getAbilities().instabuild) selectedItem.shrink(1);
            
            	event.setCancellationResult(InteractionResult.SUCCESS);
            	event.setCanceled(true); 
            	return;
        	}
        }

        // 2. SADDLING MECHANISM
        if (selectedItem.is(Items.SADDLE) && !hasSaddle && !pegasus.isBaby()) {
            pegasus.getEntityData().set(PegasusEntity.DATA_has_a_saddle_on, true);
            if (!sourceentity.getAbilities().instabuild) selectedItem.shrink(1);
            
            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true); 
            return;
        }

        // 3. REMOVE SADDLE (Sneak + Empty Hand)
        if (selectedItem.isEmpty() && sourceentity.isShiftKeyDown() && hasSaddle) {
            ItemStack saddleStack = new ItemStack(Items.SADDLE);
            
            // This tries to put it in the inventory first; if full, it drops it at the player's feet
            net.neoforged.neoforge.items.ItemHandlerHelper.giveItemToPlayer(sourceentity, saddleStack);
            
            pegasus.getEntityData().set(PegasusEntity.DATA_has_a_saddle_on, false);
            
            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true); 
            return;
        }

        // 4. RIDE RESTRICTION
        if (!hasSaddle) {
            
            // Check if the player is holding a Lead OR if the mob is already leashed
            boolean holdingLead = selectedItem.is(Items.LEAD);
            boolean isLeashed = pegasus.getLeashHolder() == sourceentity;

            if (holdingLead || isLeashed) {
                // Return PASS to let Vanilla handle attaching or removing the lead
                return; 
            }

            // Only show message if not sneaking and not interacting with leads
            if (pegasus.isBaby()) {
        		sourceentity.displayClientMessage(Component.literal("§cIt's too small to ride"), true);
        	}
            else if (!sourceentity.isShiftKeyDown()) {
                sourceentity.displayClientMessage(Component.literal("§cYou can't ride without a saddle"), true);
            }
            
            event.setCancellationResult(InteractionResult.FAIL);
            event.setCanceled(true); 
        } 
    }
}