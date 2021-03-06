package com.example.kakao_oauth.service;

import com.example.kakao_oauth.dto.request.UserUpdateRequest;
import com.example.kakao_oauth.exception.CustomException;
import com.example.kakao_oauth.exception.ErrorCode;
import com.example.kakao_oauth.repository.UserRepository;
import com.example.kakao_oauth.domain.user.User;
import com.example.kakao_oauth.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final PostRepository postRepository;
//    private final CommentRepository commentRepository;
//    private final ScrapRepository scrapRepository;
    private final UserUtil userUtil;

    /**
     * 전달받은 accessToken을 통해 현재 유저를 반환합니다.
     *
     * @return User 현재 유저
     */
    public User getUser() {
        return userUtil.findCurrentUser();
    }

    /**
     * 전달받은 유저와 유저의 개인정보를 통해 업데이트합니다.
     *
     * @param userUpdateRequest 유저 업데이트
     * @return UserResponse
     */
    @Transactional
    public User update(UserUpdateRequest userUpdateRequest) {
        User user = userUtil.findCurrentUser();
        // 닉네임 중복 확인
        if (userRepository.existsByNickname(userUpdateRequest.getNickname())) {
            throw new CustomException(ErrorCode.ALREADY_NICKNAME_EXISTS);
        }

        // 휴대폰 번호 중복 확인
//        if (userRepository.existsByPhoneNum(userUpdateRequest.getPhoneNum())) {
//            throw new CustomException(ErrorCode.ALREADY_PHONE_NUM_EXISTS);
//        }

        // 이메일 중복 확인
        if (userRepository.existsByEmail(userUpdateRequest.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_EMAIL_EXISTS);
        }
        user.update(userUpdateRequest);
        return user;
    }

    /**
     * 전달받은 사용자의 식별자를 통해 유저를 DB에서 조회합니다.
     *
     * @param id 식별자
     * @return User 유저
     */
    public User getUser(Long id) {
        return findUser(id);
    }

    /**
     * 전달받은 사용자의 식별자를 통해 유저를 DB에서 조회하고, 없으면 예외를 던집니다.
     *
     * @param id 식별자
     * @return User 유저
     */
    private User findUser(Long id) {
        return userRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }

//    /**
//     * 전달받은 이메일 인증 토큰을 통해 이메일을 변경합니다.
//     *
//     * @param authToken 이메일 인증 토큰
//     */
//    @Transactional
//    public void confirmEmail(UserMailAuth authToken) {
//        User user = findUser(authToken.getUserId());
//        authToken.useToken();
//        user.updateEmail(authToken.getMail());
//    }

    /**
     * 전달받은 사용자 email과 같은 email이 DB에 존재하는지 확인합니다.
     *
     * @param userEmail 전달받은 사용자 email
     * @return DB에 존재 여부. 존재하면 예외, 존재하지 않으면 false를 반환합니다.
     */
    public Boolean isDuplicateEmail(String userEmail) {
        if (userRepository.existsByEmailAndEmailCheckIsTrue(userEmail)) {
            throw new CustomException(ErrorCode.ALREADY_EMAIL_EXISTS);
        } else {
            return false;
        }
    }

    /**
     * 마이페이지의 내가 쓴 글을 조회합니다.
     */
//    public Page<MyPostResponse> findMyPosts(Pageable pageable) {
//        User user = userUtil.findCurrentUser();
//        Page<Post> post = postRepository.findByUserOrderByIdDesc(user, pageable);
//
//        List<MyPostResponse> postResponses =
//                post.stream()
//                        .map(MyPostResponse::toMyPostResponse)
//                        .collect(Collectors.toList());
//
//        return new PageImpl<>(postResponses, pageable, post.getTotalElements());
//    }
//
//    /**
//     * 마이페이지의 내가 쓴 댓글을 조회합니다.
//     */
//    public Page<MyCommentResponse> findMyComments(Pageable pageable) {
//        User user = userUtil.findCurrentUser();
//        Page<Comment> comments = commentRepository.findByUserOrderByIdDesc(user, pageable);
//
//        List<MyCommentResponse> commentResponses =
//                comments.stream()
//                        .map(MyCommentResponse::toMyCommentResponse)
//                        .collect(Collectors.toList());
//
//        return new PageImpl<>(commentResponses, pageable, comments.getTotalElements());
//    }
//
//    /**
//     * 마이페이지의 내가 스크랩한 글을 조회합니다.
//     */
//    public Page<MyScrapResponse> findMyScraps(Pageable pageable) {
//        User user = userUtil.findCurrentUser();
//        Page<Scrap> scraps = scrapRepository.findByUserOrderByIdDesc(user, pageable);
//
//        List<MyScrapResponse> scrapResponses =
//                scraps.stream()
//                        .map(MyScrapResponse::toMyScrapResponse)
//                        .collect(Collectors.toList());
//
//        return new PageImpl<>(scrapResponses, pageable, scraps.getTotalElements());
//    }


}