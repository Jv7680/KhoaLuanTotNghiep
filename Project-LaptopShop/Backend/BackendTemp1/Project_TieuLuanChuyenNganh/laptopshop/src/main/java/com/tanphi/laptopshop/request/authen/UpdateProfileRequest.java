package com.tanphi.laptopshop.request.authen;

import java.lang.reflect.Field;

import lombok.Data;

@Data
public class UpdateProfileRequest {
	private String firstName;
	private String lastName;
	private String address;
	public String checkNull() throws IllegalAccessException {
		for (Field f : getClass().getDeclaredFields())
			if (f.get(this) == null || f.get(this).equals(""))
				return f.getName() + " trống";
		return "Success";
	}
}
