package com.finalproject.projectapitest.kakaopay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoPayResponse {

    private String tid;
    private String tmsResult;
    private String createAt;
    private String nextRedirectPcUrl;

}
