package com.revature.model;

public class User {

  private int id;
  private String firstName;
  private String lastName;
  private String userName;
  private String password;


  public User(String userName) {
    super();    
    this.userName = userName;    
  }


  
  public User(int id, String lastName, String firstName, String userName, String password) {
    super();
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
  }


  public int getId() {
    return id;
  }



  public void setId(int id) {
    this.id = id + 1;
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



  public String getUserName() {
    return userName;
  }



  public void setUserName(String userName) {
    this.userName = userName;
  }



  public String getPassword() {
    return password;
  }



  public void setPassword(String password) {
    this.password = password;
  }



  void processLogin() {
    System.out.println("Good day, You are now logged In.  Please choose a transaction to perfom!");
  }

  void logOut() {
    System.out.println("You are now logout.  See you soon!");
  }
  

  @Override
  public String toString() {
    return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
        + userName + ", password=" + password + "]";
  }
  
  
}
