package com.huhj.resolve.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.huhj.resolve.annotation.inner.ValidationContainer;
import com.huhj.resolve.operation.method.validation.HexEqualsValidationMethod;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ValidationContainer.class)
public @interface Validation {
	/**
	 * 起始字节
	 * @return
	 */
	public int start();
	/**
	 * 结束字节
	 * @return
	 */
	public int end();
	/**
	 * 需要对这部分字节做的操作
	 * @return
	 */
	public ValidationOperation operation();
	/**
	 * 操作需要提供的参数,根据操作的不同有所不同,约定俗成
	 * @return
	 */
	public String[] parameters() default {};
	
	/**
	 * 用于指定解析的顺序
	 * @return
	 */
	public int order() default 0;
	
	public static enum ValidationOperation{
		
		/**
		 * 约定必须提供一个参数,用于比较是否相等
		 */
		HEXEQUALS(HexEqualsValidationMethod.class);
		
		
		private Class<?> clazz;

		public Class<?> getClazz() {
			return clazz;
		}

		public void setClazz(Class<?> clazz) {
			this.clazz = clazz;
		}

		private ValidationOperation(Class<?> clazz) {
			this.clazz = clazz;
		}
		
		
		
	}
	
	

}
