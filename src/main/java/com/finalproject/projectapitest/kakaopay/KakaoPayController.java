package com.finalproject.projectapitest.kakaopay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class KakaoPayController {

    @GetMapping("/kakaoPay")
    public String kakaoPayRead(){
        return "kakaoPay";
    }

    @PostMapping("/kakaoPay")
    public RedirectView kakaoPayPost(){
        //webClient 기본설정
        WebClient webclient = WebClient.builder().baseUrl("https://open-api.kakaopay.com").build();

        // api body
        Map<String, String> params = new HashMap<>();
        params.put("cid","TC0ONETIME");
        params.put("partner_order_id","partner_order_id");
        params.put("partner_user_id","partner_user_id");
        params.put("item_name","초코파이");
        params.put("quantity","1");
        params.put("total_amount", "2200");
        params.put("vat_amount","200");
        params.put("tax_amount","0");
        params.put("approval_url","https://hansool.shop/login");
        params.put("fail_url","https://hansool.shop");
        params.put("cancel_url","https://hansool.shop");

        Map<String,Object> kakaoPayResponse = webclient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/online/v1/payment/ready").build())
                .header("Authorization", "SECRET_KEY DEV4DD0EA10A998E203FBC63460C5921E358FDEF")
                .header("Content-Type","application/json")
                .bodyValue(params)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        log.info("kakaoPayResponse: {}", kakaoPayResponse.get("next_redirect_pc_url").toString());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(kakaoPayResponse.get("next_redirect_pc_url").toString());
        return redirectView;
    }
}
