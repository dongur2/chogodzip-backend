package com.kb.member.mapper;

import com.kb.member.dto.Auth;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.Member;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    List<Member> selectMemberAll();
    Member selectByNo(Long no);
    Member selectById(String id);
    Member selectBykakaoId(String kakaoId);
    int insertMember(Member member);
    int updateMember(Member member);
    int updatePassword(ChangePasswordDTO changePasswordDTO);
    int deleteMember(long mno);
    int insertAuth(Auth auth);
    int deleteAuth(Auth auth);

    Long searchOneMember(@Param("userName") String userName);
    String findPicOfMember(@Param("userId") Long userId);

    Member oneMeme(Long userId);
}
