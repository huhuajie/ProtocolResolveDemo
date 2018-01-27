package com.huhj.resolve.util;

import java.util.Arrays;

public class ByteUtil {
	
	/**
	 * 将bytes转换成16进制字符串,如果byte 00101111 通过该方法,会输出 3F
	 * @param bytes
	 * @return
	 */
	public static String bytes2HexString(byte[] bytes){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<bytes.length;i++){
			String str=Integer.toHexString(bytes[i] & 0xff);
			if(str.length()==1){
				str="0"+str;
			}
			sb.append(str);
		}
		return sb.toString().toUpperCase();
		
	}
	
	/**
	 * 将16进制字符串转换成bytes,是上面方法的逆方法
	 * @param str
	 * @return
	 */
	public static byte[] HexString2bytes(String str){
		byte[] bytes=new byte[str.length()/2];  //2个长度的字符代表一个字节
		for(int i=0;i<bytes.length;i++){
			int in=Integer.valueOf(str.substring(i*2,i*2+2),16);
			bytes[i] = (byte) (in & 0xff);
		}
		return bytes;
	}
	
	
	
	/**
	 * 拷贝指定区间的字节
	 * @param bytes
	 * @param start
	 * @param end
	 * @return
	 */
	public static byte[] copyOfRange(byte[] bytes,int start,int end){
		if(start<0){start=start+bytes.length;}
		if(end<=0){end=end+bytes.length;}
		return Arrays.copyOfRange(bytes, start, end);
	}
	
	/**
	 * 替换指定区间的原始字节
	 * @param orginalbytes
	 * @param start
	 * @param end
	 * @param replaceBytes
	 */
	public static void replaceBytes(byte[] orginalbytes,int start,int end,byte[] replaceBytes){
		if(start<0){start=start+orginalbytes.length;}
		if(end<=0){end=end+orginalbytes.length;}
		if(replaceBytes.length!=(end-start)){
			throw new RuntimeException();
		}
		if(end>orginalbytes.length){
			throw new RuntimeException();
		}
		for(int i=0;i<end-start;i++){
			orginalbytes[i+start]=replaceBytes[i];
		}
	}
	

	public static void main(String[] args){
		byte b=(byte) 0x33;
		byte[] bytes=new byte[]{b};
		System.out.println(ByteUtil.bytes2HexString(bytes));
	}
	
	
}
