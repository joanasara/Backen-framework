package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public <T> SendResult<String, String> send(String topic, T message, Map<String, Object> properties)
			throws Exception {
		KafkaMessage<T> kafkaMessage = new KafkaMessage<T>();
		kafkaMessage.setTopic(topic);
		kafkaMessage.setContext(this.getClass().toString());
		kafkaMessage.setEntity(message);
		kafkaMessage.setProperties(properties);

		String value = objectMapper.writeValueAsString(kafkaMessage);

		SendResult<String, String> result = null;

		try {
			result = this.kafkaTemplate.send(KafkaConstants.getByTopic(topic), value).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw e;
		} catch (ExecutionException e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}
}
