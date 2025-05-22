package com.bajaj.ADID.Controller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bajaj.ADID.DTO.LoginResponse;
import com.bajaj.ADID.Service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api")
@Slf4j
public class AuthController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) String error,
            HttpServletRequest request) {
        if ("sessionExpired".equals(error)) {
            request.getSession().setAttribute("errorMessage", "Session expired. Please login again.");
        } else if ("true".equals(error)) {
            request.getSession().setAttribute("errorMessage", "Invalid username or password.");
        }
        return "login"; // mapped to /WEB-INF/views/login.jsp
    }

    /*
     * @PostMapping("/login")
     * public String processLogin(@RequestParam String username,
     * 
     * @RequestParam String password,
     * HttpServletRequest request) {
     * Map<String, String> result = loginService.loginADID(username, password);
     * 
     * if ("true".equalsIgnoreCase(result.get("IS_LOGIN"))) {
     * return "redirect:https://your-custom-redirect.com/dashboard"; // success
     * } else {
     * return "redirect:/login?error=true"; // login failed
     * }
     * }
     */

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request) {
        try {
            Map<String, String> result = loginService.loginADID(username, password);
            log.info("Login result: " + result);
            log.info("Username: " + username + ", Password: " + password);
            if ("true".equalsIgnoreCase(result.get("IS_LOGIN"))) {
                URI redirectUri = URI.create("https://www.salesforce.com/in/"); // or dynamic
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(redirectUri);
                return new ResponseEntity<>(headers, HttpStatus.FOUND); // 302 redirect
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new LoginResponse(false, "Authentication failed"));
            }

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponse(false, "Login failed: " + ex.getMessage()));
        }
    }
}