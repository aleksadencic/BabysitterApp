package com.example.aleksadencic.bebysitterapplication.domain;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Post {

    private int id;
    private String description;
    private Date date;
    private Time timeFrom;
    private Time timeTo;
    private User user;
    private Settlement location;

    public Post(int id, String description, Date date, Time timeFrom, Time timeTo, User user, Settlement location) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.user = user;
        this.location = location;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Settlement getLocation() {
        return location;
    }

    public void setLocation(Settlement location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        if (getId() != post.getId()) return false;
        if (getDescription() != null ? !getDescription().equals(post.getDescription()) : post.getDescription() != null)
            return false;
        if (getDate() != null ? !getDate().equals(post.getDate()) : post.getDate() != null)
            return false;
        if (getTimeFrom() != null ? !getTimeFrom().equals(post.getTimeFrom()) : post.getTimeFrom() != null)
            return false;
        if (getTimeTo() != null ? !getTimeTo().equals(post.getTimeTo()) : post.getTimeTo() != null)
            return false;
        if (getUser() != null ? !getUser().equals(post.getUser()) : post.getUser() != null)
            return false;
        return getLocation() != null ? getLocation().equals(post.getLocation()) : post.getLocation() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getTimeFrom() != null ? getTimeFrom().hashCode() : 0);
        result = 31 * result + (getTimeTo() != null ? getTimeTo().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
