package com.example.kakao_oauth.oauth;

import com.example.kakao_oauth.domain.user.Gender;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

//    @Override
//    public String getBirthday() {
//        return (String) attributes.get("birthday");
//    }
//
//    @Override
//    public Gender getGender() {
//        String gender = (String) attributes.get("gender");
//
//        if (gender != null) {
//            if (gender.equals("male")) {
//                return Gender.MAN;
//            }
//            return Gender.WOMAN;
//        }
//        return null;
//    }
//
//    @Override
//    public String getBirthYear() {
//        return (String) attributes.get("birthyear");
//    }

}