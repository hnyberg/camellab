package com.camellab.servers;

import com.camellab.model.FileToHttpPojo;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReceiveController {

    @PostConstruct
    public void start() {
        System.out.println("starting server");
    }

    @GetMapping("/ping")
    public String ping() {
        return "ping";
    }

    @PostMapping("/receive")
    public String receive(@RequestBody FileToHttpPojo pojo) {
        System.out.println("Mottog pojo: " + pojo);
        return "Mottaget!";
    }

    @GetMapping("/receive")
    public String checkReceive() {
        System.out.println("called receive endpoint GET");
        return "you found receive endpoint, congrats";
    }
}
