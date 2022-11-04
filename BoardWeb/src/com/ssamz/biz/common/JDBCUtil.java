package com.ssamz.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
    public static Connection getConnecttion(){
        Connection conn = null;
        try{
            // JDBC 1단계 : 드라이버 객체 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //2. DB 서버 접속하기
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String userId = "songimyeong";
            String userPwd = "1234";
            conn = DriverManager.getConnection(url, userId, userPwd);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(PreparedStatement stmt, Connection conn){
        // JDBC 5단계 : 연결 해제
        try{
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn){
        // JDBC 5단계 : 연결 해제
        try{
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
