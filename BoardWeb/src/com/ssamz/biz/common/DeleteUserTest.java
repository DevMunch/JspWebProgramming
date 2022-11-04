package com.ssamz.biz.common;

import com.ssamz.biz.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteUserTest {
    public static void main(String[] args) {
        // JDBC 관련 변수
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = JDBCUtil.getConnecttion();

            // JDBC 3단계 : Statement 생성
            String sql = "delete users where id = ?";
            stmt = conn.prepareStatement(sql);

            // JDBC 4단계 : SQL 전송
            // ? 값 설정
            stmt.setString(1, "gest1");

            // SQL 전송
            int count = stmt.executeUpdate();
            System.out.println(count + "건 데이터 처리 성공!");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

    }
}
