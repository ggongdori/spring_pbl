package com.example.kakao_oauth.controller;

import com.example.kakao_oauth.domain.user.User;
import com.example.kakao_oauth.dto.request.UserUpdateRequest;
import com.example.kakao_oauth.dto.response.UserResponse;
import com.example.kakao_oauth.repository.UserRepository;
import com.example.kakao_oauth.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
//    private final MailSendService mailSendService;

    /**
     * [GET] api/users
     * 사용자의 accessToken을 받아 사용자의 정보를 반환합니다.
     *
     * @return ApiResponse 응답 내용
     */
    @GetMapping
    @ApiOperation(value = "현재 사용자 조회",
            notes = "현재 사용자의 정보를 가져옵니다. 헤더(Bearer)에 사용자 토큰 주입을 필요로 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용자를 정상적으로 조회한 경우"),
            @ApiResponse(responseCode = "404", description = "해당 사용자를 찾지 못한 경우")
    })
    public ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok(UserResponse.toUserResponse(userService.getUser()));
    }

    /**
     * [PATCH] api/users
     * 현재 사용자의 개인 정보를 변경합니다.
     *
     * @return 사용자 개인정보 수정
     */
    @PatchMapping
    @ApiOperation(value = "개인정보 수정",
            notes = "현재 사용자의 개인정보를 업데이트 합니다. 헤더(Bearer)에 사용자 토큰 주입을 필요로 합니다.")
    @ApiResponse(responseCode = "200", description = "사용자의 개인정보를 정상적으로 수정한 경우")
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(UserResponse.toUserResponse(userService.update(userUpdateRequest)));
    }

    @RestController
    public class UserController {

        @Autowired
        private UserRepository userRepository;

        @GetMapping("/user/me")
        @PreAuthorize("hasRole('USER')")
        public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
            return userRepository.findById(userPrincipal.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        }
    }
    /**
     * [GET] api/users/registerEmail
     * 변경할 이메일을 받아 이메일 인증 메일을 보냅니다.
     */
//    @PostMapping("/registerEmail")
//    @ApiOperation(value = "이메일 인증하기",
//            notes = "변경할 이메일을 받아 인증 메일을 보내고 이메일을 인증합니다. 헤더(Bearer)에 사용자 토큰 주입을 필요로 합니다.")
//    @ApiResponse(responseCode = "200", description = "이메일 인증 메일이 정상적으로 보내진 경우")
//    public void registerEmail(@RequestBody UserEmailRequest userEmailRequest) {
//        userService.isDuplicateEmail(userEmailRequest.getEmail());
//        String authKey = mailSendService.sendAuthMail(userEmailRequest.getEmail());
//        mailSendService.save(userEmailRequest.getEmail(), authKey);
//    }

//    @GetMapping("/confirm")
//    @ApiOperation(value = "이메일 인증",
//            notes = "사용자가 전송받은 인증 이메일의 인증하기 버튼을 누르면 인증 로직을 시행합니다.")
//    @ApiIgnore
//    public void confirmEmail(@RequestParam Map<String, String> map) {
//        UserMailAuth authToken = mailSendService.getAuthToken(map);
//        userService.confirmEmail(authToken);
//    }

    /**
     * [GET] api/users/emailCheck/?email= 이메일
     * 인증할 이메일을 받아, 이메일의 중복 여부를 확인 합니다.
     */
    @ApiOperation(value = "이메일 인증 중복 확인",
            notes = "DB에 입력된 이메일의 중복 여부를 리턴합니다. 이미 인증된 이메일이라면 예외, 존재하지 않으면 false를 반환합니다.")
    @GetMapping("/emailCheck")
    @ApiResponse(responseCode = "200", description = "이메일이 중복되지 않은 경우")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.isDuplicateEmail(email));
    }

    /**
     * [GET] api/users/mypage/posts&page= &size= &sort=
     *
     * page : 가져올 페이지 (기본값 : 0)
     * size : 페이지의 크기 (기본값 : 20)
     * sort : 정렬 기준으로 사용할 속성으로 기본적으로 오름차순
     *
     * 마이 페이지의 내가 쓴 글의 리스트를 최신순으로 반환합니다.
     */
//    @ApiOperation(value = "마이 페이지 내가 쓴 글 보기",
//            notes = "마이 페이지의 내가 쓴 글의 리스트를 반환합니다. 헤더(Bearer)에 사용자 토큰 주입을 필요로 합니다.")
//    @GetMapping("/mypage/posts")
//    @ApiResponse(responseCode = "200", description = "내가 쓴 글이 정상적으로 조회된 경우")
//    public ResponseEntity<Page<MyPostResponse>> findMyPosts(Pageable pageable) {
//        return ResponseEntity.ok(userService.findMyPosts(pageable));
//    }

    /**
     * [GET] api/users/mypage/comments
     *
     * page : 가져올 페이지 (기본값 : 0)
     * size : 페이지의 크기 (기본값 : 20)
     * sort : 정렬 기준으로 사용할 속성으로 기본적으로 오름차순
     *
     * 마이 페이지의 내가 쓴 댓글의 리스트를 반환합니다.
     */
//    @ApiOperation(value = "마이 페이지 내가 쓴 댓글 보기",
//            notes = "마이 페이지의 내가 쓴 글의 댓글을 반환합니다. 헤더(Bearer)에 사용자 토큰 주입을 필요로 합니다.")
//    @GetMapping("/mypage/comments")
//    @ApiResponse(responseCode = "200", description = "내가 쓴 댓글이 정상적으로 조회된 경우")
//    public ResponseEntity<Page<MyCommentResponse>> findMyComments(Pageable pageable) {
//        return ResponseEntity.ok(userService.findMyComments(pageable));
//    }

    /**
     * [GET] api/users/mypage/comments
     *
     * page : 가져올 페이지 (기본값 : 0)
     * size : 페이지의 크기 (기본값 : 20)
     * sort : 정렬 기준으로 사용할 속성으로 기본적으로 오름차순
     *
     * 마이 페이지의 내가 쓴 댓글의 리스트를 반환합니다.
     */
//    @ApiOperation(value = "마이 페이지 내가 스크랩한 글 보기",
//            notes = "마이 페이지의 내가 스크랩한 글을 반환합니다. 헤더(Bearer)에 사용자 토큰 주입을 필요로 합니다.")
//    @GetMapping("/mypage/scraps")
//    @ApiResponse(responseCode = "200", description = "내가 스크랩한 글이 정상적으로 조회된 경우")
//    public ResponseEntity<Page<MyScrapResponse>> findMyScraps(Pageable pageable) {
//        return ResponseEntity.ok(userService.findMyScraps(pageable));
//    }

}