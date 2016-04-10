package com.cinema.dto;

import com.cinema.model.Role;

import java.time.LocalDate;

public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate date;
    private String login;
    private String password;
    private String email;
    Role role;

    public UserDTO() {
    }

    public UserDTO(Integer id, String firstName, String lastName, int year, int month, int day, String login, String password, String email, Role role) {
        setId(id);
        setRole(role);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setLogin(login);
        setPassword(password);
        setDate(year, month, day);
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(int year, int month, int day) {
        date = LocalDate.of(year, month, day);
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date=" + date +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}' + "\n";
    }
}
