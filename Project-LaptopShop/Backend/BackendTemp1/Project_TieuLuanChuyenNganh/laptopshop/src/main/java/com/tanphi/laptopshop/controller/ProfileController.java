package com.tanphi.laptopshop.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.request.authen.PasswordResetRequest;
import com.tanphi.laptopshop.request.authen.UpdateProfileRequest;
import com.tanphi.laptopshop.security.UserPrincipal;
import com.tanphi.laptopshop.service.AccountsService;
import com.tanphi.laptopshop.service.AuthenticationMapper;

@PreAuthorize("hasAuthority('CUSTOMER')")
@RestController
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	private AccountsService accountsService;
	@Autowired AuthenticationMapper authenticationMapper;

	@GetMapping("/{customerId}")
    public ResponseEntity<?> getProfileById(@PathVariable int customerId) throws ParseException
    {
		Accounts myAccount=accountsService.findAccountById(customerId);
		if(myAccount==null)
		{
			throw new BadRequestException("Không tồn tại tài khoản có id: "+customerId);
		}
		return ResponseEntity.ok(myAccount);
    }

	@PutMapping("/update//{customerId}")
	public ResponseEntity<?> updateProfile(@PathVariable int customerId,@RequestBody UpdateProfileRequest request) throws Exception {
		String resultValidate=request.checkNull();
		if(!resultValidate.equals("Success"))
		{
			throw new BadRequestException("Dữ liệu không đủ - "+resultValidate);
		}
		accountsService.updateProfile(customerId,request);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Cập nhật thông tin thành công");
		return ResponseEntity.ok(apiResponse);
	}
	@PutMapping("/edit/password")
    public ResponseEntity<?> updateUserPassword(@AuthenticationPrincipal UserPrincipal user,@Valid @RequestBody PasswordResetRequest passwordReset,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);
        }
        if(passwordReset.getAccountId()==null)
        {
        	throw new BadRequestException("Chưa truyền accountId");
        }
        if(passwordReset.getPassword() != null &&!(passwordReset.getPassword().equals(passwordReset.getRepassword())))
        {
            throw new BadRequestException("Mật khẩu không ăn khớp");
        }
        String message=authenticationMapper.updatePassword(passwordReset.getAccountId(), passwordReset.getPassword());
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage(message);
        return ResponseEntity.ok(apiResponse);
    }
}
