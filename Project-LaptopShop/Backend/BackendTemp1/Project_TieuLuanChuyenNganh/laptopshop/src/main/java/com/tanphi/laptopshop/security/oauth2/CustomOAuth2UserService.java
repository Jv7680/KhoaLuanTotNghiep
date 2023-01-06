package com.tanphi.laptopshop.security.oauth2;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.enums.ActiveAccountStatus;
import com.tanphi.laptopshop.entity.enums.IsDeleteStatus;
import com.tanphi.laptopshop.repository.AccountsRepo;
import com.tanphi.laptopshop.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private AccountsRepo accountsRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		try {
			String provider = userRequest.getClientRegistration().getRegistrationId();
			OAuth2UserInfo oAuth2UserInfo = null;
			oAuth2UserInfo = OAuth2UserFactory.getOAuth2UserInfo(provider, oAuth2User.getAttributes());
			Accounts user = accountsRepository.findAccountsByGmail(oAuth2UserInfo.getEmail());

			if (user == null) {
				user = authenticationService.registerOauth2User(provider, oAuth2UserInfo);
			} else {
				user = authenticationService.updateOauth2User(user, provider, oAuth2UserInfo);
			}
		} catch (OAuth2AuthenticationException ex) {
			ex.printStackTrace();
		}
		catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return oAuth2User;
	}
}
