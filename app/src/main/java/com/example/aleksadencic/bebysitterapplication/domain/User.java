package com.example.aleksadencic.bebysitterapplication.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class User implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("mail")
    private String mail;

    @SerializedName("role")
    private String role;

    @SerializedName("gender")
    private String gender;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public User() {
    }

    public User(int id, String firstName, String lastName, String mail, String role, String gender, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.role = role;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String mail, String role, String gender, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.role = role;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }

    protected User(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        mail = in.readString();
        role = in.readString();
        gender = in.readString();
        username = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getMail() != null ? !getMail().equals(user.getMail()) : user.getMail() != null)
            return false;
        if (getRole() != null ? !getRole().equals(user.getRole()) : user.getRole() != null)
            return false;
        if (getGender() != null ? !getGender().equals(user.getGender()) : user.getGender() != null)
            return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getMail() != null ? getMail().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(mail);
        parcel.writeString(role);
        parcel.writeString(gender);
        parcel.writeString(username);
        parcel.writeString(password);
    }

}
