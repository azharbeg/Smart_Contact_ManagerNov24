package com.scm.validators;

import org.springframework.web.multipart.MultipartFile;







public class FileValidator implements ConstraintValidator<ValidFile,MultipartFile> {
    
    private static final long MAX_FILE_SIZE = 1024 *1024 * 2;

    //type

    // height

    // width
    @Override
    public boolean isValid(MultipartFile  file, ConstraintValidatorContext context){

        if(file == null || file.isEmpty()){
            
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("file cant be empty");
            return false;
        }

        // file size
        if(file.getSize()> MAX_FILE_SIZE){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("file size exceed < 2 HONA chahiye");
            return false;
        }

        // resolution khud se kro
         
    }
}
