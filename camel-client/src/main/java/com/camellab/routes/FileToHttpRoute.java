package com.camellab.routes;

import com.camellab.model.FileToHttpPojo;
import com.camellab.processor.FileToHttpProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class FileToHttpRoute extends RouteBuilder {

    @Override
    public void configure() {
        onException(IllegalArgumentException.class)
                .handled(true)
                .log("Fel vid hantering av fil ${header.CamelFileName}: ${exception.message}")
                .log("flyttar fil till mappen error/")
                .marshal().json(JsonLibrary.Jackson)
                .toD("file:error/?fileName=${header.CamelFileName}");

        from("file:input/?delete=true")
                .routeId("file-to-http")
                .log("Läst fil: ${header.CamelFileName}")
                .unmarshal().json(JsonLibrary.Jackson, FileToHttpPojo.class)
                .bean(FileToHttpProcessor.class, "process")
                .log("Böna skapad")
                .marshal().json()
                .setHeader("Content-Type", constant("application/json"))
                .to("http://localhost:8080/api/receive")
                .log("Data skickat till HTTP endpoint");

    }
}
