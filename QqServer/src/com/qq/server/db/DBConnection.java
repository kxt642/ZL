/**
 * 这个类主要用于连接数据库和关闭连接
 * 修改时间:3-26
 * 修改人：tkx
 * 没有弄好
 */

package com.qq.server.db;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DBConnection 
{
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //驱动程序
    private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=ZL"; //JDBC数据源
    private String username = "tkx"; //用户名
    private String password = "12306"; //密码
    private Connection con = null;

    public DBConnection() 
    {
        Properties p = new Properties(); //有问题？
        try 
        {
            //获取文件的分隔符
            String file_separator = System.getProperty("file.separator");
            //System.out.println(file_separator);
            FileInputStream in = new FileInputStream("property" +
                    file_separator + "dbProperties.txt");
            p.load(in); //从输入流中读取属性列表
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
    
  //创建数据库连接
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
    
  //关闭数据库连接
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
