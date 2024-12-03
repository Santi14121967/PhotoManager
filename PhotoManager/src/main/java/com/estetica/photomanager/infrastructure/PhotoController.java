package com.estetica.photomanager.infrastructure;



import com.estetica.photomanager.application.PhotoService;
import com.estetica.photomanager.domain.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/{id}")
    public void uploadPhoto(
            @PathVariable String id,
            @RequestParam("photo") MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setSrc(file.getOriginalFilename());
        photoService.savePhoto(id, photo, file.getBytes());
    }

    @GetMapping("/{id}")
    public List<Photo> listPhotos(@PathVariable String id) {
        return photoService.listPhotos(id);
    }

    @DeleteMapping("/{id}/{photoName}")
    public void deletePhoto(@PathVariable String id, @PathVariable String photoName) {
        photoService.deletePhoto(id, photoName);
    }

    @DeleteMapping("/{id}")
    public void deleteFolder(@PathVariable String id) {
        photoService.deleteFolder(id);
    }
}
