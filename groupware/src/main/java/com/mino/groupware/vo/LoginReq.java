package com.mino.groupware.vo;

import com.mino.groupware.jwt.NewToken;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginReq {
    private String user_id;

    private String user_pswd;

    public String getUser_id() {
        return user_id;
    }

    public String getUser_pswd() {
        return user_pswd;
    }
}