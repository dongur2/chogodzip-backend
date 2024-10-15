package com.kb.kakao;


import lombok.Data;

@Data
public class KakaoPayRequestParam {
    String item_name; // 결제 이름
    String total_amount; // 가격
    String quantity; //  수량
}
