package com.bitc.java501_team4.controller;

import com.bitc.java501_team4.dto.festival.FestaItemDTO;
import com.bitc.java501_team4.dto.sights.SightsItemDTO;
import com.bitc.java501_team4.dto.weather.WeatherItemDTO;
import com.bitc.java501_team4.service.BoardService;
import com.bitc.java501_team4.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private WeatherService weatherService;

    @Value("AUIh5hIEboiMc%2FZLcDpczV%2BCf7FPo1e8CHSo0kb8ejd5IWHsX7AhgdcUzzGUsZgzoBZIu0iN1eA82qvQq%2FbLlA%3D%3D")
    private String serviceMyKey;


    @RequestMapping("/")
    public String index() throws Exception {
        return "festival/pj_main";
    }

    @GetMapping("/festival/pj_main")
    public String pj_main() throws Exception {
        return "festival/pj_main";
    }

    @ResponseBody
    @PostMapping("/festival/pj_main")
    public Object WeatherList() throws Exception {

        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();

        // 출력 형식 설정 (yyyMMdd)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 현재 날짜를 문자열로 변환
        String formattedDate = currentDate.format(formatter);

        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
        String optKey = "?serviceKey=";
        String opt1 = "&pageNo=1";
        String opt2 = "&numOfRows=1000";
        String opt3 = "&dataType=JSON";
        String opt4 = "&base_date=";
        String opt5 = "&base_time=0500";
        String opt6 = "&nx=98";
        String opt7 = "&ny=76";
        String serviceUrl = url + optKey + serviceMyKey + opt1 + opt2 + opt3 + opt4 + formattedDate + opt5 + opt6 + opt7;

        List<WeatherItemDTO> weatherList = weatherService.WeatherList(serviceUrl);

        return weatherList;
    }

    @RequestMapping("/festival/f_list")
    public String f_list() throws Exception {
        return "festival/f_list";
    }

    @ResponseBody
    @PostMapping("/festival/f_list")
    public Object FestaList(@RequestParam("pageNo") int pageNo, @RequestParam("numOfRows") int numOfRows, @RequestParam("language") String language) throws Exception {
        String url = "";
        String optKey = "";
        String opt1 = "";
        String opt2 = "";
        String opt3 = "";
        String serviceUrl = "";

        if (language.equals("En")) {
            url = "https://apis.data.go.kr/6260000/FestivalService/getFestivalEn";
            optKey = "?serviceKey=OEGWo9HelquRMZH4QOIhetf1SpZIFQOaUXgtVJ7%2BgGnoial08m9k%2FEtkY02QzfdZlZDiaeXvmeO46iynznvvAA%3D%3D";
            opt1 = "&pageNo=";
            opt2 = "&numOfRows=";
            opt3 = "&resultType=JSON";
            serviceUrl = url + optKey + opt1 + pageNo + opt2 + numOfRows + opt3;
        } else if (language.equals("Ja")) {
            url = "https://apis.data.go.kr/6260000/FestivalService/getFestivalJa";
            optKey = "?serviceKey=OEGWo9HelquRMZH4QOIhetf1SpZIFQOaUXgtVJ7%2BgGnoial08m9k%2FEtkY02QzfdZlZDiaeXvmeO46iynznvvAA%3D%3D";
            opt1 = "&pageNo=";
            opt2 = "&numOfRows=";
            opt3 = "&resultType=JSON";
            serviceUrl = url + optKey + opt1 + pageNo + opt2 + numOfRows + opt3;
        } else {
            url = "https://apis.data.go.kr/6260000/FestivalService/getFestivalKr";
            optKey = "?serviceKey=";
            opt1 = "&pageNo=";
            opt2 = "&numOfRows=";
            opt3 = "&resultType=JSON";
            serviceUrl = url + optKey + serviceMyKey + opt1 + pageNo + opt2 + numOfRows + opt3;
        }


        List<FestaItemDTO> festaList = boardService.FestaList(serviceUrl, language);

        return festaList;
    }


    @GetMapping("/festival/f_detail")
    public ModelAndView f_detail(@RequestParam int uc_seq, @RequestParam String language) throws Exception {
        ModelAndView mv = new ModelAndView("festival/f_detail");
        mv.addObject("ucSeq", uc_seq);
        mv.addObject("language", language);

        return mv;
    }

    @ResponseBody
    @PostMapping("/festival/f_detail")
    public Object FestaDetail(@RequestParam("ucSeq") int ucSeq, @RequestParam("language") String language) throws Exception {
        String url = "";
        String optKey = "";
        String opt3 = "";
        String opt4 = "";
        String serviceUrl = "";

        if (language.equals("En")) {
            url = "https://apis.data.go.kr/6260000/FestivalService/getFestivalEn";
            optKey = "?serviceKey=OEGWo9HelquRMZH4QOIhetf1SpZIFQOaUXgtVJ7%2BgGnoial08m9k%2FEtkY02QzfdZlZDiaeXvmeO46iynznvvAA%3D%3D";
            opt3 = "&UC_SEQ=";
            opt4 = "&resultType=JSON";
            serviceUrl = url + optKey + opt3 + ucSeq + opt4;
        } else if (language.equals("Ja")) {
            url = "https://apis.data.go.kr/6260000/FestivalService/getFestivalJa";
            optKey = "?serviceKey=OEGWo9HelquRMZH4QOIhetf1SpZIFQOaUXgtVJ7%2BgGnoial08m9k%2FEtkY02QzfdZlZDiaeXvmeO46iynznvvAA%3D%3D";
            opt3 = "&UC_SEQ=";
            opt4 = "&resultType=JSON";
            serviceUrl = url + optKey + opt3 + ucSeq + opt4;
        } else {
            url = "https://apis.data.go.kr/6260000/FestivalService/getFestivalKr";
            optKey = "?serviceKey=";
            opt3 = "&UC_SEQ=";
            opt4 = "&resultType=JSON";
            serviceUrl = url + optKey + serviceMyKey + opt3 + ucSeq + opt4;
        }

        List<FestaItemDTO> festaList = boardService.FestaList(serviceUrl, language);

        return festaList;
    }


    @GetMapping("/sights/s_list")
    public String s_list() throws Exception {
        return "sights/s_list";
    }

    @ResponseBody
    @PostMapping("/sights/s_list")
    public Object SightsList(@RequestParam("pageNo") int pageNo, @RequestParam("numOfRows") int numOfRows, @RequestParam("language") String language) throws Exception {
        String url = "";
        String optKey = "";
        String opt1 = "";
        String opt2 = "";
        String opt3 = "";
        String serviceUrl = "";

        if (language.equals("En")) {
            url = "https://apis.data.go.kr/6260000/AttractionService/getAttractionEn";
            optKey = "?serviceKey=OEGWo9HelquRMZH4QOIhetf1SpZIFQOaUXgtVJ7%2BgGnoial08m9k%2FEtkY02QzfdZlZDiaeXvmeO46iynznvvAA%3D%3D";
            opt1 = "&pageNo=";
            opt2 = "&numOfRows=";
            opt3 = "&resultType=JSON";
            serviceUrl = url + optKey + opt1 + pageNo + opt2 + numOfRows + opt3;
        } else if (language.equals("Ja")) {
            url = "https://apis.data.go.kr/6260000/AttractionService/getAttractionJa";
            optKey = "?serviceKey=OEGWo9HelquRMZH4QOIhetf1SpZIFQOaUXgtVJ7%2BgGnoial08m9k%2FEtkY02QzfdZlZDiaeXvmeO46iynznvvAA%3D%3D";
            opt1 = "&pageNo=";
            opt2 = "&numOfRows=";
            opt3 = "&resultType=JSON";
            serviceUrl = url + optKey + opt1 + pageNo + opt2 + numOfRows + opt3;
        } else {
            url = "https://apis.data.go.kr/6260000/AttractionService/getAttractionKr";
            optKey = "?serviceKey=";
            opt1 = "&pageNo=";
            opt2 = "&numOfRows=";
            opt3 = "&resultType=JSON";
            serviceUrl = url + optKey + serviceMyKey + opt1 + pageNo + opt2 + numOfRows + opt3;
        }


        List<SightsItemDTO> sightsList = boardService.SightsList(serviceUrl, language);

        return sightsList;
    }

    @GetMapping("/sights/s_detail")
    public ModelAndView s_detail(@RequestParam int uc_seq, @RequestParam("language") String language) throws Exception {
        ModelAndView mv = new ModelAndView("sights/s_detail");
        mv.addObject("ucSeq", uc_seq);
        mv.addObject("language", language);

        return mv;
    }

    @ResponseBody
    @PostMapping("/sights/s_detail")
    public Object SigthsDetail(@RequestParam("ucSeq") int ucSeq, @RequestParam("language") String language) throws Exception {
        String url = "";
        String optKey = "";
        String opt3 = "";
        String opt4 = "";
        String serviceUrl = "";


        if (language.equals("En")) {
            url = "https://apis.data.go.kr/6260000/AttractionService/getAttractionEn";
            optKey = "?serviceKey=OEGWo9HelquRMZH4QOIhetf1SpZIFQOaUXgtVJ7%2BgGnoial08m9k%2FEtkY02QzfdZlZDiaeXvmeO46iynznvvAA%3D%3D";
            opt3 = "&UC_SEQ=";
            opt4 = "&resultType=JSON";
            serviceUrl = url + optKey + opt3 + ucSeq + opt4;
        } else if (language.equals("Ja")) {
            url = "https://apis.data.go.kr/6260000/AttractionService/getAttractionJa";
            optKey = "?serviceKey=OEGWo9HelquRMZH4QOIhetf1SpZIFQOaUXgtVJ7%2BgGnoial08m9k%2FEtkY02QzfdZlZDiaeXvmeO46iynznvvAA%3D%3D";
            opt3 = "&UC_SEQ=";
            opt4 = "&resultType=JSON";
            serviceUrl = url + optKey + opt3 + ucSeq + opt4;
        } else {
            url = "https://apis.data.go.kr/6260000/AttractionService/getAttractionKr";
            optKey = "?serviceKey=";
            opt3 = "&UC_SEQ=";
            opt4 = "&resultType=JSON";
            serviceUrl = url + optKey + serviceMyKey + opt3 + ucSeq + opt4;
        }

        List<SightsItemDTO> sightsList = boardService.SightsList(serviceUrl, language);

        return sightsList;
    }


}
