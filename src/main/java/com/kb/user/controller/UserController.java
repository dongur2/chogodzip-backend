package com.kb.user.controller;

import com.kb.user.dto.response.UserHeaderDTO;
import com.kb.user.dto.request.UserJoinDTO;
import com.kb.user.dto.UserProfileDTO;
import com.kb.user.service.KakaoLoginService;
import com.kb.user.dto.User;
import com.kb.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Api(value = "UserController", tags = "사용자 컨트롤러")
@PropertySource({"classpath:/application.properties"})
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService service;
    private final KakaoLoginService kakaoService;

    //카카오 회원 정보 조회
    @GetMapping("/kakaoInfo/{code}")
    public ResponseEntity<Map<String,Object>> getKakaoInfo(@PathVariable String code) {
        String enrollUrl = "http://localhost:5173/auth/kakaojoin";
        String token = kakaoService.getToken(code, enrollUrl);

        Map<String, Object> userInfo = kakaoService.getUserInfo(token);
        return ResponseEntity.ok(userInfo);
    }

    //가입 여부 확인
    @GetMapping("/checkkakaoid/{username}")
    public ResponseEntity<Boolean> checkKakaoDuplicated(@PathVariable String username) {
        return ResponseEntity.ok().body(service.checkKaKaoDuplicate(username));
    }

    //회원가입 리다이렉트
    @RequestMapping("/auth/kakaojoin")
    public String kakaoRedirect(@RequestBody Map<String, Object> requestBody) {
        String kakaoId = (String) requestBody.get("id");
        service.getMemberByKakaoId(kakaoId);
        return "redirect:/auth/kakaologin";
    }

    //닉네임 중복 확인
    @GetMapping("/checknickname/{nickname}")
    public ResponseEntity<Boolean> checkDuplicate(@PathVariable String nickname) {
        return ResponseEntity.ok().body(service.checkDuplicate(nickname));
    }

    //회원 가입
    @PostMapping
    public ResponseEntity<User> join(UserJoinDTO joinDTO) throws IllegalAccessException {
        return ResponseEntity.ok(service.join(joinDTO));
    }

    //토큰으로 회원 정보 조회(헤더)
    @GetMapping
    public ResponseEntity<UserHeaderDTO> getUserOnHeader(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(UserHeaderDTO.from(user));
    }

    //토큰으로 프로필 조회(마이페이지)
    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.getRealRegionWithUser(user));
    }

    //프로필 수정(마이페이지)
    @PostMapping("/profile")
    public HttpStatus updateUser(@AuthenticationPrincipal User user,
                                 @RequestBody UserProfileDTO userInfo) throws IllegalAccessException {
        service.updateUserProfile(user.getUserId(), userInfo);
        return HttpStatus.OK;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<User> get(@PathVariable String id) {
//        return ResponseEntity.ok(service.getMember(id));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<User> delete(@PathVariable String id) {
//        return ResponseEntity.ok(service.delete(id));
//    }
}
