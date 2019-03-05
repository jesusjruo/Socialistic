package models;

public class User {

    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String birthday;
    //private String creation_time;
    private String avatar;
    private String user_sex;
    //private boolean enabled;


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
    public void setName(String name) {this.name = name; }

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

    public String getBirthday() {
        return String.valueOf(birthday);
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    /*public boolean isEnabled() {return enabled; }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }*/
}
