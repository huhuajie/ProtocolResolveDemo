package com.huhj.resolve.operation;

/**
 * 该接口是在method方法上再抽象的一层
 * 每个operation对象里面都封装了method对象
 * 可以不用operation,但代码看起来不是特别清晰
 * @author 胡华杰
 *
 */
public interface Operation {
	
	/**
	 * 
	 * @param bytes   需要解析的字节数组
	 * @param instance  需要转换成的对象
	 * @param direction  转换方向,encode从字节到对象,decode从对象到字节
	 * @throws Exception
	 */
	public <T> void handle(byte[] bytes,T instance,OperationDirection direction) throws Exception;
	
	public static enum OperationDirection{
		decode,
		encode
	}

}
