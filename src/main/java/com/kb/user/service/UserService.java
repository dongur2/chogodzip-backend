package com.kb.user.service;

import com.kb.user.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kb.user.mapper.UserMapper;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class UserService {
    private final UserMapper mapper;

    //회원 정보 조회
    public User getMemberByKakaoId(String kakaoId) {
        return Optional.ofNullable(mapper.selectByKakaoId(kakaoId))
                .orElseThrow(NoSuchElementException::new);
    }

    //마이페이지 회원 실거주지 조회
    public UserProfileDTO getRealRegionWithUser(User user) {
        String realRegion = mapper.selectRealRegionByUserId(user.getUserId());
        return UserProfileDTO.from(user, realRegion);
    }

    //가입 이메일 여부 확인
    public Boolean checkKaKaoDuplicate(String username) {
        User member = mapper.selectByKakaoId(username);
        return member != null;
    }

    //닉네임 중복 확인
    public boolean checkDuplicate(String nickname) {
        User member = mapper.selectByNickname(nickname);
        return member != null;
    }

    //회원 가입
    @Transactional(rollbackFor = Exception.class)
    public User join(UserJoinDTO joinDTO) throws IllegalAccessException {
        User user = joinDTO.toUser();

        //유효성 검사
        if(user.checkRequiredValue()){
            throw new IllegalAccessException();
        }

        //회원 등록
        int result = mapper.insertUser(user);
        isSuccessed(result);

        //추가 정보 등록
        UserOptInfo optInfo = joinDTO.toOptInfo();
        optInfo.setUserId(user.getUserId());
        result = mapper.insertUserOptInfo(optInfo);
        isSuccessed(result);

        //권한 등록
        result = mapper.insertAuth(new Auth(user.getUserId(), "ROLE_MEMBER"));
        isSuccessed(result);

        return mapper.selectByUserId(user.getUserId());
    }

    //회원 정보 수정
    public void updateUserProfile(Long userId, Map<String, Object> info) throws IllegalAccessException {
        int result = mapper.updateUser(userId, info.get("nickname").toString(), info.get("pic").toString());
        isSuccessed(result);

        String realRegion = null;
        if(info.get("realRegion") != null) realRegion = info.get("realRegion").toString();
        result = mapper.updateUserOptInfo(userId, realRegion, info.get("interestGu").toString());
        isSuccessed(result);
    }

    //* 쿼리가 정상적으로 수행되었는지 확인
    private void isSuccessed(int result) throws IllegalAccessException {
        if(result != 1) throw new IllegalAccessException();
    }

//    public User getMember(String id) {
//        return Optional.ofNullable(mapper.selectById(id))
//                        .orElseThrow(NoSuchElementException::new);
//    }

//    public User delete(String id) {
//        User member = mapper.selectById(id);
////        mapper.deleteMember(member.getMno());
//        return member;
//    }
//
//
//    public Long searchOneMember(String userName) {
//        return mapper.searchOneMember(userName);
//    }
//
//    // 프로필 사진만 조회
//    public String getPicOfMember(Long userId) {
//        return mapper.findPicOfMember(userId);
//    }
//
//
//    public User getIdMem(Long userId) {
//        return mapper.oneMeme(userId);
//    }
//
//    public User getMemberInfo(String userId) {
//        return mapper.getOneInfo(userId);
//    }
//
}