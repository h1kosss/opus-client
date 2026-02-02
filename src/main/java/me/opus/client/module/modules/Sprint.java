package me.opus.client.module.modules;

import me.opus.client.module.Category;
import me.opus.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class Sprint extends Module {
    
    public Sprint() {
        super("Sprint", "Automatically sprints", Category.MOVEMENT, GLFW.GLFW_KEY_V);
    }
    
    @Override
    public void onEnable() {
        // Сообщение при включении
    }
    
    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.player.setSprinting(false);
        }
    }
    
    @Override
    public void onTick() {
        if (mc.player != null && mc.player.forwardSpeed > 0) {
            mc.player.setSprinting(true);
        }
    }
}
