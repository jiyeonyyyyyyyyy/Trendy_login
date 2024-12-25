package com.trendy.login;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final UserRepository userRepository = null;

    public void saveUser(String accessToken) {
        String userInfoUri = "https://kapi.kakao.com/v2/user/me";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(userInfoUri, String.class, accessToken);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            String username = jsonNode.get("properties").get("nickname").asText();
            //String email = jsonNode.get("kakao_account").get("email").asText();
            String profileImageUrl = jsonNode.get("properties").get("profile_image").asText();

            User user = new User();
            user.setUsername(username);
            //user.setEmail(email);
            user.setProfileImageUrl(profileImageUrl);

            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Kakao user info", e);
        }
    }
    
    
    
    
    
}
