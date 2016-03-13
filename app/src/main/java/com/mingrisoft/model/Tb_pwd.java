package com.mingrisoft.model;

/**
 * Created by Administrator on 2016/2/27.
 */
public class Tb_pwd {
    private  String password;

    public Tb_pwd(String password) {
        this.password = password;
    }

    public Tb_pwd() {
        super();
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
