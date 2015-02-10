package com.zhukm.swing;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

public class PortalPane extends JSplitPane {
	private TreePane treePane;
	private InfoPane infoPane;
	public PortalPane(){
		infoPane = new InfoPane();
		treePane = new TreePane(infoPane);
		this.setOneTouchExpandable(true);
		this.setContinuousLayout(true);
		this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.setLeftComponent(treePane);
		this.setRightComponent(infoPane);
		this.setDividerSize(3);
		this.setDividerLocation(200);
	}
	public TreePane getTreePane() {
		return treePane;
	}
	public void setTreePane(TreePane treePane) {
		this.treePane = treePane;
	}
	public InfoPane getInfoPane() {
		return infoPane;
	}
	public void setInfoPane(InfoPane infoPane) {
		this.infoPane = infoPane;
	}
	
}


