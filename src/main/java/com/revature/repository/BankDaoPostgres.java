package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.revature.Main;

import com.revature.model.Account;
import com.revature.model.User;

public class BankDaoPostgres implements BankDao {

  private static Connection conn;

  private static Logger log = Logger.getLogger(Main.class);

  List<User> allUsers = new ArrayList<User>();

  static {
    try {
      conn = DriverManager.getConnection(System.getenv("connstring"), System.getenv("username"),
          System.getenv("password"));
      // "jdbc:postgresql://database-1.cs4xqzqqrfzt.us-east-1.rds.amazonaws.com:5432/postgres",
      // "postgres", "5235923a");
      log.info("Connected to the Database");
      System.out.println("You are Connected");
    } catch (SQLException e) {
      // TODO: handle exception
      System.out.println("You are not Connected");
      log.error("Connection to the Database Failed");
      e.printStackTrace();
    }
  }


  @Override
  public List<Account> getAllAccounts() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public List<User> getAllUsers() {

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = conn.prepareStatement("SELECT * FROM user_table");
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allUsers.add(new User(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"),
            rs.getString("username"), rs.getString("userpassword")));
      }
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return allUsers;
  }

  @Override
  public void saveUser(User user) {
    // TODO Auto-generated method stub
    PreparedStatement stmt = null;

    try {
      stmt = conn.prepareStatement(
          "INSERT INTO user_table(id, lastname, firstname, username, userpassword) VALUES(default, ?,?,?,?)");
      stmt.setString(1, user.getLastName());
      stmt.setString(2, user.getFirstName());
      stmt.setString(3, user.getUserName());
      stmt.setString(4, user.getPassword());

      stmt.execute();

      System.out.println("Account Created, please login to continue.");

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  @Override
  public void saveAccount(Account account) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateBalance(User user, double money) {
    // TODO Auto-generated method stub
    PreparedStatement stmt = null;

    try {
      stmt = conn.prepareStatement(
          "UPDATE account SET balance = ? WHERE user_id = ? AND account_Type = 'Checking'");
      stmt.setDouble(1, money);
      stmt.setInt(2, user.getId());

      stmt.execute();

      // System.out.println("Your Checking account balance has been updated");

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  @Override
  public void checkBalance() {
    // TODO Auto-generated method stub

  }

  @Override
  public void withdrawFunds(double money) {
    // TODO Auto-generated method stub

  }

  @Override
  public void login() {
    // TODO Auto-generated method stub

  }

  @Override
  public void logout() {
    // TODO Auto-generated method stub
  }



  @Override
  public void registerAccount() {
    // TODO Auto-generated method stub

  }

  @Override
  public User get(String username) {
    // TODO Auto-generated method stub
    User out = null;
    try {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_table WHERE username = ?");
      stmt.setString(1, username);
      stmt.execute();

      ResultSet rs = stmt.getResultSet();
      rs.next();
      out = new User(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"),
          rs.getString("username"), rs.getString("userpassword"));
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return out;
  }

  @Override
  public void getUser(User user) {
    // TODO Auto-generated method stub

  }

  /*
   * 
   * @Override public Account getUserAccounts(int userid) { // TODO Auto-generated method stub
   * Account out = null; try { PreparedStatement stmt =
   * conn.prepareStatement("SELECT * FROM account WHERE userid = ?"); stmt.setInt(1, userid);
   * stmt.execute();
   * 
   * 
   * } catch (Exception e) { // TODO: handle exception } return null; }
   * 
   */


  @Override
  public double viewBalance(int userid) {
    // TODO Auto-generated method stub
    Account out = null;

    return 0;
  }


  @Override
  public List<Account> getUserAccounts(int userid) {
    List<Account> userAccounts = new ArrayList<Account>();

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = conn.prepareStatement("SELECT * FROM account WHERE user_id = ?");
      stmt.setInt(1, userid);
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        userAccounts.add(new Account(rs.getInt("id"), rs.getInt("user_id"),
            rs.getInt("account_number"), rs.getString("account_type"), rs.getDouble("balance")));
      }
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return userAccounts;
  }


  @Override
  public void updateAccount(User user) {
    // TODO Auto-generated method stub

  }



}
