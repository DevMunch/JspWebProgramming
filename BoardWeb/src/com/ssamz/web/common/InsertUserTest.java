package com.ssamz.web.common;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertUserTest {
    public static void main(String[] args) {
        // JDBC 관련 변수
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = JDBCUtil.getConnecttion();
            // JDBC 3단계 : Statement or PreparedStatement 객체 생성하기
            String sql = "insert into users values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            // ? 값 설정
            stmt.setString(1, "gest1");
            stmt.setString(2, "gest1231");
            stmt.setString(3, "방문자1");
            stmt.setString(4, "USER1");

            // JDBC 4단계 : SQL 실행
            int count = stmt.executeUpdate();
            System.out.println(count + "건 데이터 처리 성공!");

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }
}
