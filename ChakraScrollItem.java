package com.borutomod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ChakraScrollItem extends Item {
    public ChakraScrollItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 30, 2));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 30, 2));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20 * 30, 0));
            ((ServerWorld) world).spawnParticles(ParticleTypes.SOUL_FIRE_FLAME,
                    user.getX(), user.getY() + 1.0, user.getZ(), 30, 0.5, 0.8, 0.5, 0.05);
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1.0f, 1.5f);
            user.addExhaustion(4.0f);
        }
        user.getItemCooldownManager().set(this, 20 * 120);
        return TypedActionResult.success(stack, world.isClient());
    }
}
