package com.ssamz.biz.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectUserTest2 {
    public static void main(String[] args) {
        // JDBC 관련 변수
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = JDBCUtil.getConnecttion();

            // JDBC 3단계 : Statement 생성
            String sql = "select * from users where role = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "ADMIN");

            // JDBC 4단계 : SQL 전송
            rs = stmt.executeQuery();

            // JDBC 5단계 : 조회 결과 사용
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
