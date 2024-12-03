package com.estetica.photomanager.application;

import com.estetica.photomanager.domain.Photo;
import com.estetica.photomanager.domain.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void savePhoto(String id, Photo photo, byte[] fileContent) {
        photoRepository.savePhoto(id, photo, fileContent);
    }

    public List<Photo> listPhotos(String id) {
        return photoRepository.listPhotos(id);
    }

    public void deletePhoto(String id, String photoName) {
        photoRepository.deletePhoto(id, photoName);
    }

    public void deleteFolder(String id) {
        photoRepository.deleteFolder(id);
    }
}
