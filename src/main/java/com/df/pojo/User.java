package com.df.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type User.
 *
 * @author MFine
 */
public class User {

    @JsonProperty("id")
    private Integer id;
    private String name;
    private String password;

    /**
     * Instantiates a new User.
     *
     * @param id       the id
     * @param name     the name
     * @param password the password
     */
    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    //    public User(String name, String password) {
//        this.name = name;
//        this.password = password;
//    }

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}