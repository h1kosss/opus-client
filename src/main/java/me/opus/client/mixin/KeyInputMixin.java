package me.opus.client.mixin;

import me.opus.client.OpusClient;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyInputMixin {
    
    @Shadow @Final private MinecraftClient client;
    
    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        // Только при нажатии клавиши (не отпускании)
        if (action != GLFW.GLFW_PRESS) return;
        
        // Не обрабатывать если открыт чат или меню
        if (client.currentScreen != null) return;
        
        // Передаём нажатие в ModuleManager
        if (OpusClient.INSTANCE != null && OpusClient.INSTANCE.getModuleManager() != null) {
            OpusClient.INSTANCE.getModuleManager().onKey(key);
        }
    }
}
