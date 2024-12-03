package com.estetica.photomanager.domain;

import java.util.List;

public interface PhotoRepository {
    void savePhoto(String id, Photo photo, byte[] fileContent);
    List<Photo> listPhotos(String id);
    void deletePhoto(String id, String photoName);
    void deleteFolder(String id);
}
