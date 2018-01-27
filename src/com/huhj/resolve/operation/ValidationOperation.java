package com.huhj.resolve.operation;

import java.lang.reflect.Method;


public class ValidationOperation implements Operation {
	
    private Method decodeMethod;
	
	private Method encodeMethod;
	
	private Object invokeObject;
	
	public  ValidationOperation(Method decodeMethod,Method encodeMethod,
			int start,int end,String[] parameters) {
		try{
			this.decodeMethod=decodeMethod;
			this.encodeMethod=encodeMethod;
			this.invokeObject=encodeMethod.getDeclaringClass()
			  .getConstructor(Integer.class,Integer.class,String[].class)
			  .newInstance(start,end,parameters);
		}catch(Exception e){
			throw new RuntimeException("构造异常");
		}
	}

	@Override
	public <T> void handle(byte[] bytes, T instance, OperationDirection direction) throws Exception {
		if(direction.equals(OperationDirection.encode)){
			encodeMethod.invoke(invokeObject, bytes);
		}else if(direction.equals(OperationDirection.decode)){
			decodeMethod.invoke(invokeObject, bytes);
		}
	}

}
