package com.zhukm.swing;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

public class PortalPane extends JSplitPane {
	public PortalPane(){
		this.setOneTouchExpandable(true);
		this.setContinuousLayout(true);
		this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.setLeftComponent(new TreePane());
		this.setRightComponent(new InfoPane());
		this.setDividerSize(3);
		this.setDividerLocation(200);
		/*this.setLayout(new BorderLayout());
		this.add(new TreePane(), "West");*/
	}
}


