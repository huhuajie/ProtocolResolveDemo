package com.huhj.resolve.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.huhj.resolve.annotation.Convert;
import com.huhj.resolve.annotation.Replace;
import com.huhj.resolve.annotation.Validation;
import com.huhj.resolve.annotation.inner.ReplaceContainer;
import com.huhj.resolve.annotation.inner.ValidationContainer;
import com.huhj.resolve.operation.ConvertOperation;
import com.huhj.resolve.operation.Operation;
import com.huhj.resolve.operation.ReplaceOperation;
import com.huhj.resolve.operation.ValidationOperation;

public class AnalysisUtil {
	
	/**
	 * 返回结果需要缓存到map里面,demo中没有缓存
	 * 获取注解信息同时也需要获取父类的注解信息,demo先不做考虑
	 * 还要考虑order注解,修正operation的顺序,demo中也并没有考虑
	 * @param clazz
	 * @return
	 */
	public static List<Operation> ananlysisClass(Class<?> clazz){
		
		List<Operation> list=new ArrayList<Operation>();
		
		List<Validation> validations=new ArrayList<Validation>();
		if(clazz.isAnnotationPresent(ValidationContainer.class)){
			ValidationContainer vc=clazz.getAnnotation(ValidationContainer.class);
			for(Validation v:vc.value()){
				validations.add(v);
			}
		}
		if(clazz.isAnnotationPresent(Validation.class)){
			validations.add(clazz.getAnnotation(Validation.class));
		}
		
		
		for(Validation v:validations){
			Method decodeMethod=findMethod("decode",v.operation().getClazz());
			Method encodeMethod=findMethod("encode",v.operation().getClazz());
			Operation oper=new ValidationOperation(decodeMethod, encodeMethod, v.start(), v.end(), v.parameters());
			list.add(oper);
		}
		
		
		
		
		
		List<Replace> replaces=new ArrayList<Replace>();
		if(clazz.isAnnotationPresent(ReplaceContainer.class)){
			for(Replace r:clazz.getAnnotation(ReplaceContainer.class).value()){
				replaces.add(r);
			}
		}
		if(clazz.isAnnotationPresent(Replace.class)){
			replaces.add(clazz.getAnnotation(Replace.class));
		}
		for(Replace r:replaces){
			Method decodeMethod=findMethod("decode",r.operation().getClazz());
			Method encodeMethod=findMethod("encode",r.operation().getClazz());		
			Operation oper=new ReplaceOperation(decodeMethod, encodeMethod, r.start(), r.end(), r.parameters());
			list.add(oper);
		}
		
		
		
		for(Field field:clazz.getDeclaredFields()){
			if(field.isAnnotationPresent(Convert.class)){
				Convert con=field.getAnnotation(Convert.class);
				Method decodeMethod=findMethod("decode",con.operation().getClazz());
				Method encodeMethod=findMethod("encode",con.operation().getClazz());
				Method setMethod=findMethod("set"+field.getName(),clazz);
				Method getMethod=findMethod("get"+field.getName(),clazz);
				Operation oper=new ConvertOperation(decodeMethod, encodeMethod, setMethod, getMethod, con.start(), con.end(), con.parameters());
				list.add(oper);
			}
		}
		
		
		
		
		return list;	
	}
	
	
	private static Method findMethod(String methodName,Class<?> clazz){
		for(Method m:clazz.getMethods()){
			if(m.getName().equalsIgnoreCase(methodName)){
				return m;
			}
		}
		return null;
	}

}
