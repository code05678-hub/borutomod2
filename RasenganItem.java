package com.borutomod.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RasenganItem extends Item {
    public RasenganItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            Vec3d look = user.getRotationVec(1.0f);
            Box box = user.getBoundingBox().stretch(look.multiply(3.0)).expand(1.0);
            for (LivingEntity target : world.getEntitiesByClass(LivingEntity.class, box, e -> e != user)) {
                target.damage(world.getDamageSources().playerAttack(user), 8.0f);
                target.takeKnockback(1.5, -look.x, -look.z);
            }
            Vec3d p = user.getEyePos().add(look.multiply(1.5));
            ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD, p.x, p.y, p.z, 40, 0.4, 0.4, 0.4, 0.15);
            ((ServerWorld) world).spawnParticles(ParticleTypes.SWEEP_ATTACK, p.x, p.y, p.z, 3, 0.3, 0.3, 0.3, 0.0);
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.6f, 1.6f);
            user.addExhaustion(6.0f);
        }
        user.getItemCooldownManager().set(this, 60);
        return TypedActionResult.success(stack, world.isClient());
    }
}
