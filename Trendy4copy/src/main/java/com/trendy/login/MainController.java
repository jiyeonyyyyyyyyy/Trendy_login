package com.trendy.login;


import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main"; // main.html 반환
    }
    
    @GetMapping("/main")
    public String main() {
    	return "main";
    }
    
    @GetMapping("login")
    public String login() {
    	return "main";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "loginFailure"; // loginFailure.html 반환
    }


@GetMapping("loginSuccess")
	public String loginSuccess() {
		return "loginSuccess";
	}

}