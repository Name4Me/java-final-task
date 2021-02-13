package com.example.app.model.user;

import java.io.Serializable;
import java.util.Arrays;

public class User implements Serializable{
    private int id;
    private String email;
    private byte[] password;
    private UserType userType;
    private UserStatus userStatus;

    public User() {}

    public User(int id, String email, byte[] password, UserType userType, UserStatus userStatus) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.userStatus = userStatus;
    }

    public User(String email, byte[] password, UserType userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (id != user.id) return false;
        if (!email.equals(user.email)) return false;
        if (!Arrays.equals(password, user.password)) return false;
        return userType == user.userType;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + Arrays.hashCode(password);
        result = 31 * result + userType.hashCode();
        return result;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
