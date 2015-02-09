package com.zhukm.swing;

import javax.swing.ImageIcon;

import org.junit.Test;

public class MainFrameTest {

	@Test
	public void testImageIcon(){
		ImageIcon icon = new ImageIcon("images\\frameIcon.PNG");
		
		System.out.println(icon.getIconHeight());
		
		
	}
}
