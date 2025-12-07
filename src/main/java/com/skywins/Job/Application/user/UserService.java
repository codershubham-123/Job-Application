package com.skywins.Job.Application.user;

import com.skywins.Job.Application.auth.AuthResponse;
import com.skywins.Job.Application.auth.LoginRequest;
import com.skywins.Job.Application.auth.SignupRequest;


public interface UserService {

    AuthResponse signup(SignupRequest req);

    AuthResponse login(LoginRequest req);

}
