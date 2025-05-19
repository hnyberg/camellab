package com.camellab.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileToHttpRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("file:input/?noop=true")
                .routeId("file-to-http")
                .log("Läst fil: ${header.CamelFileName}")
                .unmarshal().json()
                .bean(com.camellab.processor.FileToHttpProcessor.class, "process")
                .marshal().json()
                .setHeader("Content-Type", constant("application/json"))
                .to("http://localhost:8080/api/receive")
                .log("Data skickat till HTTP endpoint");

    }
}
