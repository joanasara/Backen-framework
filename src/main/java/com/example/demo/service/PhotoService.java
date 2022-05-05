package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Photo;
import com.example.demo.entities.Publication;
import com.example.demo.repository.PhotoRepository;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public void publishPhotoFiles(List<Photo> photoFiles, Publication publication) {
        photoFiles.forEach(file -> {
            file.setPublication(publication);
            this.photoRepository.save(file);
        });
    }

    public void deletePhotoFilesByPublicationId(Long id) {
        this.photoRepository.deletePhotoFilesByPublicationId(id);
    }
}
