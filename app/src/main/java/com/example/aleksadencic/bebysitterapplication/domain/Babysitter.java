package com.example.aleksadencic.bebysitterapplication.domain;

public class Babysitter {

    private User user;
    private String about;
    private String education;
    private String experience;

    public Babysitter() {
    }

    public Babysitter(User user, String about, String education, String experience) {
        this.user = user;
        this.about = about;
        this.education = education;
        this.experience = experience;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Babysitter)) return false;

        Babysitter that = (Babysitter) o;

        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null)
            return false;
        if (getAbout() != null ? !getAbout().equals(that.getAbout()) : that.getAbout() != null)
            return false;
        if (getEducation() != null ? !getEducation().equals(that.getEducation()) : that.getEducation() != null)
            return false;
        return getExperience() != null ? getExperience().equals(that.getExperience()) : that.getExperience() == null;
    }

    @Override
    public int hashCode() {
        int result = getUser() != null ? getUser().hashCode() : 0;
        result = 31 * result + (getAbout() != null ? getAbout().hashCode() : 0);
        result = 31 * result + (getEducation() != null ? getEducation().hashCode() : 0);
        result = 31 * result + (getExperience() != null ? getExperience().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return user.getFirstName() + " " + user.getLastName();
    }
}
