package com.example.demo.kafka;

import java.util.List;

public class KafkaConstants {

	public static final String PUBLICATION_TOPIC = "publication-topic";
	public static final String COMMENT_TOPIC = "comment-topic";
	public static final String LIKE_TOPIC = "like-topic";
	public static final String VISUALIZATION_TOPIC = "visualization-topic";

	public static final List<String> ALLOWED_TOPICS = List.of(
			"publication-topic",
			"comment-topic", 
			"like-topic",
			"visualization-topic");

	public static String getByTopic(String value) {
		return ALLOWED_TOPICS.contains(value) ? value : null;
	}

}
