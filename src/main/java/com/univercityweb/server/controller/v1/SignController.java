package com.univercityweb.server.controller.v1;

import com.univercityweb.server.dto.sign.UserLoginRequestDto;
import com.univercityweb.server.dto.sign.UserSignupRequestDto;
import com.univercityweb.server.model.response.SingleResult;
import com.univercityweb.server.service.response.ResponseService;
import com.univercityweb.server.service.security.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"1. SignUp/LogIn"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/sign")
public class SignController {
    private final SignService signService;
    private final ResponseService responseService;

    @ApiOperation(value = "로그인", notes = "이메일로 로그인을 합니다.")
    @PostMapping("/login")
    public SingleResult<String> login(
            @ApiParam(value = "로그인 요청 DTO", required = true)
            @RequestBody UserLoginRequestDto userLoginRequestDto) {
        String loginupId = signService.login(userLoginRequestDto);
        return responseService.getSingleResult(loginupId);
    }

    @ApiOperation(value = "회원가입", notes = "회원가입을 합니다.")
    @PostMapping("/signup")
    public SingleResult<String> signup(
            @ApiParam(value = "회원 가입 요청 DTO", required = true)
            @RequestBody UserSignupRequestDto userSignupRequestDto) {
        String signupId = signService.signup(userSignupRequestDto);
        return responseService.getSingleResult(signupId);
    }
}