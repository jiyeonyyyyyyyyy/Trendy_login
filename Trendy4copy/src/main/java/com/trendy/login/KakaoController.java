package com.trendy.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService kakaoService = new KakaoService();

    @GetMapping("/kakao")
    public void kakaoLoginRedirect(HttpServletResponse response) {
        String kakaoAuthUri = "https://kauth.kakao.com/oauth/authorize?response_type=code" +
                "&client_id=5f437335c73db193eccb36fa22153089" +
                "&redirect_uri=http://localhost:3000/login/oauth2/code/kakao";
        try {
            response.sendRedirect(kakaoAuthUri); // 외부 URL로 이동
        } catch (IOException e) {
            throw new RuntimeException("Failed to redirect to Kakao Auth URI", e);
        }
    }

    @GetMapping("/oauth2/code/kakao")
    public void kakaoCallback(@RequestParam String code, HttpServletResponse response) {
        try {
            String accessToken = getAccessTokenFromCode(code);
            kakaoService.saveUser(accessToken); // 사용자 저장
            response.sendRedirect("/main"); // 메인 페이지로 이동
        } catch (Exception e) {
            try {
                response.sendRedirect("/loginFailure");
            } catch (IOException ioException) {
                throw new RuntimeException("Failed to redirect to loginFailure", ioException);
            }
        }
    }
    
    
	private String getAccessTokenFromCode(String code) {
	    String tokenUri = "https://kauth.kakao.com/oauth/token";
	    RestTemplate restTemplate = new RestTemplate();
	
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
	
	    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
	    body.add("grant_type", "authorization_code");
	    body.add("client_id", "5f437335c73db193eccb36fa22153089");
	    body.add("redirect_uri", "http://localhost:3000/login/oauth2/code/kakao");
	    body.add("code", code);
	    body.add("client_secret", "sklo4XULd5zDamOkjThxM6JJpiqaMzID");
	
	    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
	
	    try {
	        ResponseEntity<String> responseEntity = restTemplate.exchange(tokenUri, HttpMethod.POST, requestEntity, String.class);
	
	        // JSON 응답에서 액세스 토큰 추출
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode responseNode = objectMapper.readTree(responseEntity.getBody());
	        return responseNode.get("access_token").asText(); // access_token 추출
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to retrieve access token: " + e.getMessage());
    	}
}
    
    
    
}
