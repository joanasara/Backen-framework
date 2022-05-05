package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.demo.entities.Publication;
import com.example.demo.service.LikeService;
import com.example.demo.service.PublicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Kafka {
	private static final String PUBLISH_ACTION = "publish";
	private static final String DELETE_ACTION = "delete";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PublicationService publicationService;

	@Autowired
	private LikeService likeService;

	@KafkaListener(topics = KafkaConstants.LIKE_TOPIC)
	private void processLikeTopic(String message) throws JsonProcessingException {
		KafkaMessage<Publication> kafkaMessage = this.objectMapper.readValue(message,
				new TypeReference<KafkaMessage<Publication>>() {
				});

		String action = kafkaMessage.getAction();
		if (action.equals(PUBLISH_ACTION)) {
			this.likeService.publish(kafkaMessage);
		}
		if (action.equals(DELETE_ACTION)) {
			this.likeService.delete(kafkaMessage);
		}
	}

	@KafkaListener(topics = KafkaConstants.VISUALIZATION_TOPIC)
	private void processVisualizationTopic(String message) throws JsonProcessingException {
		KafkaMessage<Publication> kafkaMessage = this.objectMapper.readValue(message,
				new TypeReference<KafkaMessage<Publication>>() {
				});

		String action = kafkaMessage.getAction();
		if (action.equals(PUBLISH_ACTION)) {
			this.publicationService.visualize(kafkaMessage);
		}
		if (action.equals(DELETE_ACTION)) {

		}
	}

	@KafkaListener(topics = KafkaConstants.PUBLICATION_TOPIC)
	private void processPublicationTopic(String message) throws JsonProcessingException {
		KafkaMessage<Publication> kafkaMessage = this.objectMapper.readValue(message,
				new TypeReference<KafkaMessage<Publication>>() {
				});

		String action = kafkaMessage.getAction();
		if (action.equals(PUBLISH_ACTION)) {
			this.publicationService.publish(kafkaMessage);
		}
		if (action.equals(DELETE_ACTION)) {
			this.publicationService.delete(kafkaMessage);
		}
	}

	@KafkaListener(topics = KafkaConstants.COMMENT_TOPIC)
	private void processCommentTopic(String message) throws JsonProcessingException {
		KafkaMessage<Publication> kafkaMessage = this.objectMapper.readValue(message,
				new TypeReference<KafkaMessage<Publication>>() {
				});

		String action = kafkaMessage.getAction();
		if (action.equals(PUBLISH_ACTION)) {
			this.publicationService.publishComment(kafkaMessage);
		}
		if (action.equals(DELETE_ACTION)) {
			this.publicationService.deleteComment(kafkaMessage);
		}
	}

}
