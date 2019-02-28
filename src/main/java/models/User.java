package models;

import java.sql.Timestamp;

public class User {

    private int Id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private int birthday;
    private Timestamp creation_time;
    private String avatar;
    private boolean type_id;
    private String user_sex;
    private boolean enabled;


    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthday() {
        return birthday;
    }
    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public Timestamp getCreation_time() {
        return creation_time;
    }
    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isType_id() {
        return type_id;
    }
    public void setType_id(boolean type_id) {
        this.type_id = type_id;
    }

    public String getUser_sex() {
        return user_sex;
    }
    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
