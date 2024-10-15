package com.kb.community.dto.response;

import com.kb.community.vo.CommunityCmt;
import lombok.*;

import java.util.Date;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class CommentDetailDTO {
    private Long cmtId;
    private Long mNo;
    private String memberId;
    private String memberName;
    private String memberPic;
    private String content;
    private Date createdAt;

    //VO => DTO
    public static CommentDetailDTO from(CommunityCmt vo) {
        return CommentDetailDTO.builder()
                .cmtId(vo.getCmtId())
                .mNo(vo.getMNo())
                .memberId(vo.getMemberId())
                .memberName(vo.getMemberName())
                .content(vo.getContent())
                .createdAt(vo.getCreatedAt())
                .build();
    }
}
