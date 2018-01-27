package com.huhj.resolve.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.huhj.resolve.operation.method.convert.HexDecimalConvertMethod;

/**
 * 参考validation提供的注解
 * @author 胡华杰
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Convert {
	
	public int start();
	
	public int end();
	
	public ConvertOperation operation();
	
	public String[] parameters() default {};
	
	public int order() default 0;

	public static enum ConvertOperation{
		
		
		HexDecimal(HexDecimalConvertMethod.class);
		
		private Class<?> clazz;

		public Class<?> getClazz() {
			return clazz;
		}

		public void setClazz(Class<?> clazz) {
			this.clazz = clazz;
		}

		private ConvertOperation(Class<?> clazz) {
			this.clazz = clazz;
		}
		
	}
	
}
