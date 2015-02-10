package com.zhukm.swing;

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

public class ScroPane extends JScrollPane {
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=mybatis";
	String user = "sa";
	String password = "ri1yvi2txgt6";
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("数据库列表");
	private InfoPane infoPane;
	// root;
	public ScroPane(InfoPane info) {
		final JTree tree = new JTree(addDB(root));
		
		this.infoPane = info;
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
						if("数据库列表".equals(node.getParent().toString())) return;
						String dbName = node.getParent().toString();
						String table = node.toString();
						List<String> params = JdbcUtils.parseDB(dbName, table);
						List<List<String>> strRst = JdbcUtils.getStringRst(dbName, table, params);
						JTable jt = new JTable(strRst.size()+1,params.size());
						for(int i = 0; i < params.size(); i++){
							jt.setValueAt(params.get(i), 0, i);
						}
						
						for(int i = 0; i < strRst.size(); i++){
							for(int j = 0; j < params.size(); j++){
								jt.setValueAt(strRst.get(i).get(j), i+1, j);
							}
						}
						infoPane.setTable(jt);
					}
				}
			}
			
		});
		this.setViewportView(tree);
	}
	
	/**
	 * 查询所有数据库,并创建以数据库名为名字的树节点,最后加下root节点下
	 * @param root	根节点
	 * @return	
	 */
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
	
	/**
	 * 从数据库中查询所有表,并将表名做为一个Node节点
	 * @param node 父节点
	 * @param name 数据库表
	 * @return 名字为表名的树节点
	 */
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
