package com.tanphi.laptopshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.request.register.RegistrationRequest;
import com.tanphi.laptopshop.response.authen.AuthenticationResponse;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {
    private final AuthenticationService authenticationService;

    public AuthenticationResponse login(String username) {
        Map<String, String> resultMap = authenticationService.login(username);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(Integer.parseInt(resultMap.get("id")));
        response.setUsername(resultMap.get("username"));
        response.setToken(resultMap.get("token"));
        response.setUserRole(resultMap.get("userRole"));
        response.setImageLink(resultMap.get("imageLink"));
        return response;
    }


    public void registerUser(RegistrationRequest request) {
        authenticationService.registerUser(request);
    }

    public boolean activateUser(String code) {
        return authenticationService.activateUser(code);
    }

    public boolean sendPasswordResetCode(String email) {
        return authenticationService.sendPasswordResetCode(email);
    }

    public Accounts findByPasswordResetCode(String code) {
        return authenticationService.findByPasswordResetCode(code);
    }

    public String passwordReset(String email, String password) {
        return authenticationService.passwordReset(email, password);
    }
    public String updatePassword(Integer id, String password) {
        return authenticationService.updatePassword(id, password);
    }
}
