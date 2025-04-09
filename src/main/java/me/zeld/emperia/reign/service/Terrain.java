package me.zeld.emperia.reign.service;


import me.zeld.emperia.reign.util.type.*;

public class Terrain {
    protected TerrainType type;
    protected BuildingType[] options;
    protected Building building;

    private Thread thread;


    /**
     * Just a constructor.
     * @param type
     * @param options
     */
    private Terrain(TerrainType type, BuildingType[] options) {
        this.type = type;
        this.options = options;
        this.building = null;
    }

    /***
     * Get the type of terrain.
     * @return type
     */
    public TerrainType getType() {
        return type;
    }

    /**
     * Get the options for building in terrain.
     * @return options
     */
    public BuildingType[] getOptions() {
        return options;
    }

    /**
     * Get the building in terrain.
     * @return building
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Build a building in terrain.
     * @param building
     */
    public void build(Building building) {
        for (BuildingType option : options) {
            if (option == building.getType()) {
                this.building = building;
                thread = new Thread(building);
                thread.start();
                return;
            }
        }
    }


    /**
     * Destroy the building in terrain.
     */
    public void destroy() {
        if (building != null) {
            thread.interrupt();
            building = null;
        }
    }

    public static Terrain create(TerrainType type) {
        return switch (type) {
            case FIELD -> new Terrain(type, new BuildingType[]{BuildingType.FARM});
            case MOUNTAIN -> new Terrain(type, new BuildingType[]{BuildingType.QUARRY});
            case FOREST -> new Terrain(type, new BuildingType[]{BuildingType.LUMBERJACK_HUT});
        };
    }

    public Resource collect() {
        if (building != null) {
            return building.collect();
        }
        return null;
    }
}
