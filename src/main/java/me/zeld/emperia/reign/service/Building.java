package me.zeld.emperia.reign.service;


import me.zeld.emperia.reign.util.type.*;

public class Building implements Runnable {
    private BuildingType type;
    private final ResourceType resource;
    private Resource production;
    private int productionRate;

    /**
     * @param type
     * @param productionRate
     */
    public Building(BuildingType type, int productionRate) {
        this.type = type;

        this.resource = switch (type) {
            case PIG_FARM -> ResourceType.MEAT;
            case QUARRY -> ResourceType.STONE;
            case FIELD_FARM -> ResourceType.VEGETABLE;
            case LUMBERJACK_HUT -> ResourceType.WOOD;
        };

        this.productionRate = productionRate;

        production = new Resource(0, resource);
    }

    /**
     * @return
     */
    public Resource collect() {
        Resource collected = production;
        production = new Resource(0, collected.getType());

        return collected;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                production.setValue(production.getValue() + productionRate);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @return type
     */
    public BuildingType getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(BuildingType type) {
        this.type = type;
    }

    /**
     * @return productionRate
     */
    public int getProductionRate() {
        return productionRate;
    }

    /**
     * @param productionRate
     */
    public void setProductionRate(int productionRate) {
        this.productionRate = productionRate;
    }
}
