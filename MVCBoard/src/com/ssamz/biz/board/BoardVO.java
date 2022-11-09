package com.ssamz.biz.board;

//import lombok.*;
import lombok.Data;

import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Date;


@Data
public class BoardVO {
    private int seq;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private int cnt;

    // 검색 관련 변수
    private String searchCondition;
    private String searchKeyword;
    
    public void valueBound(HttpSessionBindingEvent event){
        System.out.println("---> UserVO 객체가 세션에 등록됨");
    }

    public void valueUnbound(HttpSessionBindingEvent event){
        System.out.println("---> UserVO 객체가 세션에서 제거됨");
    }
}
