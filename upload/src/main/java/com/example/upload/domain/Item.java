package com.example.upload.domain;

import lombok.Data;
import java.util.List;

@Data
public class Item {
    private Long id;
    private String title;
    private String contents;
    private UploadFile attachFile;
    private List<UploadFile> imageFiles;
}