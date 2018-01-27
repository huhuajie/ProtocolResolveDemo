package com.huhj.resolve.annotation.inner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.huhj.resolve.annotation.Validation;

/**
 * validation注解的容器,否则validation注解不支持多个
 * @author 胡华杰
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidationContainer {
	
	public Validation[] value();

}
