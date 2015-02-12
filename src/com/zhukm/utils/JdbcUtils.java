package com.zhukm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhukm.swing.Config;

/**
 * JDBC����
 * @author zhukm
 *
 */
public class JdbcUtils {
	private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//��������
	public static Connection getConnection(String url ,String user, String password){
		try {
			//1:������
			Class.forName(driverName);
			//2����ȡ����
			Connection connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);	//�����Զ��ύΪfalse����������ع�
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("�Ҳ���jdbc����");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("���Ӵ���ʧ�ܣ��������ӵ�ַ���û�����������ȷ��");
			e.printStackTrace();
		}
		return null;
	}
	
	//�ر���Դ
	public static void closeRS(Connection conn, PreparedStatement pstmt, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("resultset�ر�ʧ��");
				e.printStackTrace();
			}
		}
		
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("preparedStatement�ر�ʧ��");
				e.printStackTrace();
			}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("connection�ر�ʧ��");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取指定数据库中指定表的字段名
	 * @param dbName 数据库名
	 * @param table 表名
	 * @return List<String>形式存放字段名
	 */
	public static List<String> parseDB(String dbName, String table){
		if(!Config.isInit()) return null;
		String server = Config.getValue("server");
		String user = Config.getValue("user");
		String password = Config.getValue("password");
		String url = server + dbName;
		ArrayList<String> params = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = JdbcUtils.getConnection(url, user, password);
			if(conn != null){
				pstmt = conn.prepareStatement("SELECT Name FROM SysColumns WHERE id=Object_Id('" + table + "') ");
				if(pstmt != null){
					rst = pstmt.executeQuery();
					if(rst != null){
						while(rst.next()){
							params.add(rst.getString("Name"));
						}
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.closeRS(conn, pstmt, rst);
		}
		return params;
	}
	
	/**
	 * 将数据库中的数据以String的方式返回
	 * @param dbName 数据库名
	 * @param table 表名
	 * @param params 数据库表字段
	 * @return 以List<List<String>>的方式返回表中的数据
	 */
	public static List<List<String>> getStringRst(String dbName, String table){
		if(!Config.isInit()) return null;
		String server = Config.getValue("server");
		String user = Config.getValue("user");
		String password = Config.getValue("password");
		String url = server + dbName;
		List<String> params = JdbcUtils.parseDB(dbName, table);
		List<List<String>> strRst = new ArrayList<List<String>>();
		List<String> name = new ArrayList<String>();
		name.add(table);
		strRst.add(name);
		strRst.add(params);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = JdbcUtils.getConnection(url, user, password);
			if(conn != null){
				pstmt = conn.prepareStatement("SELECT * FROM " + table);
				if(pstmt != null){
					rst = pstmt.executeQuery();
					if(rst != null){
						while(rst.next()){
							List<String> lsRst = new ArrayList<String>();
							for(String s : params){
								String value = rst.getString(s);
								lsRst.add(value);
							}
							strRst.add(lsRst);
						}
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.closeRS(conn, pstmt, rst);
		}
		return strRst;
	}
}
