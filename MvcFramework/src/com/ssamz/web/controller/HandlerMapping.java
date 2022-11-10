package com.ssamz.web.controller;

import com.ssamz.web.controller.board.*;
import com.ssamz.web.controller.user.*;

import java.util.HashMap;
import java.util.Map;

// 클라리언트의 요청이 들어오면 요청을 처리할 적절한 컨트롤러를 매핑한다.
public class HandlerMapping {

    // Controller를 구현한 객체들을 저장하는 Map
    private Map<String, Controller> mappings;

    public HandlerMapping(){
        // Key-Value 형태로 수많은 Controller를 등록한다.
        mappings = new HashMap<String, Controller>();
        mappings.put("/insertUserView.do", new InsertUserViewController());
        mappings.put("/insertUser.do", new InsertUserController());
        mappings.put("/loginView.do", new LoginViewController());
        mappings.put("/login.do", new LoginController());
        mappings.put("/getBoardList.do", new GetBoardListController());
        mappings.put("/getBoard.do", new GetBoardController());
        mappings.put("/insertBoardView.do", new InsertBoardViewController());
        mappings.put("/insertBoard.do", new InsertBoardController());
        mappings.put("/updateBoard.do", new UpdateBoardController());
        mappings.put("/deleteBoard.do", new DeleteBoardController());
        mappings.put("/logout.do", new LogoutController());
    }

    public Controller getController(String path){
        // Map에 등록된 Controller들 중에서
        // 특정 경로(path)에 해당하는 Controller를 리턴한다.
        return mappings.get(path);
    }
}
