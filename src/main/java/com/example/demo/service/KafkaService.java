package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;

import com.example.demo.entities.Publication;
import com.example.demo.kafka.KafkaConstants;
import com.example.demo.kafka.KafkaProducer;

public class KafkaService {

	@Autowired
	private KafkaProducer kafkaProducer;

	public SendResult<String, String> publishPublication(Publication publication) throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("action", "publish");

		return this.kafkaProducer.send(KafkaConstants.PUBLICATION_TOPIC, publication, properties);
	}

	public SendResult<String, String> deletePublication(Publication publication) throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("action", "delete");

		return this.kafkaProducer.send(KafkaConstants.PUBLICATION_TOPIC, publication, properties);
	}

	public SendResult<String, String> commentPublication(Publication publication) throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("action", "publish");
		properties.put("publication", publication.getReference().getId());

		return this.kafkaProducer.send(KafkaConstants.COMMENT_TOPIC, publication, properties);
	}

	public SendResult<String, String> deleteComment(Publication publication) throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("action", "delete");

		return this.kafkaProducer.send(KafkaConstants.COMMENT_TOPIC, publication, properties);
	}

	public SendResult<String, String> likePublication(Publication publication) throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("action", "publish");
		properties.put("user", publication.getCreator().getId());

		return this.kafkaProducer.send(KafkaConstants.LIKE_TOPIC, publication, properties);
	}

	public SendResult<String, String> dislikePublication(Publication publication) throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("action", "delete");
		properties.put("user", publication.getCreator().getId());

		return this.kafkaProducer.send(KafkaConstants.LIKE_TOPIC, publication, properties);
	}

	public SendResult<String, String> visualisePublication(Publication publication) throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("action", "publish");

		return this.kafkaProducer.send(KafkaConstants.VISUALIZATION_TOPIC, publication, properties);
	}

}
