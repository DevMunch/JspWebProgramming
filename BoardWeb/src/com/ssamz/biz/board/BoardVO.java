package com.ssamz.biz.board;

//import lombok.*;
import lombok.Data;

import java.util.Date;

//@NoArgsConstructor // 기본 생성자 추가
//@AllArgsConstructor // 모든 멤버 변수를 초기화하는 생성자 추가
//@Getter
//@Setter
//@ToString
@Data // 모든 어노테이션을 포함한다.
// VO(Value Object) 클래스
public class BoardVO {
    private int seq;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private int cnt;
}
