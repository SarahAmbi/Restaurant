package ch.bzz.restaurant.model;


import java.util.List;
/**
 * a user for authentication / authorization
 */
public class User {
    private String userUUID;
    private String username;
    private String password;
    private String role;

    public User(){
        setRole("guest");
    }

    /**
     * gets userUUID
     * @return value of userUUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * sets userUUID
     *
     * @param userUUID the value to set
     */
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    /**
     * gets username
     *
     * @return value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets username
     *
     * @param username the value to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets password of user
     *
     * @return value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password of user
     *
     * @param password the value to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets user role
     *
     * @return value of user role
     */
    public String getRole() {
        return role;
    }

    /**
     * sets user role
     * @param role the value to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}