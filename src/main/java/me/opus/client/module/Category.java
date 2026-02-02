package me.opus.client.module;

public enum Category {
    MOVEMENT("Movement"),
    RENDER("Render"),
    PLAYER("Player"),
    COMBAT("Combat"),
    WORLD("World");
    
    private final String name;
    
    Category(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
