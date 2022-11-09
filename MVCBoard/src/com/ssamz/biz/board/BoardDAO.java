package com.ssamz.biz.board;

import com.ssamz.web.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    // 검색 관련 쿼리
    private static String BOARD_LIST_T = "select * from board where title like '%'||?||'%' order by seq desc";
    private static String BOARD_LIST_C = "select * from board where content like '%'||?||'%' order by seq desc";

    // JDBC 관련 변수
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    // USERS 테이블 관련 SQL 명령어
    private static String BOARD_LIST = "select * from board order by seq desc";
    private static String BOARD_INSERT = "insert into board(seq, title, writer, content) values((select nvl(max(seq),0) + 1 from board), ?, ?, ?)";
    private static String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
    private static String BOARD_DELETE = "delete board where seq=?";
    private static String BOARD_GET = "select * from board where seq = ?";
    
    // USERS 테이블 관련 CRUD 메서드
    // 글 등록
    public void insertBoard(BoardVO vo){
        try{
            conn = JDBCUtil.getConnecttion();
            stmt = conn.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getWriter());
            stmt.setString(3, vo.getContent());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 글 수정
    public void updateBoard(BoardVO vo){
        try{
            conn = JDBCUtil.getConnecttion();
            stmt = conn.prepareStatement(BOARD_UPDATE);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getContent());
            stmt.setInt(3, vo.getSeq());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(stmt, conn);
        }
    }
    
    // 글 삭제
    public void deleteBoard(BoardVO vo){
        try{
            conn = JDBCUtil.getConnecttion();
            stmt = conn.prepareStatement(BOARD_DELETE);
            stmt.setInt(1, vo.getSeq());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 글 상세 조회
    public BoardVO getBoard(BoardVO vo){
        BoardVO board = null;
        try{
            conn = JDBCUtil.getConnecttion();
            stmt = conn.prepareStatement(BOARD_GET);
            stmt.setInt(1,vo.getSeq());
            rs = stmt.executeQuery();
            while (rs.next()){
                board = new BoardVO();
                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("TITLE"));
                board.setWriter(rs.getString("WRITER"));
                board.setContent(rs.getString("CONTENT"));
                board.setRegDate(rs.getDate("REGDATE"));
                board.setCnt(rs.getInt("CNT"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,stmt,conn);
        }
        return board;
    }
    
    // 글 목록 조회
    public List<BoardVO> getBoardList(BoardVO vo){
        List<BoardVO> boardList = new ArrayList<BoardVO>();
        try{
            conn = JDBCUtil.getConnecttion();
            if(vo.getSearchCondition().equals("TITLE")){
                stmt = conn.prepareStatement(BOARD_LIST_T);
            }else if(vo.getSearchCondition().equals("CONTENT")){
                stmt = conn.prepareStatement(BOARD_LIST_C);
            }
            stmt.setString(1, vo.getSearchKeyword());
            rs = stmt.executeQuery();
            while (rs.next()){
                BoardVO board = new BoardVO();
                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("TITLE"));
                board.setWriter(rs.getString("WRITER"));
                board.setContent(rs.getString("CONTENT"));
                board.setRegDate(rs.getDate("REGDATE"));
                board.setCnt(rs.getInt("CNT"));
                boardList.add(board);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,stmt,conn);
        }
        return boardList;
    }
}
