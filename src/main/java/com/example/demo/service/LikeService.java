package com.example.demo.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entities.Like;
import com.example.demo.entities.Publication;
import com.example.demo.entities.User;
import com.example.demo.kafka.KafkaMessage;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.PublicationRepository;

@Service
@Transactional
public class LikeService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	private PublicationRepository publicationRepository;

	public void publish(Publication kafkaMessage) {
		Optional<Publication> optional = this.publicationRepository.findById(kafkaMessage.getEntity().getId());
		if (optional.isPresent()) {
			Long userId = ((Integer) kafkaMessage.getProperties().get("user")).longValue();
			ResponseEntity<User> request = restTemplate.exchange("http://localhost:8080/user/user/id/" + userId,
					HttpMethod.GET, null, User.class);
			User user = request.getBody();

			Publication publication = optional.get();

			Like like = new Like();
			like.setPublication(publication);
			like.setUser(user);

			this.likeRepository.save(like);
		}
	}

	public void delete(KafkaMessage<Publication> kafkaMessage) {
		Optional<Publication> optional = this.publicationRepository.findById(kafkaMessage.getEntity().getId());
		if (optional.isPresent()) {
			Long userId = ((Integer) kafkaMessage.getProperties().get("user")).longValue();
			ResponseEntity<User> request = restTemplate.exchange("http://localhost:8080/user/user/id/" + userId,
					HttpMethod.GET, null, User.class);
			User user = request.getBody();

			Publication publication = optional.get();

			this.likeRepository.deleteLikeFromPublicationAndUser(publication.getId(), user.getId());
		}
	}

	public void deleteLikesFromPublicationId(Long id) {
		this.likeRepository.deleteLikesFromPublicationId(id);
	}

}
