package com.huhj.resolve.operation;

import java.lang.reflect.Method;

public class ConvertOperation implements Operation {
	
	private Method decodeMethod;
	
	private Method encodeMethod;
	
	private Method setMethod;
	
	private Method getMethod;
	
    private Object invokeObject;
	
	public ConvertOperation(Method decodeMethod,Method encodeMethod,
			Method setMethod,Method getMethod,int start,int end,String[] parameters){
		try{
			this.decodeMethod=decodeMethod;
			this.encodeMethod=encodeMethod;
			this.setMethod=setMethod;
			this.getMethod=getMethod;
			this.invokeObject=encodeMethod.getDeclaringClass()
					  .getConstructor(Integer.class,Integer.class,String[].class)
					  .newInstance(start,end,parameters);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public <T> void handle(byte[] bytes, T instance, OperationDirection direction) throws Exception {
		//解密,从服务端到表端
		if(direction.equals(OperationDirection.encode)){
			Object obj=getMethod.invoke(instance);
			encodeMethod.invoke(bytes, obj);
		}else if(direction.equals(OperationDirection.decode)){
			Object obj=decodeMethod.invoke(invokeObject, bytes);
			setMethod.invoke(instance, obj);
		}

	}

}
