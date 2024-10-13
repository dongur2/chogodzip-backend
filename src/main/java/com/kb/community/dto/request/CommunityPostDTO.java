package com.kb.community.dto.request;

import com.kb.community.vo.Community;
import lombok.*;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class CommunityPostDTO {
    private Long mNo;
    private String memberId;
    private String title;
    private String content;
    private String tag;
    private String pics;


    //DTO => VO
    public Community toVO() {
        return Community.builder()
                .mNo(mNo)
                .title(title)
                .content(content)
                .tag(tag)
                .pics(pics)
                .build();
    }
}
