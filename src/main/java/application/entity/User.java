package application.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String copyPassword;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private Date dateOfBirth;
    private String gender;
    private String bug;
    private String comment;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCopyPassword() {
        return copyPassword;
    }

    public void setCopyPassword(String copyPassword) {
        this.copyPassword = copyPassword;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBug() {
        return bug;
    }

    public void setBug(String bug) {
        this.bug = bug;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
