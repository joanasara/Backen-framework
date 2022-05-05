package com.example.demo.kafka;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KafkaMessage<T> {
	
    private String topic;
    private String context;
    private T entity;
    private Map<String, Object> properties;

    public String getAction() {
        return (String) this.properties.get("action");
    }
}
