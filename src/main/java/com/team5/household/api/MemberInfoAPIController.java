package com.team5.household.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.service.MemberService;
import com.team5.household.vo.MemberJoinVO;
import com.team5.household.vo.MemberResponseVO;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberInfoAPIController {
    private final MemberService mService;

    @PostMapping("/join")
    public Map<String, Object> postMemberJoin(@RequestBody MemberJoinVO data){
        Map<String, Object> resultmap = new LinkedHashMap<>();
        MemberResponseVO response = mService.joinMember(data);
        resultmap.put("data", response);
        resultmap.put("status", HttpStatus.CREATED);
        return resultmap;
    }
    
<<<<<<< HEAD
    @PostMapping("login")
    public Map<String, Object> postMemberLogin(@RequestBody LoginVO login) {
        Map<String, Object> resultMap = mService.loginMember(login);
        System.out.println(login);
        return resultMap;
    }
=======
>>>>>>> 39fbe14b43a189bd30e2dd028c4e9137b48ab814
    
}
