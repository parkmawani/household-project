package com.team5.household.expense.vo;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinVO {
    @NotNull
    @Email
    private String email;
    
    @Pattern(regexp = "^[a-zA-Z\\d`~!@#$%^&*()-_=+]{6,}$")
    private String pwd;

    @NotBlank
    private String nickname;

    @Nullable
    private String role;
    // public MemberJoinVO(MemberInfoEntity entity){
    //     this.MemberEmail = entity.getMiEmail();
    //     this.Membernickname = entity.getMiNickname();
    //     return new MemberJoinVO(entity);
    // }
}