package com.team5.household.expense.vo.memberVO;

import com.team5.household.expense.Security.VO.TokenVO;

import lombok.Data;

@Data
public class MemberResponseVO {
    private String email;
    private String nickname;
    private String message;
    private String role;
    private Long seq;
    private TokenVO token;
    
    public void setMemberResponseVO(String email, String nickname){
        this.email = email;
        this.nickname = nickname;
    }
}