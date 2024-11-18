package com.kb.user.service;

import com.kb.user.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kb.user.mapper.UserMapper;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //* 쿼리가 정상적으로 수행되었는지 확인
    private void isSuccessed(int result) throws IllegalAccessException {
        if(result != 1) throw new IllegalAccessException();
    }

//    public User getMember(String id) {
//        return Optional.ofNullable(mapper.selectById(id))
//                        .orElseThrow(NoSuchElementException::new);

//    }

//    public User update(User updateMember, MultipartFile avatar) throws IllegalAccessException {
//        User oldMember = mapper.selectById(updateMember.getUsername());
//        if(!passwordEncoder.matches(updateMember.getPassword(),oldMember.getPassword())) {
//            throw new PasswordMissmatchException();
//        }
////        updateMember.setMno(oldMember.getMno());
//        mapper.updateMember(updateMember);
//        if(avatar != null && !avatar.isEmpty()) {
//            saveAvatar(avatar, oldMember.getUsername());
//        }
//        return mapper.selectById(updateMember.getUsername());
//    }

//    public User delete(String id) {
//        User member = mapper.selectById(id);
////        mapper.deleteMember(member.getMno());
//        return member;
//    }
//
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
//    public int updateUserProfile(String userId, UserProfileUpdateRequest updatedData) {
//
//        User user = mapper.getOneInfo(userId);
//
//        if (user != null) {
//            // 사용자 정보를 업데이트
//
//            user.setUsername(userId);
//            user.setNickname(updatedData.getName());
////            user.setAddress(updatedData.getAddress());
////            user.setInterestArea(updatedData.getInterestArea());
////            user.setProfileImg(updatedData.getProfileImg());
//
//            // MyBatis로 업데이트 쿼리 실행
//            return mapper.updateMemberInfo(user);
//        } else {
//            // 사용자 정보가 없으면 0 반환 (업데이트 실패)
//            return 0;
//        }
//    }
}