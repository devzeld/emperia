package me.zeld.emperia.api;

import me.zeld.emperia.reign.network.Client;
import me.zeld.emperia.reign.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reign", produces = "application/json")
public class ReignController {
    Reign reign;

    @GetMapping(value = "{name}/full", produces = "application/json")
    public Reign getFullReign(@PathVariable String name) {
        if (reign != null) {
            System.out.println("Reign found");
            if (reign.getName().equals(name)) {
                return reign;
            }
            Client.delete(name);
            reign = null;
            System.out.println("Reign deleted");
        }
        reign = Client.create(name);
        System.out.println("Reign created");

        return reign;
    }
}
