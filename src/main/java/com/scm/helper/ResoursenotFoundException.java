package com.scm.helper;

public class ResoursenotFoundException extends RuntimeException
{
    public  ResoursenotFoundException(String message){
       
        super(message);
    }
    public ResoursenotFoundException(){
        super("Resource not found");
    }

}

