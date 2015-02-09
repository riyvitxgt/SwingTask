package com.zhukm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
