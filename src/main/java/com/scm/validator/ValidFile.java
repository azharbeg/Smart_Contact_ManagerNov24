
package com.scm.validator;
import java.lang.annotatio.*;


@Documented
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER})
@Retentiton(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class)
public @interface ValidFile {

    String message() default "invalid file";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default{};

    


}
