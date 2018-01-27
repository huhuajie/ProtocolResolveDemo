package com.huhj.resolve.operation.method.convert;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.huhj.resolve.operation.method.ConvertMethod;
import com.huhj.resolve.util.ByteUtil;

public class HexDecimalConvertMethod implements ConvertMethod<BigDecimal>{
	
	private int start;
	
	private int end;
	
	private int pointPosition;
	
	public HexDecimalConvertMethod(Integer start,Integer end,String[] parameters){
		this.start=start;
		this.end=end;
		this.pointPosition=Integer.valueOf(parameters[0]);
	}

	public BigDecimal decode(byte[] bytes) {
		String str=ByteUtil.bytes2HexString(ByteUtil.copyOfRange(bytes, start, end));
		StringBuilder sb=new StringBuilder(str);
		sb.insert(pointPosition, ".");
		return new BigDecimal(sb.toString());
	}
	
	
	
	@Override
	public void encode(byte[] bytes,BigDecimal instance) {
		//前面需要有  in个字符,加上小数点,再加上 (end-start)*2-in个字符
		String[] strs=instance.toString().split("\\.");
		String part1=strs[0];
		String part2=strs.length==1?"":strs[1];
		
		String str=StringUtils.leftPad(part1, pointPosition, "0")+StringUtils.rightPad(part2, (end-start)*2-pointPosition,"0");
		
		ByteUtil.replaceBytes(bytes, start, end, ByteUtil.HexString2bytes(str));
		
	}
	
	/*@Override
	public BigDecimal decode(byte[] bytes, int start, int end, String[] parameters) {
		int i=Integer.valueOf(parameters[0]);
		String str=ByteUtil.bytes2HexString(ByteUtil.copyOfRange(bytes, start, end));
		StringBuilder sb=new StringBuilder(str);
		sb.insert(i, ".");
		return new BigDecimal(sb.toString());
	}

	@Override
	public void encode(byte[] bytes, int start, int end, String[] parameters, BigDecimal instance) {
		//前面需要有  in个字符,加上小数点,再加上 (end-start)*2-in个字符
		int in=Integer.valueOf(parameters[0]);
		String[] strs=instance.toString().split("\\.");
		String part1=strs[0];
		String part2=strs.length==1?"":strs[1];
		
		String str=StringUtils.leftPad(part1, in, "0")+StringUtils.rightPad(part2, (end-start)*2-in,"0");
		
		ByteUtil.replaceBytes(bytes, start, end, ByteUtil.HexString2bytes(str));
		
	}*/
	

}
