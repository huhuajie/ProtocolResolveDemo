package com.huhj.resolve.operation.method.replace;

import com.huhj.resolve.operation.method.ReplaceMethod;
import com.huhj.resolve.util.ByteUtil;

public class SubtractReplaceMethod implements ReplaceMethod{
	
	private int start;
	
	private int end;
	
	private byte subtractByte;
	
	public SubtractReplaceMethod(Integer start,Integer end,String[] parameters){
		this.start=start;
		this.end=end;
		this.subtractByte=ByteUtil.HexString2bytes(parameters[0])[0];
	}

	@Override
	public void decode(byte[] bytes) {
		for(int i=0;i<end-start;i++){
			bytes[i+start]=(byte) (bytes[i+start]-subtractByte);
		}
		
	}

	@Override
	public void encode(byte[] bytes) {
		for(int i=0;i<end-start;i++){
			bytes[i+start]=(byte) (bytes[i+start]+subtractByte);
		}
	}

}
