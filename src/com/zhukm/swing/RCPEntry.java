package com.zhukm.swing;

import javax.swing.tree.DefaultMutableTreeNode;

import com.zhukm.utils.TreeUtils;

public class RCPEntry {
	public static void main(String[] args){
		Config.init();
		MainFrame frame = new MainFrame();
		
		Thread  getTree = new Thread(new Runnable(){
			@Override
			public void run() {
				DefaultMutableTreeNode root = new DefaultMutableTreeNode("数据库列表");
				ComponentCache.addComponent("root", TreeUtils.addDB(root));
				
				System.out.println((DefaultMutableTreeNode)ComponentCache.getComponent("root"));
			}
		});
		
		getTree.start();
	}
}
