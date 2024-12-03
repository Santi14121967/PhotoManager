package com.estetica.photomanager.domain;
import lombok.Data;

@Data
public class Photo {
    private int width;
    private int height;
    private String src;
}