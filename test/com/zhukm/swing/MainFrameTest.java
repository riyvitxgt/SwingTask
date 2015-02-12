package com.zhukm.swing;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MainFrameTest {

	@Test
	public void testImageIcon(){
		/*ImageIcon icon = new ImageIcon("images\\frameIcon.PNG");
		
		System.out.println(icon.getIconHeight());*/
		//Map<String,Object> map = new HashMap<String,Object>();
		
		//System.out.println((boolean)map.get("ddd"));
		
		/*map.put("aa", "b");
		System.out.println(map.containsKey("aa"));*/
		Config.init();
		System.out.println(Config.getValue("user"));
	}
}
