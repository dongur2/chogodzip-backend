package com.kb.member.controller;

import com.kb.common.util.UploadFiles;
import com.kb.kakao.KaKaoLoginService;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.Member;
import com.kb.member.dto.MemberDTO;
import com.kb.member.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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




    @PutMapping("/kakaoInfo/{code}")
    public ResponseEntity<Member> changeProfile(MemberDTO memberDTO,
                                                @RequestParam(name = "avatar", required = false) MultipartFile avatar) throws IllegalAccessException {
        Member member = memberDTO.toMember();
        return ResponseEntity.ok(service.update(member, avatar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Member> delete(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }


}
