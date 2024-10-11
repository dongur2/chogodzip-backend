package com.kb.community.dto.request;

import com.kb.community.vo.Community;
import lombok.*;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class CommunityPostDTO {
    private Long mNo;
    private String title;
    private String content;
    private String tag;


    //DTO => VO
    public Community toVO() {
        return Community.builder()
                .mNo(mNo)
                .title(title)
                .content(content)
                .tag(tag)
                .build();
    }
}
