package com.zhukm.swing;

import java.util.HashMap;
import java.util.Map;

public class ComponentCache {
	private static Map<String, Object> cache = new HashMap<String, Object>();
	
	private ComponentCache(){
		super();
	}
	
	public static Object getComponent(String key){
		return cache.get(key);
	}
	
	public static synchronized void addComponent(String key, Object value){
		if(!cache.containsKey(key)){
			cache.put(key, value);
		}
	}
}
