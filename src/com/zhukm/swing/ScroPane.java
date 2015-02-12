package com.zhukm.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.zhukm.utils.JdbcUtils;
import com.zhukm.utils.TreeUtils;

public class ScroPane extends JScrollPane {
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("数据库列表");
	private InfoPane infoPane;
	// root;
	public ScroPane(InfoPane info) {
		final JTree tree = new JTree(TreeUtils.addDB(root));
		
		this.infoPane = info;
		tree.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				int sum = e.getClickCount();
				//双击事件产生时将对应数据库表的数据写入到JTable中
				if(sum >1){
					//获取到点击的那个树节点
					int n = tree.getRowForLocation(e.getX(), e.getY());
					if(n < 0) return;
					TreePath  selTree = tree.getPathForRow(n);
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)selTree.getLastPathComponent();
					if(node.isLeaf()){
						if("数据库列表".equals(node.getParent().toString())) return;
						String dbName = node.getParent().toString();
						String table = node.toString();
						List<List<String>> strRst = JdbcUtils.getStringRst(dbName, table);
						int col = strRst.get(1).size();
						JTable jt = new JTable(strRst.size(),col);
						
						for(int i = 0; i < strRst.size(); i++){
							for(int j = 0; j < col; j++){
								if(i == 0){
									jt.setValueAt(strRst.get(0).get(0), 0, col/2);
									break;
								}else{
									jt.setValueAt(strRst.get(i).get(j), i, j);
								}
							}
						}
						infoPane.setTable(jt);
					}
				}
			}
			
		});
		
		this.setPreferredSize(new Dimension(250,600));
		this.setViewportView(tree);
	}
	
	
}
