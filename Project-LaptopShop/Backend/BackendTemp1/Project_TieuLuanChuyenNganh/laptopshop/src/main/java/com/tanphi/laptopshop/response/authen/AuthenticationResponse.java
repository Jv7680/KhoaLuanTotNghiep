package com.tanphi.laptopshop.response.authen;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private Integer Id;
    private String username;
    private String token;
    private String userRole;
    private String imageLink;
}
