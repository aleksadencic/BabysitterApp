package com.example.aleksadencic.bebysitterapplication.domain;

public class Comment {

    private int id;
    private String description;
    private Post post;
    private User user;

    public Comment() {
    }

    public Comment(int id, String description, Post post, User user) {
        this.id = id;
        this.description = description;
        this.post = post;
        this.user = user;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (getId() != comment.getId()) return false;
        if (getDescription() != null ? !getDescription().equals(comment.getDescription()) : comment.getDescription() != null)
            return false;
        if (getPost() != null ? !getPost().equals(comment.getPost()) : comment.getPost() != null)
            return false;
        return getUser() != null ? getUser().equals(comment.getUser()) : comment.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPost() != null ? getPost().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
