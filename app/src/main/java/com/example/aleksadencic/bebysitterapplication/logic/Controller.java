package com.example.aleksadencic.bebysitterapplication.logic;

import com.example.aleksadencic.bebysitterapplication.db.DatabaseBroker;
import com.example.aleksadencic.bebysitterapplication.domain.City;
import com.example.aleksadencic.bebysitterapplication.domain.Post;
import com.example.aleksadencic.bebysitterapplication.domain.Settlement;
import com.example.aleksadencic.bebysitterapplication.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static Controller instance;

    private Controller(){
    }

    public static Controller getInstance(){
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try {
            DatabaseBroker.getInstance().connect();
            users = DatabaseBroker.getInstance().getAllUsers();
            DatabaseBroker.getInstance().disconnect();
        } catch(Exception ex){
            System.out.println("Controller: Error in getAllUsers() function!\n" + ex);
        }
        return users;

    }

    public void insertUser(User user) {
        try {
            DatabaseBroker.getInstance().connect();
            boolean inserted = DatabaseBroker.getInstance().insertUser(user);
            if(inserted)
                DatabaseBroker.getInstance().commmit();
            else
                DatabaseBroker.getInstance().rollback();
            DatabaseBroker.getInstance().disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        try {
            DatabaseBroker.getInstance().connect();
            cities = DatabaseBroker.getInstance().getAllCities();
            DatabaseBroker.getInstance().disconnect();
        } catch(Exception ex){
            System.out.println("Controller: Error in getAllCities() function!\n" + ex);
        }
        return cities;
    }

    public List<Settlement> getSettlements(City selectedCity) {
        List<Settlement> settlements = new ArrayList<>();
        try {
            DatabaseBroker.getInstance().connect();
            settlements = DatabaseBroker.getInstance().getSettlements(selectedCity);
            DatabaseBroker.getInstance().disconnect();
        } catch(Exception ex){
            System.out.println("Controller: Error in getSettlements() function!\n" + ex);
        }
        return settlements;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try {
            DatabaseBroker.getInstance().connect();
            posts = DatabaseBroker.getInstance().getAllPosts();
            DatabaseBroker.getInstance().disconnect();
        } catch(Exception ex){
            System.out.println("Controller: Error in getAllPosts() function!\n" + ex);
        }
        return posts;
    }

    public List<Post> getPosts(String settlement, String date) {
        List<Post> posts = new ArrayList<>();
        try {
            DatabaseBroker.getInstance().connect();
            posts = DatabaseBroker.getInstance().getPosts(settlement, date);
            DatabaseBroker.getInstance().disconnect();
        } catch(Exception ex){
            System.out.println("Controller: Error in getPosts() function!\n" + ex);
        }
        return posts;
    }
}
