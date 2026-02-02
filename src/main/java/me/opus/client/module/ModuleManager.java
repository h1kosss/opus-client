package me.opus.client.module;

import me.opus.client.module.modules.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();
    
    public ModuleManager() {
        // Movement
        modules.add(new Sprint());
        modules.add(new Flight());
        
        // Render
        modules.add(new FullBright());
    }
    
    public void onTick() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onTick();
            }
        }
    }
    
    public void onKey(int key) {
        for (Module module : modules) {
            if (module.getKeyBind() == key) {
                module.toggle();
            }
        }
    }
    
    public List<Module> getModules() {
        return modules;
    }
    
    public List<Module> getEnabledModules() {
        List<Module> enabled = new ArrayList<>();
        for (Module module : modules) {
            if (module.isEnabled()) {
                enabled.add(module);
            }
        }
        // Сортировка по длине имени (для красивого ArrayList)
        enabled.sort((a, b) -> b.getName().length() - a.getName().length());
        return enabled;
    }
    
    public Module getModule(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }
}
