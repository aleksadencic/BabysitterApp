package com.example.aleksadencic.bebysitterapplication.domain;

public class Parent {

    private User user;
    private String description;

    public Parent() {
    }

    public Parent(User user, String description) {
        this.user = user;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parent)) return false;

        Parent parent = (Parent) o;

        if (getUser() != null ? !getUser().equals(parent.getUser()) : parent.getUser() != null)
            return false;
        return getDescription() != null ? getDescription().equals(parent.getDescription()) : parent.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getUser() != null ? getUser().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return user.getFirstName() + " " + user.getLastName();
    }

}
