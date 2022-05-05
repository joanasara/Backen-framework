package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Publication;
import com.example.demo.service.KafkaService;

@RestController
@RequestMapping("/")
public class KafkaController {

	@Autowired
	private KafkaService kafkaService;


	@PostMapping(value = "/publish/comment")
	public ResponseEntity<?> commentPublication(@RequestBody Publication publication) {
		try {
			this.kafkaService.commentPublication(publication);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/delete/comment")
	public ResponseEntity<?> deleteComment(@RequestBody Publication publication) {
		try {
			this.kafkaService.deleteComment(publication);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
	}

	@PostMapping(value = "/publish/like")
	public ResponseEntity<?> likePublication(@RequestBody Publication publication) {
		try {
			this.kafkaService.likePublication(publication);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/delete/like")
	public ResponseEntity<?> dislikePublication(@RequestBody Publication publication) {
		try {
			this.kafkaService.dislikePublication(publication);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
	}

	@PostMapping(value = "/visualization")
	public ResponseEntity<?> visualisePublication(@RequestBody Publication publication) {
		try {
			this.kafkaService.visualisePublication(publication);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/publish/publication")
	public ResponseEntity<?> publishPublication(@RequestBody Publication publication) {
		try {
			this.kafkaService.publishPublication(publication);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/delete/publication")
	public ResponseEntity<?> deletePublication(@RequestBody Publication publication) {
		try {
			this.kafkaService.deletePublication(publication);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
	}

}
