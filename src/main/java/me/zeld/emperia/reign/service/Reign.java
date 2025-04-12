package me.zeld.emperia.reign.service;

public class Reign implements Runnable {
    private String name;

    private Warehouse warehouse;
    private Thread warehouseThread;

    private Terrain[] map;
    private int gold;

    private boolean active;

    public Reign(String name, int gold) {
        this.name = name;
        this.gold = gold;

        this.map = new Terrain[5];

        this.warehouse = new Warehouse(this.map);
        this.warehouseThread = new Thread(warehouse);
        this.warehouseThread.start();
    }

    public void addTerrain(Terrain terrain) {
        for (int i = 0; i < map.length; i++) {
            if (map[i] == null) {
                map[i] = terrain;
                break;
            }
        }
    }

    public Terrain getTerrain(int index) {
        return map[index];
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Terrain[] getMap() {
        return map;
    }

    public int getGold() {
        return gold;
    }
}
