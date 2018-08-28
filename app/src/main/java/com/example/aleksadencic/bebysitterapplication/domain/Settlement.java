package com.example.aleksadencic.bebysitterapplication.domain;

public class Settlement {

    private int id;
    private City city;
    private String name;

    public Settlement() {
    }

    public Settlement(int id, City city, String name) {
        this.id = id;
        this.city = city;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Settlement)) return false;

        Settlement that = (Settlement) o;

        if (getId() != that.getId()) return false;
        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null)
            return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
