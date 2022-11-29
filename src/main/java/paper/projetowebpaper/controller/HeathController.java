package paper.projetowebpaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeathController {
    @GetMapping(path = "/api/status")
    public String status() {
        return "online";
    }
}
