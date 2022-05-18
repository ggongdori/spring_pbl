package com.example.kakao_oauth.service;

import com.example.kakao_oauth.domain.user.User;
import com.example.kakao_oauth.oauth.CustomUserDetails;
import com.example.kakao_oauth.oauth.OAuth2UserInfo;
import com.example.kakao_oauth.oauth.OAuth2UserInfoFactory;
import com.example.kakao_oauth.oauth.ProviderType;
import com.example.kakao_oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
//    private final AssetCategoryRepository assetCategoryRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
        Optional<User> savedUser = userRepository.findByUserId(userInfo.getId());

        if (savedUser.isPresent()) {
            updateUser(savedUser.get(), userInfo);
        } else {
            savedUser = Optional.of(createUser(userInfo, providerType));
        }

        return CustomUserDetails.create(savedUser.get(), user.getAttributes());
    }

    private User createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
        User user = userRepository.saveAndFlush(
                User.builder()
                        .userId(userInfo.getId())
                        .nickname(userInfo.getName())
                        .email(userInfo.getEmail())
//                        .birthday(userInfo.getBirthday())
//                        .birthYear(userInfo.getBirthYear())
//                        .gender(userInfo.getGender())
                        .providerType(providerType)
                        .build()
        );

        return user;
    }


    private User updateUser(User user, OAuth2UserInfo userInfo) {
        if (userInfo.getName() != null && !user.getNickname().equals(userInfo.getName())) {
            user.setNickname(userInfo.getName());
        }

        return user;
    }

}