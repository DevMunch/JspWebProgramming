package com.ssamz.biz.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectUserTest1 {
    public static void main(String[] args) {
        // JDBC 관련 변수
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = JDBCUtil.getConnecttion();

            // JDBC 3단계 : Statement 생성
            String sql = "select * from users";
            stmt = conn.prepareStatement(sql);

            // JDBC 4단계 : SQL 전송
            rs = stmt.executeQuery();

            // JDBC 5단계 : 조회 결과 사용
//            System.out.println("[USER 목록]");
//            rs.next();
//            System.out.print(rs.getString("ID") + " : ");
//            System.out.print(rs.getString("PASSWORD") + " : ");
//            System.out.print(rs.getString("NAME") + " : ");
//            System.out.print(rs.getString("ROlE"));

            // JDBC 5단계 : 조회 결과 사용, 모든 유저 출력
            System.out.println("[USER 목록]");
            while(rs.next()){
                System.out.print(rs.getString("ID") + " : ");
                System.out.print(rs.getString("PASSWORD") + " : ");
                System.out.print(rs.getString("NAME") + " : ");
                System.out.println(rs.getString("ROlE"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }
}
