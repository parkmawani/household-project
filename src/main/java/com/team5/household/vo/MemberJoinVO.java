package com.team5.household.vo;

import com.team5.household.entity.MemberInfoEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberJoinVO {
    private String MemberEmail;
    private String MemberPwd;
    private String Membernickname;

    // public MemberJoinVO(MemberInfoEntity entity){
    //     this.MemberEmail = entity.getMiEmail();
    //     this.Membernickname = entity.getMiNickname();
    //     return new MemberJoinVO(entity);
    // }
}