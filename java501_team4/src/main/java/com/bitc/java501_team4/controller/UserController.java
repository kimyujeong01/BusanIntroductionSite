package com.bitc.java501_team4.controller;

import com.bitc.java501_team4.dto.CountDTO;
import com.bitc.java501_team4.dto.UserDTO;
import com.bitc.java501_team4.dto.community.ReviewBoardDTO;
import com.bitc.java501_team4.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/member/membership")
    public String membership() throws Exception {
        return "member/membership";
    }

    @ResponseBody
    @PostMapping(value = "/member/membership")
    public String memberInsertProcess(UserDTO user) throws Exception {
        String msg = "";

        String userId = user.getUId();
        String userPw = user.getUPw();
        String userName = user.getUName();

        if ((userId == "") || (userPw == "") || (userName == "")) {
            if (userId == "") {
                msg = "id";
            } else if (userPw == "") {
                msg = "pw";
            } else if (userName == "") {
                msg = "name";
            }
        } else {
            int cnt = userService.cntUser(user);

            if (cnt == 0) {
                userService.insertUser(user);
                msg = "/login/login";
            } else {
                msg = "no";
            }
        }

        return msg;
    }


    @GetMapping("/login/login")
    public String login() throws Exception {
        return "login/login";
    }

    @ResponseBody
    @RequestMapping(value = "/login/loginProcess", method = RequestMethod.POST)
    public String loginProcess(@RequestParam("uId") String uId, @RequestParam("uPw") String uPw, HttpServletRequest req) throws Exception {
        int result = userService.isUserInfo(uId, uPw);
        String msg = "";

        if (result == 1) {
            UserDTO user = userService.getUserInfo(uId);
            HttpSession session = req.getSession();
            session.setAttribute("uId", user.getUId());
            session.setAttribute("uName", user.getUName());
            session.setAttribute("uYn",user.getUYn());

            msg = "OK";
        } else {
            msg = "NO";
        }

        return msg;
    }

    @GetMapping("/login/logoutProcess")
    public String logoutProcess(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        session.removeAttribute("uId");
        session.removeAttribute("uName");
        session.removeAttribute("uYn");

        session.invalidate();

        return "redirect:/festival/pj_main";
    }
    
    
    
    // 관리자 관련
    @GetMapping("/manager/manager")
    public ModelAndView manager(@RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("/manager/manager");

        CountDTO allCnt = userService.allCount();
        PageInfo<UserDTO> userPageList = new PageInfo<>(userService.selectUserPageList(pageNum), 15);

        mv.addObject("userPageList", userPageList);
        mv.addObject("allCnt",allCnt);

        return mv;
    }

    @GetMapping("/manager/check/{uIdx}/{uYn}")
    public String userCheck(@PathVariable("uIdx") int uIdx,@PathVariable("uYn") String uYn) throws Exception {
        String yn = "";

        if (uYn.equals("N")){
            yn = "Y";
        }else{
            yn = "N";
        }

        userService.updateUserCkeck(uIdx,yn);

        return "redirect:/manager/manager";
    }




}
