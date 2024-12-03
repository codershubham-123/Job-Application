package com.skywins.Job.Application.user;

public interface UserService {
    User registerUser(User user);
    User loginUser(String username, String password);

}
