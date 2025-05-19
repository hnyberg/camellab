package com.camellab.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.camellab.model.FileToHttpPojo;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FileToHttpProcessor implements Processor {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {

        String jsonString = exchange.getIn().getBody(String.class);
        FileToHttpPojo pojo = mapper.readValue(jsonString, FileToHttpPojo.class);

        if (pojo.value() < 0) {
            throw new IllegalArgumentException("Value must not be negative");
        }

        exchange.getIn().setBody(pojo);
    }
}
