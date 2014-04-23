/**
 * �������Ҫ�����������ݿ�͹ر�����
 * �޸�ʱ��:3-26
 * �޸��ˣ�tkx
 * û��Ū��
 */

package com.qq.server.db;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DBConnection 
{
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //��������
    private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=ZL"; //JDBC����Դ
    private String username = "tkx"; //�û���
    private String password = "12306"; //����
    private Connection con = null;

    public DBConnection() 
    {
        Properties p = new Properties(); //�����⣿
        try 
        {
            //��ȡ�ļ��ķָ���
            String file_separator = System.getProperty("file.separator");
            //System.out.println(file_separator);
            FileInputStream in = new FileInputStream("property" +
                    file_separator + "dbProperties.txt");
            p.load(in); //���������ж�ȡ�����б�
            driver = p.getProperty("jdbc.driver");
            url = p.getProperty("jdbc.url");
            username = p.getProperty("username");
            password = p.getProperty("password");
            System.out.println(driver + " " + url + "  " + password);
            in.close();
        } catch (FileNotFoundException ex) 
        {
            ex.printStackTrace();
        } catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    public DBConnection(String driver, String url, String username,
            String password) 
    {
    	this.driver = driver;
    	this.url = url;
    	this.username = username;
    	this.password = password;
    }
    
  //�������ݿ�����
    public Connection makeConnection() 
    {
        con = null;
        try 
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) 
        {
            sqle.printStackTrace();
        } catch (ClassNotFoundException ex) 
        {
            ex.printStackTrace();
        }
        return con;
    }
    
  //�ر����ݿ�����
    public void closeConnection() 
    {
        try 
        {
            con.close();
        } catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }
    
}
