/**
 * ���ݿ������
 * ʱ�䣺3-18
 * ���ߣ�tkx
 * 
 */

package com.qq.server.db;

import java.sql.*;



public class CommonaJdbc {
	public static void main(String [] args)
	 {
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=ZL";
		String userName="tkx";
		String userPwd="12306";
		try
		{
			Class.forName(driverName);
			System.out.println("���������ɹ���");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("��������ʧ�ܣ�");
		}
		try{
			Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
				System.out.println("�������ݿ�ɹ���");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("SQL Server����ʧ�ܣ�");
		}	
		
	
	 }
}



/**
 * java����sql2008
 * 3-18
 * û�гɹ�
 */

/*
public class CommonaJdbc 
{
	private static Connection con = null;
	private static Statement st = null;	
	private static ResultSet rs = null;
	
	private static void dbconn()
	{
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		} catch (java.lang.ClassNotFoundException classnotfound) {
			classnotfound.printStackTrace();
		}
		
		String conURL = "jdbc:sqlserver://localhost:1433;DatabaseName=NWPUMQ ";
		String userName="tkx";
		String userPwd="12306";
		try 
		{
			con=DriverManager.getConnection(conURL, userName, userPwd);
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		private static void  dbclose()
		{
			try 
			{
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			st=null;
			con=null;
		}
		
		public static ResultSet datasSet(String sql)
		{
			dbconn();
			try 
			{
				rs=st.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
		}
		
		public int update(String sql)
		{
			dbconn();
			int i=0;
			try 
			{
				i=st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbclose();
			return i;
		}
		
		public static void main(String[] args)
		{
			dbconn();
			String sql = "select * from [User]";
			ResultSet r1 = datasSet(sql);
			try 
			{
				while (r1.next()) 
				{
					System.out.println(r1.getString("U_ID")+""+r1.getString("U_NickName")+"\n");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbclose();
		}
	}
	*/
	
	
    
    
    
    
   
