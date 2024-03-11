package com.bitc.java501_team4.controller;

import com.bitc.java501_team4.dto.community.QnaDTO;
import com.bitc.java501_team4.dto.community.ReviewBoardDTO;
import com.bitc.java501_team4.service.MypageService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class MypageController {


    @Autowired
    private MypageService mypageService;

    @GetMapping("/mypage/mypage")
    public ModelAndView mypage(HttpServletRequest req,@RequestParam(required = false, defaultValue = "1") int pageNum ,@RequestParam(required = false, defaultValue="1") int pageNum2) throws Exception{
        ModelAndView mv = new ModelAndView("mypage/mypage");


        HttpSession session = req.getSession();
        String uId = (String) session.getAttribute("uId");
        String uName = (String) session.getAttribute("uName");


        PageInfo<QnaDTO> userQnaListPaging = new PageInfo<>(mypageService.userQnaListPaging(pageNum,uId), 5);
        PageInfo<ReviewBoardDTO> userReviewListPaging = new PageInfo<>(mypageService.userReviewListPaging(pageNum2,uId), 5);



        if (uId == null || uName == null) {
            // null일시 로그인페이지로이동
//            mv.setViewName("redirect:/loginlogin");
        }

        mv.addObject("uId", uId);
        mv.addObject("uName", uName);
        mv.addObject("userQnaListPaging", userQnaListPaging);
        mv.addObject("userReviewListPaging", userReviewListPaging);

        return mv;
    }

    @GetMapping("/mypage/userQnaPaging")
    public ModelAndView mypageQnaPaging(HttpServletRequest req,@RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception{
        ModelAndView mv = new ModelAndView("mypage/mypage");

        HttpSession session = req.getSession();
        String uId = (String) session.getAttribute("uId");
        String uName = (String) session.getAttribute("uName");

        List<QnaDTO> userQnaList = mypageService.userQnaList(uId);

        PageInfo<QnaDTO> userQnaListPaging = new PageInfo<>(mypageService.userQnaListPaging(pageNum,uId), 5);


        if (uId == null || uName == null) {
            // null일시 로그인페이지로이동
//            mv.setViewName("redirect:/loginlogin");
        }

        mv.addObject("uId", uId);
        mv.addObject("uName", uName);
        mv.addObject("userQnaListPaging", userQnaListPaging);

        mv.addObject("userQnaList", userQnaList);

        return mv;
    }



    @GetMapping("/mypage/userReviewPaging")
    public ModelAndView mypageReviewPaging(HttpServletRequest req,@RequestParam(required = false, defaultValue = "1") int pageNum2) throws Exception{
        ModelAndView mv = new ModelAndView("mypage/mypage");


        HttpSession session = req.getSession();
        String uId = (String) session.getAttribute("uId");
        String uName = (String) session.getAttribute("uName");


        List<QnaDTO> userQnaList = mypageService.userQnaList(uId);
        List<ReviewBoardDTO> userReviewList = mypageService.userReviewList(uId);
        PageInfo<ReviewBoardDTO> userReviewListPaging = new PageInfo<>(mypageService.userReviewListPaging(pageNum2,uId), 5);

        if (uId == null || uName == null) {
            // null일시 로그인페이지로이동
//            mv.setViewName("redirect:/loginlogin");
        }

        mv.addObject("uId", uId);
        mv.addObject("uName", uName);
        mv.addObject("userReviewListPaging", userReviewListPaging);
        mv.addObject("userReviewList", userReviewList);


        return mv;
    }




}
