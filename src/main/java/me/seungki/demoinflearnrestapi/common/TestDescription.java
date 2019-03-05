package me.seungki.demoinflearnrestapi.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
//해당 어노테이션을 언제까지 가져갈꺼냐
@Retention(RetentionPolicy.SOURCE)
public @interface TestDescription {

    String value();
}
