package com.estetica.photomanager.infrastructure;

import com.estetica.photomanager.domain.Photo;
import com.estetica.photomanager.domain.PhotoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileSystemPhotoRepository implements PhotoRepository {
    @Value("${photo-manager.base-directory}")
    private String BASE_DIRECTORY;

    @Override
    public void savePhoto(String id, Photo photo, byte[] fileContent) {
        File folder = new File(BASE_DIRECTORY, id);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File photoFile = new File(folder, photo.getSrc());
        try (FileOutputStream fos = new FileOutputStream(photoFile)) {
            fos.write(fileContent);
        } catch (IOException e) {
            throw new RuntimeException("Error saving photo", e);
        }
    }

    @Override
    public List<Photo> listPhotos(String id) {
        File folder = new File(BASE_DIRECTORY, id);
        List<Photo> photos = new ArrayList<>();
        System.out.println("\nBase directory:" + BASE_DIRECTORY);
        System.out.println("folder:" + folder.getName());
        System.out.println("isDirectory:" + folder.isDirectory());
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                Photo photo = new Photo();
                photo.setSrc(file.getName());
                // Opcional: podrías calcular dimensiones si necesitas.
                try {
                    BufferedImage image = ImageIO.read(file);
                    photo.setWidth(image.getWidth());
                    photo.setHeight(image.getHeight());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                photos.add(photo);
            }
        }
        return photos;
    }

    @Override
    public void deletePhoto(String id, String photoName) {
        File photoFile = new File(BASE_DIRECTORY, id + File.separator + photoName);
        if (!photoFile.delete()) {
            throw new RuntimeException("Error deleting photo");
        }
    }

    @Override
    public void deleteFolder(String id) {
        File folder = new File(BASE_DIRECTORY, id);
        if (folder.exists()) {
            for (File file : folder.listFiles()) {
                file.delete();
            }
            folder.delete();
        }
    }
}
