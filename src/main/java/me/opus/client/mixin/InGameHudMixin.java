package me.opus.client.mixin;

import me.opus.client.OpusClient;
import me.opus.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    
    @Shadow @Final private MinecraftClient client;
    
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (client.options.debugEnabled) return;
        if (OpusClient.INSTANCE == null) return;
        
        TextRenderer textRenderer = client.textRenderer;
        
        // === Название клиента ===
        String title = OpusClient.NAME + " §7v" + OpusClient.VERSION;
        context.drawTextWithShadow(textRenderer, title, 4, 4, 0xFF55FF);
        
        // === FPS ===
        String fps = "FPS: " + client.getCurrentFps();
        context.drawTextWithShadow(textRenderer, fps, 4, 16, 0xAAAAAA);
        
        // === Список включённых модулей (ArrayList) ===
        if (OpusClient.INSTANCE.getModuleManager() != null) {
            List<Module> enabled = OpusClient.INSTANCE.getModuleManager().getEnabledModules();
            
            int screenWidth = client.getWindow().getScaledWidth();
            int y = 4;
            
            for (Module module : enabled) {
                String name = module.getName();
                int width = textRenderer.getWidth(name);
                int x = screenWidth - width - 4;
                
                // Фон
                context.fill(x - 2, y - 1, screenWidth, y + 9, 0x90000000);
                
                // Цветная полоска справа
                context.fill(screenWidth - 2, y - 1, screenWidth, y + 9, getCategoryColor(module));
                
                // Текст
                context.drawTextWithShadow(textRenderer, name, x, y, 0xFFFFFF);
                
                y += 11;
            }
        }
    }
    
    private int getCategoryColor(Module module) {
        switch (module.getCategory()) {
            case MOVEMENT: return 0xFF00BFFF; // Голубой
            case RENDER: return 0xFFFFFF00;   // Жёлтый
            case PLAYER: return 0xFF00FF00;   // Зелёный
            case COMBAT: return 0xFFFF0000;   // Красный
            case WORLD: return 0xFF8B4513;    // Коричневый
            default: return 0xFFFFFFFF;
        }
    }
}
