package me.opus.client;

import me.opus.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpusClient implements ClientModInitializer {
    public static final String NAME = "OpusClient";
    public static final String VERSION = "1.0.0";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
    
    public static OpusClient INSTANCE;
    public static MinecraftClient mc;
    private ModuleManager moduleManager;
    
    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        mc = MinecraftClient.getInstance();
        
        LOGGER.info("Loading " + NAME + " v" + VERSION);
        
        moduleManager = new ModuleManager();
        
        LOGGER.info(NAME + " loaded successfully!");
    }
    
    public ModuleManager getModuleManager() {
        return moduleManager;
    }
    
    public static OpusClient getInstance() {
        return INSTANCE;
    }
}
