package com.camellab.servers;

import com.camellab.model.FileToHttpPojo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReceiveController {

    @PostMapping("/receive")
    public String receive(@RequestBody FileToHttpPojo pojo) {
        System.out.println("Mottog pojo: " + pojo);
        return "Mottaget!";
    }
}
