/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.havahsadventure.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.Registries;

import net.mcreator.havahsadventure.entity.PegasusEntity;
import net.mcreator.havahsadventure.entity.GuardEntity;
import net.mcreator.havahsadventure.entity.GuardAttackEntity;
import net.mcreator.havahsadventure.HavahsAdventureMod;

@EventBusSubscriber
public class HavahsAdventureModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, HavahsAdventureMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<PegasusEntity>> PEGASUS = register("pegasus",
			EntityType.Builder.<PegasusEntity>of(PegasusEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<GuardEntity>> GUARD = register("guard",
			EntityType.Builder.<GuardEntity>of(GuardEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<GuardAttackEntity>> GUARD_ATTACK = register("guard_attack",
			EntityType.Builder.<GuardAttackEntity>of(GuardAttackEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.5f, 0.5f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		PegasusEntity.init(event);
		GuardEntity.init(event);
		GuardAttackEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(PEGASUS.get(), PegasusEntity.createAttributes().build());
		event.put(GUARD.get(), GuardEntity.createAttributes().build());
		event.put(GUARD_ATTACK.get(), GuardAttackEntity.createAttributes().build());
	}
}