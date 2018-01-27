package com.huhj.resolve.operation.method.validation;

import com.huhj.resolve.operation.method.ValidationMethod;
import com.huhj.resolve.util.ByteUtil;

public class HexEqualsValidationMethod implements ValidationMethod {
	
	private int start;
	
	private int end;
	
	private String compareStr;
	
	public HexEqualsValidationMethod(Integer start,Integer end,String[] parameters){
		this.start=start;
		this.end=end;
		this.compareStr=parameters[0];
	}

	@Override
	public void decode(byte[] bytes) {
		String str=ByteUtil.bytes2HexString(ByteUtil.copyOfRange(bytes, start, end));
		if(!compareStr.equals(str)){
			throw new RuntimeException("相等校验失败");
		}

	}

	@Override
	public void encode(byte[] bytes) {
		// TODO Auto-generated method stub

	}

}
