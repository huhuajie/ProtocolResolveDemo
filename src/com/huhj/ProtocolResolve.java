package com.huhj;

import java.util.List;

import com.huhj.model.ImiateProtocolModel;
import com.huhj.resolve.operation.Operation;
import com.huhj.resolve.operation.Operation.OperationDirection;
import com.huhj.resolve.util.AnalysisUtil;
import com.huhj.resolve.util.ByteUtil;

/**
 * 基于注解解析协议的一个demo
 * @author 胡华杰
 *
 */
public class ProtocolResolve {
	
	public static <T> T  bytes2Ojbect(byte[] bytes,Class<T> clazz) throws Exception{
		T instance=clazz.newInstance();
		List<Operation> opers=AnalysisUtil.ananlysisClass(clazz);
		for(Operation oper:opers){
			oper.handle(bytes, instance, OperationDirection.decode);
		}
		return instance;
	}
	
	/*反向解析,按正向解析参考,可能需要提供template注解*/
	public static <T> byte[] Object2bytes(T instance){
		return null;
	}
	
	public static void main(String[] args) throws Exception{
		byte[] bytes=ByteUtil.HexString2bytes("3F010616263B");
		ImiateProtocolModel instance=ProtocolResolve.bytes2Ojbect(bytes, ImiateProtocolModel.class);
		System.out.println(instance);
	}

}
