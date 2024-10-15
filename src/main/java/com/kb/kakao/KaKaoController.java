package com.kb.kakao;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/api/kakaopay")
public class KaKaoController {

    @Setter(onMethod_ = {@Autowired})
    private KakaoPayService kakaopayService;

    @PostMapping("/kakaoPayRequest")
    public ResponseEntity<Map<String, Object>> kakaoPay(KakaoPayRequestParam param, HttpSession session) {
        log.info("kakaoPay post............................................");
        log.info("param : " + param);
        session.setAttribute("param", param);
        KakaoPayReadyVO readyVO = kakaopayService.kakaoPayReady(param);

        Map<String, Object> map = new HashMap<>();
        if(readyVO == null){
            map.put("result", "fail");
            return ResponseEntity.ok(map);
        }

        map.put("result", "ok");
        map.put("readyInfo", readyVO);
        session.setAttribute("readyVO", readyVO);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/kakaoPaySuccess")
    public ResponseEntity<KakaoPayApprovalVO> kakaoPaySuccess(@RequestParam("pg_token") String pg_token, HttpSession session) {
        log.info("kakaoPaySuccess post............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        KakaoPayRequestParam param = (KakaoPayRequestParam) session.getAttribute("param");
        KakaoPayReadyVO readyVO = (KakaoPayReadyVO) session.getAttribute("readyVO");
        return ResponseEntity.ok(kakaopayService.kakaoPayInfo(pg_token, readyVO, param));
    }

}
