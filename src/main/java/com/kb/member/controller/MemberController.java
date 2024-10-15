package com.kb.member.controller;

import com.kb.common.util.UploadFiles;
import com.kb.kakao.KaKaoLoginService;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.Member;
import com.kb.member.dto.MemberDTO;
import com.kb.member.dto.UserProfileUpdateRequest;
import com.kb.member.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Api(value = "MemberController", tags = "멤버 정보")
@PropertySource({"classpath:/application.properties"})
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {

    @Value("#{'${os_type}' == 'win' ? '${file_save_location_win}':'${file_save_location_other}'}")
    public String LOCATION;

    private final MemberService service;

    private final KaKaoLoginService kaKaoLoginService;

    @RequestMapping("/auth/kakaojoin")
    public String kakaoRedirect(@RequestBody Map<String, Object> requestBody) {
        // requestBody로 넘어온 데이터에서 id 값 추출
        String kakaoId = (String) requestBody.get("id");
        service.getMemberByKakaoId(kakaoId);
        log.info("아이디입니다요@@@@@@ : " + kakaoId);
        return "redirect:/auth/kakaologin";
    }

    @GetMapping("/checkid/{id}")
    public ResponseEntity<Boolean> checkDuplicate(@PathVariable String id) {
        return ResponseEntity.ok().body(service.checkDuplicate(id));
    }
    
    @GetMapping("/checkkakaoid/{id}")
    public ResponseEntity<Boolean> checkKakaoDuplicate(@PathVariable String id) {
        return ResponseEntity.ok().body(service.checkKaKaoDuplicate(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> get(@PathVariable String id) {
        return ResponseEntity.ok(service.getMember(id));
    }


    @GetMapping("/kakaoInfo/{code}")
    public ResponseEntity<Map<String,Object>> getKakaoInfo(@PathVariable String code) throws IOException {
        String enrollUrl = "http://localhost:5173/auth/kakaojoin";
        String token = kaKaoLoginService.getToken(code, enrollUrl);
        System.out.println("@@@" + token);
        Map<String, Object> map = kaKaoLoginService.getUserInfo(token);
        System.out.println(map);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/{id}/avatar")
    public void getAvatar(@PathVariable String id, HttpServletResponse response) {
        String avatarPath =  LOCATION + "/avatar/" + id + ".png";
        File file = new File(avatarPath);
        if (!file.exists()) {
            file = new File( LOCATION + "/avatar/unknown.png");
        }
        UploadFiles.downloadImage(response, file);
    }


    @PostMapping("")
    public ResponseEntity<Member> join(MemberDTO memberDTO,
                                       @RequestParam(name = "avatar", required = false)
                                       MultipartFile avatar) throws IllegalAccessException {
        Member member = memberDTO.toMember();
        System.out.println("@@@" + member);
        return ResponseEntity.ok(service.join(member, avatar));
    }


    @PutMapping("/{id}/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePassword) {
        service.changePassword(changePassword);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change/{userName}")
    public ResponseEntity<Integer> changeProfile(
            @PathVariable String userName,
            @RequestBody UserProfileUpdateRequest updatedData) {

        // 서비스 계층에서 userName을 사용해 해당 사용자 정보를 찾아 프로필 업데이트
        int result = service.updateUserProfile(userName, updatedData);

        if (result > 0) {
            // 업데이트 성공
            return ResponseEntity.ok(result);
        } else {
            // 업데이트 실패
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Member> delete(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/join")
    public ResponseEntity<Member> joinMember(String userName){
        Member result = service.getMemberInfo(userName);
        return ResponseEntity.ok(result);
    }


}
