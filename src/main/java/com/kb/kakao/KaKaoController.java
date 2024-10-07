package com.kb.kakao;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Slf4j
@Controller
public class KaKaoController {

	@Setter(onMethod_ = {@Autowired})
    private KakaoPayService kakaopayService;
	    
    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestParam Map<String, String> param, HttpSession session) {
        log.info("kakaoPay post............................................");
        session.setAttribute("param", param);
        return "redirect:" + kakaopayService.kakaoPayReady(param);
 
    }
    
    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpSession session) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
		Map<String, String> param = (Map<String, String>) session.getAttribute("param");
        model.addAttribute("info", kakaopayService.kakaoPayInfo(pg_token, param));
        return "kakao/kakaoPaySuccess";
    }
	
}
