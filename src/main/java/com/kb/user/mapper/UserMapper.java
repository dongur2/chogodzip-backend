package com.kb.user.mapper;

import com.kb.user.dto.Auth;
import com.kb.user.dto.User;


import com.kb.user.dto.UserOptInfo;

public interface UserMapper {
    //조회
    User selectByKakaoId(String username);
    User selectByNickname(String nickname);
    User selectByUserId(Long userId);

    //회원가입
    int insertUser(User user);
    int insertUserOptInfo(UserOptInfo userOptInfo);
    int insertAuth(Auth auth);

//    List<User> selectMemberAll();
//    int updateMember(User member);
//    int deleteMember(long mno);
//    int deleteAuth(Auth auth);
//
//    Long searchOneMember(@Param("userName") String userName);
//    String findPicOfMember(@Param("userId") Long userId);
//
//    User oneMeme(Long userId);
//
//    User getOneInfo(String userId);
//
//    int updateMemberInfo(User member);
}
