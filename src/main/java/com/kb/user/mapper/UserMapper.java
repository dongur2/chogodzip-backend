package com.kb.user.mapper;

import com.kb.user.dto.Auth;
import com.kb.user.dto.User;


import com.kb.user.dto.UserOptInfo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    //조회
    User selectByKakaoId(String username);
    User selectByNickname(String nickname);
    User selectByUserId(Long userId);

    //마이페이지 - 실거주지 조회
    String selectRealRegionByUserId(Long userId);

    //회원가입
    int insertUser(User user);
    int insertUserOptInfo(UserOptInfo userOptInfo);
    int insertAuth(Auth auth);

    //회원정보 수정
    int updateUser(@Param("userId") Long userId, @Param("nickname") String nickname, @Param("pic") String pic);
    int updateUserOptInfo(@Param("userId") Long userId, @Param("realRegion") String realRegion, @Param("interestGu") String interestGu);


    //    List<User> selectMemberAll();
//    int deleteMember(long mno);
//    int deleteAuth(Auth auth);
//
//    Long searchOneMember(@Param("userName") String userName);
//    String findPicOfMember(@Param("userId") Long userId);
//
//    User oneMeme(Long userId);
//
//    User getOneInfo(String userId);
}
