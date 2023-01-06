package com.tanphi.laptopshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.enums.AuthProvider;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.exception.NotFoundException;
import com.tanphi.laptopshop.request.authen.AuthenticationRequest;
import com.tanphi.laptopshop.request.authen.PasswordResetRequest;
import com.tanphi.laptopshop.service.AuthenticationMapper;
import com.tanphi.laptopshop.service.CustomAuthenticationProviderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired private CustomAuthenticationProviderService customerauthenticationproviderservice;
    @Autowired
    private AuthenticationMapper authenticationMapper;

    @GetMapping("/oauth/google")
    public ResponseEntity<?> loginGoogle(@RequestParam("id") String id,@RequestParam("provider") String provider) {
        if(AuthProvider.LOCAL.getCode().toString().equals(provider) )
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tài khoản đã tồn tại");
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Đăng nhập thành công");
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest request, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        try {
        	customerauthenticationproviderservice.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return ResponseEntity.ok(authenticationMapper.login(request.getUsername()));
        } catch (AuthenticationException e) {
            throw new BadRequestException("Nhập sai mật khẩu hoặc tên tài khoản");
        }
    }
    @PostMapping("/forgot")
    public ResponseEntity<Object> forgotPassword(@RequestBody PasswordResetRequest passwordReset) {
        boolean forgotPassword = authenticationMapper.sendPasswordResetCode(passwordReset.getEmail());
        if (!forgotPassword) {
            throw new NotFoundException("Không tìm được Email");
        }
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Mã đặt lại mật mật khẩu đã được gửi về Email của bạn");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/reset/{code}")
    public ResponseEntity<?> getPasswordResetCode(@PathVariable String code) {
        Accounts accounts = authenticationMapper.findByPasswordResetCode(code);
        if (accounts == null) {
            throw new NotFoundException("Mã đặt lại mật khẩu không hợp lệ");
        }
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Mã đặt lại ăn khớp");
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> passwordReset(@Valid @RequestBody PasswordResetRequest passwordReset,BindingResult bindingResult) {
        if(passwordReset.getPassword() != null &&!(passwordReset.getPassword().equals(passwordReset.getRepassword())))
        {
            throw new BadRequestException("Mật khẩu không ăn khớp");
        }
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        String message=authenticationMapper.passwordReset(passwordReset.getEmail(),passwordReset.getPassword());
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage(message);
        return ResponseEntity.ok(apiResponse);
    }
}
