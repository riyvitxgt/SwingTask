package com.zhukm.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.DefaultMutableTreeNode;

import com.zhukm.swing.Config;

public class TreeUtils {
	/**
	 * 查询所有数据库,并创建以数据库名为名字的树节点,最后加下root节点下
	 * @param root	根节点
	 * @return	
	 */
	public static DefaultMutableTreeNode addDB(DefaultMutableTreeNode root){
		if(!Config.isInit()) return null;
		String user = Config.getValue("user");
		String password = Config.getValue("password");
		String url = Config.getValue("defaultDB");
		
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
						//如果是tempdb就不加入到树节点中去
						if(!"tempdb".equals(rst.getString("Name"))){
							root.add(addTable(db,rst.getString("Name")));
						}
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
	public static DefaultMutableTreeNode addTable(DefaultMutableTreeNode node, String name){
		if(!Config.isInit()) return null;
		String server = Config.getValue("server");
		String user = Config.getValue("user");
		String password = Config.getValue("password");
		String uri = server + name;
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
