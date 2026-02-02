package me.opus.client.module.modules;

import me.opus.client.module.Category;
import me.opus.client.module.Module;
import org.lwjgl.glfw.GLFW;

public class FullBright extends Module {
    
    private double oldGamma;
    
    public FullBright() {
        super("FullBright", "See in the dark", Category.RENDER, GLFW.GLFW_KEY_B);
    }
    
    @Override
    public void onEnable() {
        oldGamma = mc.options.getGamma().getValue();
        mc.options.getGamma().setValue(16.0);
    }
    
    @Override
    public void onDisable() {
        mc.options.getGamma().setValue(oldGamma);
    }
    
    @Override
    public void onTick() {
        if (mc.options.getGamma().getValue() != 16.0) {
            mc.options.getGamma().setValue(16.0);
        }
    }
}
