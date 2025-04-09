package me.zeld.emperia.reign.service;


import me.zeld.emperia.reign.util.type.*;

public class Warehouse implements Runnable {

    private final int sleepTime = 10000;
    private Resource[] warehouse;
    private Terrain[] terrains;

    /**
     * @param terrains
     */
    public Warehouse(Terrain[] terrains) {
        this.warehouse = new Resource[ResourceType.values().length];
        this.terrains = terrains;

        for (int i = 0; i < this.warehouse.length; i++) {
            warehouse[i] = new Resource(0, ResourceType.values()[i]);
        }
    }



    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(sleepTime);
                for (Terrain terrain : terrains) {
                    if (terrain != null) {
                        Building building = terrain.getBuilding();
                        if (building != null) {
                            Resource production = building.collect();
                            for (Resource resource : warehouse) {
                                if (resource.getType() == production.getType()) {
                                    resource.setValue(resource.getValue() + production.getValue());
                                }
                            }
                        }
                    }
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Resource[] getWarehouse() {
        return warehouse;
    }

    public Terrain[] getTerrains() {
        return terrains;
    }

    /**
     * @return sleepTime
     */
    public int getSleepTime() {
        return sleepTime;
    }
}