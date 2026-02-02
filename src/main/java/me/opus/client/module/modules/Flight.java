package me.opus.client.module.modules;

import me.opus.client.module.Category;
import me.opus.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class Flight extends Module {
    
    private float flySpeed = 0.5f;
    
    public Flight() {
        super("Flight", "Allows you to fly", Category.MOVEMENT, GLFW.GLFW_KEY_G);
    }
    
    @Override
    public void onEnable() {
        if (mc.player != null) {
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().setFlySpeed(flySpeed);
        }
    }
    
    @Override
    public void onDisable() {
        if (mc.player != null && !mc.player.isCreative()) {
            mc.player.getAbilities().flying = false;
            mc.player.getAbilities().setFlySpeed(0.05f);
        }
    }
    
    @Override
    public void onTick() {
        if (mc.player != null) {
            mc.player.getAbilities().flying = true;
            
            // Вверх/вниз
            if (mc.options.jumpKey.isPressed()) {
                mc.player.setVelocity(mc.player.getVelocity().add(0, flySpeed, 0));
            }
            if (mc.options.sneakKey.isPressed()) {
                mc.player.setVelocity(mc.player.getVelocity().add(0, -flySpeed, 0));
            }
        }
    }
}
