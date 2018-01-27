package com.huhj.resolve.operation.method;

/**
 * 验证方法的接口
 * @author 胡华杰
 *
 */
public interface ValidationMethod {
	
	public void decode(byte[] bytes);
	
	
	public void encode(byte[] bytes);
	

}
