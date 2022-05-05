package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Publication;
import com.example.demo.service.PublicationService;

@RestController
@RequestMapping(value = "/publication")
public class PublicationController {

	@Autowired
	private PublicationService publicationService;

	@GetMapping(value = "/list")
	public List<Publication> findAll() {
		return this.publicationService.findAll();
	}

	@GetMapping(value = "/page")
	public ResponseEntity<?> findAllPageable(@RequestParam(required = false) Map<String, String> params) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.publicationService.findAllPageable(params));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			Publication publication = this.publicationService.findByIdWithThrows(id);
			return ResponseEntity.status(HttpStatus.OK).body(publication);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}
}
