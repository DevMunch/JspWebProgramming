package com.ssamz.web.user;

import com.ssamz.biz.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // JDBC 관련 변수
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    // USERS 테이블 관련 SQL 명령어
    private static String USER_LIST = "select * from users";
    private static String USER_INSERT = "insert into users values(?,?,?,?)";
    private static String USER_UPDATE = "update users set name=?,role=? where id=?";
    private static String USER_DELETE = "delete users where id=?";
    private static String USER_GET = "select * from users where id = ?";
    // USERS 테이블 관련 CRUD 메서드
    // 회원 상세 조회
    public UserVO getUser(UserVO vo){
        UserVO user = null;
        try{
            conn = JDBCUtil.getConnecttion();
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1,vo.getId());
            rs = stmt.executeQuery();
            while (rs.next()){
                user = new UserVO();
                user.setId(rs.getString("ID"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setRole(rs.getString("ROLE"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,stmt,conn);
        }
        return user;
    }
    // 회원 삭제
    public void deleteUser(String id){
        try{
            conn = JDBCUtil.getConnecttion();
            stmt = conn.prepareStatement(USER_DELETE);
            stmt.setString(1, id);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(stmt, conn);
        }
    }
    // 회원 수정
    public void updateUser(UserVO vo){
        try{
            conn = JDBCUtil.getConnecttion();
            stmt = conn.prepareStatement(USER_UPDATE);
            stmt.setString(1, vo.getName());
            stmt.setString(2, vo.getRole());
            stmt.setString(3, vo.getId());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(stmt, conn);
        }
    }
    // 회원 등록
    public void insertUser(UserVO vo){
        try{
            conn = JDBCUtil.getConnecttion();
            stmt = conn.prepareStatement(USER_INSERT);

            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            stmt.setString(3, vo.getName());
            stmt.setString(4, vo.getRole());

            int count = stmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }
    // 회원 목록 조회
    public List<UserVO> getUserList(){
        List<UserVO> userList = new ArrayList<UserVO>();
        try{
            conn = JDBCUtil.getConnecttion();
            stmt = conn.prepareStatement(USER_LIST);
            rs = stmt.executeQuery();
            System.out.println("[ USER 목록 ] ");
            while (rs.next()){
                UserVO user = new UserVO();
                user.setId(rs.getString("ID"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setRole(rs.getString("ROLE"));
                userList.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,stmt,conn);
        }
        return userList;
    }
}
