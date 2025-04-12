package me.zeld.emperia.api;

import me.zeld.emperia.reign.network.*;
import me.zeld.emperia.reign.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reign")
public class ReignController {
    Reign reign;

    @GetMapping(value = "/{name}", produces = "application/json")
    public Reign createReign(@PathVariable String name) {
        if (reign != null) {
            Client.delete(reign.getName());
            System.out.println("Reign deleted");
        }
        reign = Client.create(name);
        System.out.println("Reign created");

        return reign;
    }
}
