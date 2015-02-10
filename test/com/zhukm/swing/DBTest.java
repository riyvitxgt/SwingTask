package com.zhukm.swing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.zhukm.utils.JdbcUtils;

/**
 * 
 * @author zhukm
 *
 * 1.获取所有数据库名: 
SELECT Name FROM Master..SysDatabases ORDER BY Name

2.获取所有表名: 
SELECT Name FROM DatabaseName..SysObjects Where XType='U' ORDER BY Name 
XType='U':表示所有用户表; 
XType='S':表示所有系统表;


3.获取所有字段名: 
SELECT Name FROM SysColumns WHERE id=Object_Id('TableName')
 */
public class DBTest {
	
	private String url;
	private String user;
	private String password;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rst ;
	@Test
	public void testConn(){
		url = "jdbc:sqlserver://localhost:1433;DatabaseName=mybatis";  
		user = "sa";                                                   
		password = "123456";                                           
		try {
			conn = JdbcUtils.getConnection(url, user, password);
			pstmt = conn.prepareStatement("SELECT Name FROM Master..SysDatabases ORDER BY Name");
			if(pstmt != null){
				rst = pstmt.executeQuery();
				if(rst != null){
					while(rst.next()){
						System.out.println(rst.getString("Name"));
						System.out.println(rst.getString("Name") instanceof String);
					}
				}
			}
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testString(){
		String a = "aaa";
		change(a);
		System.out.println(a);
	}
	
	public void change(String a){
		a = "dddd";
	}
	
	@Test
	public void testGetParam(){
		List<String> list = new ArrayList<String>();
		list = JdbcUtils.parseDB("JD2011_WisdomCommunity", "t_orderDetail");
		List<List<String>> strList = JdbcUtils.getStringRst("JD2011_WisdomCommunity", "t_orderDetail");
		for(List<String> l : strList){
			for(String s : l){
				System.out.print(s + "  ");
			}
			System.out.println();
		}
	}
}
