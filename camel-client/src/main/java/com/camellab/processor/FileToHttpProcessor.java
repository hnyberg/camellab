package com.camellab.processor;

import com.camellab.model.FileToHttpPojo;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FileToHttpProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws IllegalArgumentException {

        FileToHttpPojo pojo = exchange.getIn().getBody(FileToHttpPojo.class);

        if (pojo.value() < 0) {
            throw new IllegalArgumentException("Value must not be negative");
        }

        exchange.getIn().setBody(pojo);
    }
}
