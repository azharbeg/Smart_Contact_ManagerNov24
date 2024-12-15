package com.scm.services.imple;

import com.scm.services.ImageService;

@Service
public class ImageServiceImp implements ImageService{

    private Cloudinary cloudinary;


    public ImageServiceImp(Cloudinary cloudinary){
        this.cloudinary = cloudinary;   // yha cloudinary ke ander data mil chuka hai
    }


    //Purpose:-  hume cloudinary ka use krke contactImage ko upload krna hai server par 

    @Override
    public String uploadImage(MultipartFile contactImage){

        // yha par vo code likna hai jo image ko upload kar rha ho
        // server par aws ya cloudely par
        // and return kar rha hoga : url

        // data nikal ke hum is array (byte) mai dalenge
        byte[] data= new byte[contactImage.getInputStream().available()];


        String filename=UUID.randomUUID().toString();

        contactImage.getInputStream().read(data);
        cloudinary.uploader().upload(data, ObjectUtils.asMap(
            "public_id", contactImage.getOriginalFilename()
        ));


        return this.getUrlFromPublicId(filename);


    }

    // is method mai hum public_id pass krenge or vo hume url pass kregi
    @Override
    public String getUrlFromPublicId(String publicId)
    {

        return cloudiary
        .url()
        .transformation(
            new Transformation <>()
            .width(AppConstants.CONTACT_IMAGE_WIDTH)
            .height(AppConstants.CONTACT_IMAGE_HEIGHT)
            .crop(AppConstants.CONTACT_IMAGE_CROP)
        )
        .generate(publicId);

    }
    
}
