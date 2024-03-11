package com.bitc.java501_team4.controller;

import com.bitc.java501_team4.dto.community.*;
import com.bitc.java501_team4.service.BoardService;
import com.bitc.java501_team4.service.CommunityService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.util.List;

@Controller
@RequestMapping("/com")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/review")
    public ModelAndView reviewBoard(@RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("/community/reviewBoard");

        PageInfo<ReviewBoardDTO> reviewPageList = new PageInfo<>(communityService.selectBoardPageList(pageNum), 10);
        mv.addObject("reviewPageList", reviewPageList);

        return mv;
    }


    @GetMapping("/reviewDetail/{rIdx}")
//    @ResponseBody
    public ModelAndView reviewDetail(@PathVariable("rIdx") int rIdx) throws Exception {
        ModelAndView mv = new ModelAndView("/community/reviewDetail");

        communityService.updateReviewCount(rIdx);

        ReviewBoardDTO reviewBoard = communityService.selectBoardDetail(rIdx);
        List<ReviewCommentDTO> revComList = communityService.selectCommentDetail(rIdx);

        mv.addObject("reviewBoard", reviewBoard);
        mv.addObject("revComList", revComList);
        return mv;
    }

    @GetMapping("/reviewWrite")
    public String reviewWrite() throws Exception {
        return "/community/reviewWrite";
    }

    @PostMapping("/reviewWrite")
    public String reviewWriteProcess(ReviewBoardDTO reviewBoard, MultipartHttpServletRequest multipart) throws Exception {
        System.out.printf(reviewBoard.getRCreateId());
        communityService.writeReview(reviewBoard,multipart);

        return "redirect:/com/review";
    }

    @PostMapping("/reviewUpdate/{rIdx}")
    public String reviewUpdate(ReviewBoardDTO board, MultipartHttpServletRequest multipart) throws Exception {
        communityService.updateReview(board,multipart);

        return "redirect:/com/review";
    }

    @PostMapping("/reviewDelete/{rIdx}")
    public String reviewDelete(@PathVariable("rIdx") int rIdx) throws Exception {

        communityService.deleteRevAllment(rIdx);
        communityService.deleteReview(rIdx);

        return "redirect:/com/review";
    }

    @PostMapping("/review/ment")
    public String reviewCommentInsert(ReviewCommentDTO revComm) throws Exception {
        communityService.commentReviewWrite(revComm);

        return "redirect:/com/reviewDetail/" + revComm.getRevBoardIdx();
    }

    @GetMapping("/review/delete/{revIdx}/{revBoardIdx}")
    public String reviewCommentDelete(@PathVariable("revIdx") int revIdx, @PathVariable("revBoardIdx") int revBoardIdx) throws Exception {
        communityService.commentReviewDelete(revIdx, revBoardIdx);

        return "redirect:/com/reviewDetail/" + revBoardIdx;
    }

    @GetMapping("/downloadReivewFile")
    public void downloadReivewFile(@RequestParam("idx")int idx,@RequestParam("rIdx")int rIdx, HttpServletResponse res) throws Exception{
        reviewFileDTO reviewFIle = communityService.selectFileInfo(idx,rIdx);

        if (!ObjectUtils.isEmpty(reviewFIle)){
            String fileName = reviewFIle.getOFile();
            byte[] files = FileUtils.readFileToByteArray(new File(reviewFIle.getSFile()));

            res.setContentType("application/octet-stream");
            res.setContentLength(files.length);
            res.setHeader("Content-Disposition","attachment; fileName=\""+ URLEncoder.encode(fileName,"UTF-8")+"\"");
            res.getOutputStream().write(files);
            res.getOutputStream().flush();
            res.getOutputStream().close();
        }
    }


    @GetMapping("/qna")
    public ModelAndView qnaList(@RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
        ModelAndView mv = new ModelAndView("community/qnaBoard");

        PageInfo<QnaDTO> qnaList = new PageInfo<>(communityService.selectQnaList(pageNum), 10);
        mv.addObject("qnaList", qnaList);

        return mv;
    }


    @GetMapping("/qna/{qIdx}")
    public ModelAndView qnaDetail(@PathVariable("qIdx") int qIdx) throws Exception {
        ModelAndView mv = new ModelAndView("community/qnaDetail");


        QnaDTO qna = communityService.selectQnaDetail(qIdx);
        List<QnaComDTO> qnaComList = communityService.selectQnaComList(qIdx);

        mv.addObject("qna", qna);
        mv.addObject("qnaComList", qnaComList);

        return mv;
    }

    @GetMapping("/qna/insert")
    public String qnaInsert() throws Exception {
        return "community/qnaInsert";
    }


    @PostMapping("/qna/insert")
    public String qnaInsertProcess(QnaDTO qna) throws Exception {
        communityService.insertQna(qna);

        return "redirect:/com/qna";
    }

    @PutMapping("/qna/{qIdx}")
    public String boardUpdate(QnaDTO qna) throws Exception {
        communityService.updateQna(qna);

        return "redirect:/com/qna";
    }

    @DeleteMapping("/qna/{qIdx}")
    public String boardDelete(@PathVariable("qIdx") int qIdx) throws Exception {
        communityService.deleteAllMent(qIdx);
        communityService.deleteQna(qIdx);

        return "redirect:/com/qna";
    }

    @PostMapping("/qna/ment")
    public String mentInsert(QnaComDTO ment) throws Exception {
        communityService.insertMent(ment);

        return "redirect:/com/qna/" + ment.getQcBoardIdx();
    }

    @GetMapping("/qna/del/{qcIdx}/{qcBoardIdx}")
    public String mentDelete(@PathVariable("qcIdx") int qcIdx, @PathVariable("qcBoardIdx") int qcBoardIdx) throws Exception {
        communityService.deleteMent(qcIdx, qcBoardIdx);

        return "redirect:/com/qna/" + qcBoardIdx;
    }


    @GetMapping("/pass")
    public ModelAndView passBody(@RequestParam int qIdx) throws Exception {
        ModelAndView mv = new ModelAndView("pass/pass");
        mv.addObject("qIdx", qIdx);

        return mv;
    }

    @ResponseBody
    @PostMapping("/pass/passProcess")
    public String passProcess(@RequestParam("qCreateId") String qCreateId, @RequestParam("qIdx") int qIdx) throws Exception {
        String msg = "";
        int cnt = communityService.cntUserId(qCreateId);
        QnaDTO qna = communityService.selectQnaDetail(qIdx);

        if (cnt == 1) {
            msg = null;
        } else {
            if (qCreateId.equals(qna.getQCreateId())) {
                msg = "/com/qna/" + qIdx;
            } else {
               msg = "no";
            }
        }

        return msg;
    }


}
