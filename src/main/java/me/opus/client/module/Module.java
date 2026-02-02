package me.opus.client.module;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public abstract class Module {
    protected static final MinecraftClient mc = MinecraftClient.getInstance();
    
    private final String name;
    private final String description;
    private final Category category;
    private int keyBind;
    private boolean enabled;
    
    public Module(String name, String description, Category category, int keyBind) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.keyBind = keyBind;
        this.enabled = false;
    }
    
    public void toggle() {
        if (enabled) {
            disable();
        } else {
            enable();
        }
    }
    
    public void enable() {
        enabled = true;
        onEnable();
    }
    
    public void disable() {
        enabled = false;
        onDisable();
    }
    
    public abstract void onEnable();
    public abstract void onDisable();
    public void onTick() {}
    public void onRender() {}
    
    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Category getCategory() { return category; }
    public int getKeyBind() { return keyBind; }
    public boolean isEnabled() { return enabled; }
    public void setKeyBind(int key) { this.keyBind = key; }
}
