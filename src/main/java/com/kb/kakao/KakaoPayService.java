package com.kb.kakao;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KakaoPayService {
	private static final String HOST = "https://open-api.kakaopay.com";
	// 카카오 페이 개발키
	private static final String kakaoKey = "DEV066CF8A49040823977FB0B1BA1A266DDE2BF5";

	public KakaoPayReadyVO kakaoPayReady(KakaoPayRequestParam param) {

		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "SECRET_KEY " + kakaoKey);
		headers.add("Content-type", "application/json;");

		// 서버로 요청할 Body
		Map<String, String> params = new LinkedHashMap<>();
		params.put("cid", "TC0ONETIME");
		params.put("partner_order_id", "1001");
		params.put("partner_user_id", "test12");
		params.put("item_name", param.getItem_name());
		params.put("quantity", param.getQuantity());
		params.put("total_amount", param.getTotal_amount());
		params.put("tax_free_amount", "500");
		params.put("approval_url", "http://localhost:5173/kakaopay/kakaoPaySuccess");
		params.put("cancel_url", "http://localhost:5173/kakaopay/kakaoPayCancel");
		params.put("fail_url", "http://localhost:5173/kakaopay/kakaoPayFail");
		params.put("msg", "결제가 완료되었습니다.");


		HttpEntity<Map<String, String>> body = new HttpEntity<>(params, headers);
//		System.out.println(body);
		try {
			KakaoPayReadyVO kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/online/v1/payment/ready"), body,
					KakaoPayReadyVO.class);
			log.info("" + kakaoPayReadyVO);
			return kakaoPayReadyVO;
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}

	public KakaoPayApprovalVO kakaoPayInfo(String pg_token, KakaoPayReadyVO readyVO,  KakaoPayRequestParam param) {
		log.info("KakaoPayInfoVO............................................");
		log.info("-----------------------------");

		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "SECRET_KEY " + kakaoKey);
		headers.add("Content-type", "application/json;");

		// 서버로 요청할 Body
		Map<String, String> params = new LinkedHashMap<>();
		params.put("cid", "TC0ONETIME");
		params.put("tid", readyVO.getTid());
		params.put("partner_order_id", "1001");
		params.put("partner_user_id", "test12");
		params.put("pg_token", pg_token);
		params.put("total_amount", param.getTotal_amount());

		HttpEntity<Map<String, String>> body = new HttpEntity<>(params, headers);

		try {
			KakaoPayApprovalVO kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/online/v1/payment/approve"), body, KakaoPayApprovalVO.class);
			log.info("" + kakaoPayApprovalVO);
			return kakaoPayApprovalVO;
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
