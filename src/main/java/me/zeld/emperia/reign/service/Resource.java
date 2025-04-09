package me.zeld.emperia.reign.service;


import me.zeld.emperia.reign.util.type.*;

public class Resource {
    private final ResourceType type;
    private int value;

    public Resource(int value, ResourceType type) {
        this.value = value;
        this.type = type;
    }

    /**
     * @return type
     */
    public ResourceType getType() {
        return type;
    }

    /**
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }
}
