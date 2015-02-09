package com.zhukm.swing;

import java.awt.Color;
import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;


public class ScroPane extends JScrollPane {
	File f = new File("C:\\Foxmail 7.2\\Storage\\zhukm@gildata.com");
	DefaultMutableTreeNode root = new DefaultMutableTreeNode(f.getName());
	//root;
	public ScroPane(){
		JTree tree = new JTree(createNode(f, root));
		this.setViewportView(tree);
	}
	
	public DefaultMutableTreeNode createNode(File dir, DefaultMutableTreeNode root){
		File[] files = dir.listFiles();
		for(File file : files){
			DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(file.getName());
			root.add(treeNode);
			if(file.isDirectory()){
				createNode(file,treeNode);
			}
		}
		
		return root;
	}
}


