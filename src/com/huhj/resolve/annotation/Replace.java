package com.huhj.resolve.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.huhj.resolve.annotation.inner.ReplaceContainer;
import com.huhj.resolve.operation.method.replace.SubtractReplaceMethod;

/**
 * 请参考 validation注解
 * @author 胡华杰
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ReplaceContainer.class)
public @interface Replace {
	
	public int start();
	
	public int end();
	
	public ReplaceOperation operation();
	
	public String[] parameters() default {};
	
	public int order() default 0;
	
	public static enum ReplaceOperation{
		
		Subtract(SubtractReplaceMethod.class);
		
		private Class<?> clazz;

		public Class<?> getClazz() {
			return clazz;
		}

		public void setClazz(Class<?> clazz) {
			this.clazz = clazz;
		}

		private ReplaceOperation(Class<?> clazz) {
			this.clazz = clazz;
		}
		
	}

}
