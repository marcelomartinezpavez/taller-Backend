package com.personal.taller.response;

import com.personal.taller.dto.UsersDto;

public class LoginResponse {

    private UsersDto users;

    public UsersDto getUsers() {
        return users;
    }

    public void setUsers(UsersDto users) {
        this.users = users;
    }
}
