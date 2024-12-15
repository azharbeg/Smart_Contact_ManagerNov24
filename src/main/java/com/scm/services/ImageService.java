package com.scm.services;

public interface ImageService {

    String uploadImage(MultipartFile contactImage);

    String getUrlFromPublicId(String publicId);
}
