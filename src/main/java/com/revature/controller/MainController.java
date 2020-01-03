package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.revature.exception.BalanceTooLowException;
import com.revature.exception.InvalidUsernameException;
// import com.revature.validatePassword;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.repository.BankDao;
import com.revature.repository.BankDaoPostgres;

public class MainController {
  Scanner in = new Scanner(System.in);
  String accountChoice;
  String userName;
  String password;
  String passwordConfirm;

  BankDao bankDao = new BankDaoPostgres();

  List<User> allUsers = new ArrayList<User>();
  
  MainController mainController;


  public void showLoginScreen() {
    System.out.println("Do you have an account?  Type Y for Yes or N for No");
    accountChoice = in.nextLine().toUpperCase();
    if (accountChoice.equals("Y")) {
      processLogin();
    } else if (accountChoice.equals("N")) {
      registerUserName();
    } else {
      System.out.println(
          "Please Type Y if you have an account or type N is you need to register for a new account");
      accountChoice = in.nextLine();
    }
  }

  void processLogin() {
    System.out.println("Please enter your username");
    userName = in.nextLine();
    try {
      validateUsername(userName);
    } catch (InvalidUsernameException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  void validateUsername(String username) throws InvalidUsernameException {
    allUsers = bankDao.getAllUsers();

    User tempUser = new User("invalid");
    tempUser.setUserName("invalid");

    for (User user : allUsers) {
      if (user.getUserName().equals(username)) {
        tempUser = user;
        break;
      }
    }

    if (tempUser.getUserName().equals(username)) {
      validatePassword(tempUser);
    } else {
      throw new InvalidUsernameException();

    }
  }


  public void showAccountSummary(Account account) {

  }

  public void showAccountBalance(Account account) {

  }

  public void showAccounts(User user) {

  }

  void validatePassword(User user) {
    System.out.println("Please enter your password");
    password = in.nextLine();

    if (user.getPassword().equals(password)) {
      System.out.println("Welcome " + user.getFirstName().toUpperCase() + " "
          + user.getLastName().toUpperCase() + ":\n ");

      loadBankMenu(user);
    } else {
      System.out.println("The password is incorrect, Please try again!");
      validatePassword(user);
    }
  }


  void validateNewPassword(String userName, String p1, String p2) {
    if (p1.equals(p2)) {
      // userFile.println(username);
      System.out.println("valid");
    }
  }


  void loadBankMenu(User user) {
    // bankDao.showBankMenu();
    List<Account> userAccounts = new ArrayList<Account>();
    userAccounts = bankDao.getUserAccounts(user.getId());

    String menuChoice;
    System.out.println("-------------------------------");
    System.out.println("Choose V to VIEW your Balance");
    System.out.println("Choose D to DEPOSIT Funds");
    System.out.println("Choose W to WITHDRAW Funds");
    // System.out.println("Choose C to CREATE a new account");
    System.out.println("Choose L to LOGOUT");

    menuChoice = in.nextLine().toUpperCase();

    // if the user has an account, show the accounts and balance on the screen and give the user the
    // option to
    // deposit or withdraw money

    switch (menuChoice) {
      // case "A":
      // show bank accounts
      // System.out.println("Here is a list of your bank accounts");
      // break;

     // case "C":
        // Create a new account
       // System.out.println("What type of account would you like to create?");
       // break;

      case "L":
        // clear screen and log out.
        System.out.println("Thanks for visiting, goodbye!");
        System.out.close();

        break;

      case "D":
        // Deposit Funds
        double newDeposit;
        System.out.println("How much are you depositing today?");
        newDeposit = in.nextDouble();
        insertDeposit(user, newDeposit, userAccounts);
        loadBankMenu(user);
        // break;

      case "W":
        // Withraw Funds
        try {
          processWithdrawal(user, userAccounts);
        } catch (BalanceTooLowException e) {
          // TODO Auto-generated catch block
          // e.printStackTrace();
          
          System.out.println("Your balance is too low to withdraw that amount, please try again");
        }
        loadBankMenu(user);
       // break;

      case "V":
        // View Accounts
        System.out.println("Account List");
        // List<Account> userAccounts = new ArrayList<Account>();
        // userAccounts = bankDao.getUserAccounts(user.getId());
        showAccountBalance(userAccounts, user);
        loadBankMenu(user);
        // break;

      default:
        // System.out.println("You did not make a valid choice");
        loadBankMenu(user);
        // break;
    }
  }
    

  void registerUserName() {

    String userName;

    boolean isUserNameAvailable;

    do {
      System.out.println(
          "Please register to continue by typing in a username that is at least 5 characters");
      userName = in.nextLine().toLowerCase();
    } while (userName.length() < 5 || userName.length() > 255);

    isUserNameAvailable = verifyUsernameAvailable(userName);

    if (isUserNameAvailable) {
      setPassword(userName);
    } else {
      System.out.println("pick another username");
      registerUserName();
    }
  }


  boolean verifyUsernameAvailable(String userName) {
    allUsers = bankDao.getAllUsers();
    for (User user : allUsers) {
      if (user.getUserName().equals(userName)) {

        return false;
      }
    }
    return true;
  }

  void setPassword(String username) {
    String password;
    String passwordConfirm;

    System.out.println("Please enter a password for your account");
    password = in.nextLine();
    System.out.println("Please re-enter your password to confirm");
    passwordConfirm = in.nextLine();

    if (password.contentEquals(passwordConfirm)) {
      registerUser(username, password);
    } else {
      System.out.println("The passwords did not match");
      setPassword(username);
    }
  }

  void registerUser(String username, String password) {
    // User newUser;
    int id = 0;
    String lastName;
    String firstName;
    System.out.println("Please enter your Last Name");
    lastName = in.nextLine();
    System.out.println("Please enter your First Name");
    firstName = in.nextLine();

    User newUser = new User(id, lastName, firstName, username, password);

    bankDao.saveUser(newUser);
    createBankAccount(username);
    processLogin();
  }
  
  void createBankAccount(String username) {
    
  }
  
  
  void getUserId(String username) {
    allUsers = bankDao.getAllUsers();

    User tempUser = new User("invalid");
    tempUser.setUserName("invalid");

    for (User user : allUsers) {
      if (user.getUserName().equals(username)) {
        tempUser = user;
        break;
      }
    }
  }


  void showAccountBalance(List<Account> userAccounts, User user) {
    double balance = 0;
    for (Account account : userAccounts) {
      balance += account.getAccountBalance();
      System.out.println(account.getAccountType() + ":  " + account.getAccountBalance());

    }
    System.out.println("Total balance = \t" + balance);

   // loadBankMenu(user);
  }


  double getBalance(List<Account> userAccounts, User user) {
    double balance = 0;
    for (Account account : userAccounts) {
      balance += account.getAccountBalance();
      System.out.println(account.getAccountType() + ":  " + account.getAccountBalance());

    }
    System.out.println("Total balance = \t" + balance);

    return balance;
  }



  void insertDeposit(User user, double deposit, List<Account> userAccounts) {
    // showAccountBalance(userAccounts, user);

    double newBalance = 0;
    for (Account account : userAccounts) {

      newBalance = account.getAccountBalance() + deposit;
      System.out.println("Your old " + account.getAccountType() + " account balance was:  "
          + account.getAccountBalance());

    }
    System.out.println("Your new balance is = \t" + newBalance);

    bankDao.updateBalance(user, newBalance);

  //  loadBankMenu(user);

  }

  void processWithdrawal(User user, List<Account> userAccounts) throws BalanceTooLowException {
    double currentBalance = getBalance(userAccounts, user);
    double newBalance = 0;

    System.out.println("Your current balance is: " + currentBalance);
    double withdrawal;
    System.out.println("How much would you like to withdraw?");
    withdrawal = in.nextDouble();

    if ((currentBalance - withdrawal) >= 0) {
      newBalance = currentBalance - withdrawal;
      bankDao.updateBalance(user, newBalance);  
      
      System.out.println("Your new balance is :" + newBalance);
    }
  }



}
