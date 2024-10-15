package com.kb.member.service;

import com.kb.member.dto.Auth;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.UserProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kb.member.dto.Member;
import com.kb.member.exception.PasswordMissmatchException;
import com.kb.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class MemberService{

    private final MemberMapper memberMapper;
    @Value("#{'${os_type}' == 'win' ? '${file_save_location_win}':'${file_save_location_other}'}")
    public String LOCATION;

    final PasswordEncoder passwordEncoder;
    final MemberMapper mapper;

    public Member login(Member member) {
        Member saveMember = mapper.selectById(member.getId());
        if(passwordEncoder.matches(member.getPassword(), saveMember.getPassword())) {
            saveMember.setPassword("");
            saveMember.setMno(0);
            return saveMember;
        }else{
            return null;
        }
    }

    public boolean checkDuplicate(String id) {
        Member member = mapper.selectById(id);
        return member != null;
    }

    public Member getMemberByKakaoId(String kakao_id) {
        return Optional.ofNullable(mapper.selectBykakaoId(kakao_id))
                .orElseThrow(NoSuchElementException::new);
    }

    public Member getMember(String id) {
        return Optional.ofNullable(mapper.selectById(id))
                        .orElseThrow(NoSuchElementException::new);
    }

    private void saveAvatar(MultipartFile avatar, String id) {
        //아바타 업로드
        if(avatar != null && !avatar.isEmpty()) {
            File dir = new File(LOCATION + "/avatar");
            if(!dir.exists()){
                dir.mkdirs();
            }
            File dest = new File( LOCATION + "/avatar", id + ".png");
            if(!dest.exists()){
                dest.delete();
            }
            try {
                avatar.transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Member join(Member member) throws IllegalAccessException {
        if(member.checkRequiredValue()){
            throw new IllegalAccessException();
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        int result = mapper.insertMember(member);
        if(result != 1){
            throw new IllegalAccessException();
        }
        Auth auth = new Auth(member.getId(), "ROLE_MEMBER");
        result = mapper.insertAuth(auth);
        if(result != 1){
            throw new IllegalAccessException();
        }
        return mapper.selectById(member.getId());
    }

    public Member update(Member updateMember, MultipartFile avatar) throws IllegalAccessException {
        Member oldMember = mapper.selectById(updateMember.getId());
        if(!passwordEncoder.matches(updateMember.getPassword(),oldMember.getPassword())) {
            throw new PasswordMissmatchException();
        }
        updateMember.setMno(oldMember.getMno());
        mapper.updateMember(updateMember);
        if(avatar != null && !avatar.isEmpty()) {
            saveAvatar(avatar, oldMember.getId());
        }
        return mapper.selectById(updateMember.getId());
    }

    public Member delete(String id) {
        Member member = mapper.selectById(id);
        mapper.deleteMember(member.getMno());
        return member;
    }

    public void changePassword(ChangePasswordDTO changePassword) {
        Member member = mapper.selectById(changePassword.getId());
//        System.out.println(changePassword);
        if(!passwordEncoder.matches(
                changePassword.getOldPassword(),
                member.getPassword()
        )) {
              throw new PasswordMissmatchException();
        }
        changePassword.setNewPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        mapper.updatePassword(changePassword);
    }

    public Boolean checkKaKaoDuplicate(String id) {
        Member member = mapper.selectBykakaoId(id);
        if(member != null){
            return true;
        } else {
            return false;
        }

    }

    public Long searchOneMember(String userName) {
        return memberMapper.searchOneMember(userName);
    }

    // 프로필 사진만 조회
    public String getPicOfMember(Long userId) {
        return memberMapper.findPicOfMember(userId);
    }


    public Member getIdMem(Long userId) {
        return memberMapper.oneMeme(userId);
    }

    public Member getMemberInfo(String userName) {


        return memberMapper.getOneInfo(userName);
    }

    public int updateUserProfile(String userName, UserProfileUpdateRequest updatedData) {

        Member member = memberMapper.getOneInfo(userName);

        if (member != null) {
            // 사용자 정보를 업데이트

            member.setAddress(updatedData.getAddress());
            member.setInterestArea(updatedData.getInterestArea());

            // MyBatis로 업데이트 쿼리 실행
            return memberMapper.updateMemberInfo(member);
        } else {
            // 사용자 정보가 없으면 0 반환 (업데이트 실패)
            return 0;
        }

    }

}