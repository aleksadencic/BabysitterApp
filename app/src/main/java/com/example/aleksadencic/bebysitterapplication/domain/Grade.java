package com.example.aleksadencic.bebysitterapplication.domain;

public class Grade {

    private int id;
    private int number;
    private User userRated;
    private User userEstimated;

    public Grade() {
    }

    public Grade(int id, int number, User userRated, User userEstimated) {
        this.id = id;
        this.number = number;
        this.userRated = userRated;
        this.userEstimated = userEstimated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getUserRated() {
        return userRated;
    }

    public void setUserRated(User userRated) {
        this.userRated = userRated;
    }

    public User getUserEstimated() {
        return userEstimated;
    }

    public void setUserEstimated(User userEstimated) {
        this.userEstimated = userEstimated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade)) return false;

        Grade grade = (Grade) o;

        if (getId() != grade.getId()) return false;
        if (getNumber() != grade.getNumber()) return false;
        if (getUserRated() != null ? !getUserRated().equals(grade.getUserRated()) : grade.getUserRated() != null)
            return false;
        return getUserEstimated() != null ? getUserEstimated().equals(grade.getUserEstimated()) : grade.getUserEstimated() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNumber();
        result = 31 * result + (getUserRated() != null ? getUserRated().hashCode() : 0);
        result = 31 * result + (getUserEstimated() != null ? getUserEstimated().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "number=" + number +
                ", userRated=" + userRated +
                ", userEstimated=" + userEstimated +
                '}';
    }
}
