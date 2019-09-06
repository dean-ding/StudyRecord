package com.dean.retrofit;

import java.lang.annotation.*;

/**
 * Created: tvt on 2019-09-05 19:58
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface POST {
    String value() default "";
}
