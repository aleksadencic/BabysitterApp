package com.example.aleksadencic.bebysitterapplication.db;

import android.os.StrictMode;

import com.example.aleksadencic.bebysitterapplication.domain.City;
import com.example.aleksadencic.bebysitterapplication.domain.Post;
import com.example.aleksadencic.bebysitterapplication.domain.Settlement;
import com.example.aleksadencic.bebysitterapplication.domain.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DatabaseBroker {

    private Connection connection;
    private static DatabaseBroker instance;

    //private static final String url = "jdbc:mysql://10.20.237.3:3306/baby_sitters_db";
    private static final String url = "jdbc:mysql://10.20.237.3:3306/baby_sitters_db";
    private static final String username = "test";
    private static final String password = "test123";

    private DatabaseBroker() {
    }

    public static DatabaseBroker getInstance() throws Exception{
        if(instance == null){
            instance = new DatabaseBroker();
        }
        return instance;
    }

    public void connect() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

        } catch (Exception ex) {
            System.out.println("Error in connect function!\n" + ex);
        }
    }

    public void disconnect() throws Exception {
        if (connection == null) {
            throw new Exception("Error in disconnect function!\n");
        }
        connection.close();
    }

    public void commmit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException{
        connection.rollback();
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet rsUsers = statement.executeQuery(sql);

            while (rsUsers.next()) {
                int id = rsUsers.getInt("id");
                String firstName = rsUsers.getString("first_name");
                String lastName = rsUsers.getString("last_name");
                String mail = rsUsers.getString("mail");
                String role = rsUsers.getString("role");
                String gender = rsUsers.getString("gender");
                String username = rsUsers.getString("username");
                String password = rsUsers.getString("password");
                User user = new User(id, firstName, lastName, mail, role, gender, username, password);
                users.add(user);
            }

        } catch (SQLException ex) {
            System.out.println("DatabaseBroker: Error in getAllUsers() function!\n" + ex);
        }
        return users;
    }

    public boolean insertUser(User user) {
        try {
            String sql = "INSERT INTO user VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, getMaxId("user") + 1);
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getMail());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getGender());
            ps.setString(7, user.getUsername());
            ps.setString(8, user.getPassword());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    private int getMaxId(String table) {

        int maxId = 0;
        try {
            String sql = "SELECT * FROM " + table;
            Statement statement = connection.createStatement();
            ResultSet rsUsers = statement.executeQuery(sql);

            while (rsUsers.next()) {
                if(maxId < rsUsers.getInt("id")) maxId = rsUsers.getInt("id");
            }

        } catch (SQLException ex) {
            System.out.println("DatabaseBroker: Error in getAllUsers() function!\n" + ex);
        }
        return maxId;
    }

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM city ORDER BY name ASC";
        try {
            Statement statement = connection.createStatement();
            ResultSet rsCities = statement.executeQuery(sql);

            while (rsCities.next()) {
                int id = rsCities.getInt("id");
                String name = rsCities.getString("name");
                City city = new City(id, name);
                cities.add(city);
            }

        } catch (SQLException ex) {
            System.out.println("DatabaseBroker: Error in getAllCities() function!\n" + ex);
        }
        return cities;
    }

    public List<Settlement> getSettlements(City selectedCity) {
        List<Settlement> settlements = new ArrayList<>();
        String sql = "SELECT * FROM settlement " +
                     "WHERE city_id = " + selectedCity.getId() +
                     " ORDER BY name ASC";
        try {
            Statement statement = connection.createStatement();
            ResultSet rsSettlements = statement.executeQuery(sql);

            while (rsSettlements.next()) {
                int id = rsSettlements.getInt("id");
                String name = rsSettlements.getString("name");
                int city_id = rsSettlements.getInt("city_id");
                City city = getCity(city_id);
                Settlement settlement = new Settlement(id, city, name);
                settlements.add(settlement);
            }

        } catch (SQLException ex) {
            System.out.println("DatabaseBroker: Error in getSettlements() function!\n" + ex);
        }
        return settlements;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post ORDER BY date DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet rsPosts = statement.executeQuery(sql);

            while (rsPosts.next()) {
                int id = rsPosts.getInt("id");
                String description = rsPosts.getString("description");
                java.util.Date date = rsPosts.getDate("date");
                Time timeFrom = rsPosts.getTime("timeFrom");
                Time timeTo = rsPosts.getTime("timeTo");
                User user = getUser(rsPosts.getInt("user_id"));
                Settlement settlement = getSettlement(rsPosts.getInt("settlement_id"));
                Post post = new Post(id, description, date, timeFrom, timeTo, user, settlement);
                posts.add(post);
            }

        } catch (SQLException ex) {
            System.out.println("DatabaseBroker: Error in getAllPosts() function!\n" + ex);
        }
        return posts;
    }

    private User getUser(int user_id) {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = " + user_id;
        try {
            Statement statement = connection.createStatement();
            ResultSet rsUsers = statement.executeQuery(sql);

            while (rsUsers.next()) {
                int id = rsUsers.getInt("id");
                String firstName = rsUsers.getString("first_name");
                String lastName = rsUsers.getString("last_name");
                String mail = rsUsers.getString("mail");
                String role = rsUsers.getString("role");
                String gender = rsUsers.getString("gender");
                String username = rsUsers.getString("username");
                String password = rsUsers.getString("password");
                user = new User(id, firstName, lastName, mail, role, gender, username, password);
            }

        } catch (SQLException ex) {
            System.out.println("DatabaseBroker: Error in getUser() function!\n" + ex);
        }
        return user;
    }

    private Settlement getSettlement(int settlement_id) {
        Settlement settlement = null;
        String sql = "SELECT * FROM settlement WHERE id = " + settlement_id;
        try {
            Statement statement = connection.createStatement();
            ResultSet rsSettlement = statement.executeQuery(sql);

            while (rsSettlement.next()) {
                int id = rsSettlement.getInt("id");
                String name = rsSettlement.getString("name");
                City selectedCity = getCity(rsSettlement.getInt("city_id"));
                settlement = new Settlement(id, selectedCity, name);
            }

        } catch (SQLException ex) {
            System.out.println("DatabaseBroker: Error in getSettlement() function!\n" + ex);
        }
        return settlement;
    }

    private City getCity(int city_id) {
        City city = null;
        String sql = "SELECT * FROM city WHERE id = " + city_id;
        try {
            Statement statement = connection.createStatement();
            ResultSet rsCity = statement.executeQuery(sql);

            while (rsCity.next()) {
                int id = rsCity.getInt("id");
                String name = rsCity.getString("name");
                city = new City(id, name);
            }

        } catch (SQLException ex) {
            System.out.println("DatabaseBroker: Error in getCity() function!\n" + ex);
        }
        return city;
    }

    public List<Post> getPosts(String settlement, String date) throws ParseException {
        List<Post> posts = new ArrayList<>();
        Settlement s = getSettlement(settlement);

        String sql = "SELECT * FROM post WHERE settlement_id = " + s.getId() /* + " AND date = '" + date + "'" */ + " ORDER BY timeFrom DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet rsPosts = statement.executeQuery(sql);

            while (rsPosts.next()) {
                int id = rsPosts.getInt("id");
                String description = rsPosts.getString("description");
                java.util.Date date_new = rsPosts.getDate("date");
                Time timeFrom = rsPosts.getTime("timeFrom");
                Time timeTo = rsPosts.getTime("timeTo");
                User user = getUser(rsPosts.getInt("user_id"));
                Settlement settlement_new = getSettlement(rsPosts.getInt("settlement_id"));
                Post post = new Post(id, description, date_new, timeFrom, timeTo, user, settlement_new);
                posts.add(post);
            }

        } catch (SQLException ex) {
            System.out.println("DatabaseBroker: Error in getPosts() function!\n" + ex);
        }
        return posts;
    }

    private Settlement getSettlement(String s) {
        Settlement settlement = null;
        String sql = "SELECT * FROM settlement WHERE name = '" + s + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rsSettlement = statement.executeQuery(sql);

            while (rsSettlement.next()) {
                int id = rsSettlement.getInt("id");
                String name = rsSettlement.getString("name");
                City selectedCity = getCity(rsSettlement.getInt("city_id"));
                settlement = new Settlement(id, selectedCity, name);
            }

        } catch (SQLException ex) {
            System.out.println("DatabaseBroker: Error in getSettlement() function!\n" + ex);
        }
        return settlement;
    }
}
