package com.example.kakao_oauth.dto.request;

import com.example.kakao_oauth.domain.user.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="개인정보 수정 요청 정보")
public class UserUpdateRequest {

    @ApiModelProperty(value = "닉네임", required = true, example = "모아모아")
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @ApiModelProperty(value = "전화번호", required = true, example = "01055555555")
    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNum;

    @ApiModelProperty(value = "이메일", required = true, example = "kmw106933@naver.com")
    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$")
    private String email;

    @ApiModelProperty(value = "성별", required = true, example = "MAN")
    private Gender gender;

}