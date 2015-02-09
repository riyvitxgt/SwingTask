package com.zhukm.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.zhukm.utils.JdbcUtils;

public class ScroPane extends JScrollPane {
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=mybatis";
	String user = "sa";
	String password = "123456";
	File f = new File("C:\\Foxmail 7.2\\Storage\\zhukm@gildata.com");
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("数据库列表");

	// root;
	public ScroPane() {
		final JTree tree = new JTree(addDB(root));
		tree.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int sum = e.getClickCount();
				if(sum >1){
					int n = tree.getRowForLocation(e.getX(), e.getY());
					if(n < 0) return;
					TreePath  selTree = tree.getPathForRow(n);
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)selTree.getLastPathComponent();
					if(node.isLeaf()){
						System.out.println(node);
					}
				}
			}
			
		});
		this.setViewportView(tree);
	}

	public DefaultMutableTreeNode createNode(File dir,
			DefaultMutableTreeNode root) {
		File[] files = dir.listFiles();
		for (File file : files) {
			DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(
					file.getName());
			root.add(treeNode);
			if (file.isDirectory()) {
				createNode(file, treeNode);
			}
		}

		return root;
	}

	public DefaultMutableTreeNode addDB(DefaultMutableTreeNode root){
		Connection conn = null;        
		PreparedStatement pstmt = null;
		ResultSet rst = null;  
		
		try {
			conn = JdbcUtils.getConnection(url, user, password);
			pstmt = conn.prepareStatement("SELECT Name FROM Master..SysDatabases ORDER BY Name");
			if(pstmt != null){
				rst = pstmt.executeQuery();
				if(rst != null){
					while(rst.next()){
						DefaultMutableTreeNode db = new DefaultMutableTreeNode(rst.getString("Name"));
						root.add(addTable(db,rst.getString("Name")));
					}
				}
			}
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.closeRS(conn, pstmt, rst);
		}
		return root;
	}
	
	public DefaultMutableTreeNode addTable(DefaultMutableTreeNode node, String name){
		String uri = "jdbc:sqlserver://localhost:1433;DatabaseName=" + name;
		Connection conn = null;        
		PreparedStatement pstmt = null;
		ResultSet rst =null; 
		try {
			conn = JdbcUtils.getConnection(uri, user, password);
			pstmt = conn.prepareStatement("select name from sysobjects where xtype='U'");
			if(pstmt != null){
				rst = pstmt.executeQuery();
				if(rst != null){
					while(rst.next()){
						DefaultMutableTreeNode table = new DefaultMutableTreeNode(rst.getString("name"));
						node.add(table);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.closeRS(conn, pstmt, rst);
		}
		return node;
	}
}
