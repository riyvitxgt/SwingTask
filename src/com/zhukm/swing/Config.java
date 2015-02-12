package com.zhukm.swing;

import java.util.HashMap;
import java.util.Map;

public class Config {
	private static Map<String,String> config = new HashMap<String,String>();
	
	private Config(){
		super();
	}
	
	public static String getValue(String key){
		return config.get(key);
	}
	
	public static void init(){
		config.put("user", "sa");
		config.put("password", "ri1yvi2txgt6");
		config.put("server", "jdbc:sqlserver://localhost:1433;DatabaseName=");
		config.put("defaultDB", "jdbc:sqlserver://localhost:1433;DatabaseName=mybatis");
	}
	
	public static synchronized void  addConfig(String key, String value){
		if(!config.containsKey(key)){
			config.put(key, value);
		}
	}
	
	public static boolean isInit(){
		return !config.isEmpty();
	}
}
