package me.zeld.emperia.api;

import me.zeld.emperia.reign.service.*;
import me.zeld.emperia.reign.util.type.*;
import org.springframework.web.bind.annotation.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReignController {
    Reign test;


    @GetMapping("/reign")
    public Reign getReign() {
        test = new Reign("test", 1234);
        Terrain t = Terrain.create(TerrainType.FIELD);
        t.build(new Building(BuildingType.FARM, 60));
        test.addTerrain(t);

        return test;
    }
}
