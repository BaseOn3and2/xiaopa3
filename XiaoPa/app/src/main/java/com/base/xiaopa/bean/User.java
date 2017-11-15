package com.base.xiaopa.bean;

import com.base.xiaopa.db.NewFriend;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**用户表
 * Created by cldn1 on 2017/11/10.
 */
public class User extends BmobUser {
    //头像
    private String avatar;
    private  String college;  //院校
    private  String academy;  //学院
    private  String major;    //专业
    private  Boolean gender;  //0为女，1为男
    public User(){}

    public User(NewFriend friend){
        setObjectId(friend.getUid());
        setUsername(friend.getName());
        setAvatar(friend.getAvatar());
    }

    public String getAvatar(){
        return avatar;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
}
