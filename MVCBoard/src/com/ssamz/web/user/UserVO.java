package com.ssamz.web.user;

import lombok.Data;

@Data
public class UserVO {
    // private 멤버 변수 선언
    private String id;
    private String password;
    private String name;
    private String role;
}
