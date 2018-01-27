package com.huhj.resolve.operation.method;

/**
 * T代表的是要对象的某个属性
 * @author 胡华杰
 *
 * @param <T>
 */
public interface ConvertMethod<T> {

	
	public T decode(byte[] bytes);
	
	
	public void encode(byte[] bytes,T instance);

}
