package com.revature.repository;

import java.util.List;
import com.revature.model.Account;
import com.revature.model.User;

public interface BankDao {
  
  User get(String userName);
  
  // Account getUserAccounts();
  
  List<Account> getAllAccounts();
  
  List<User> getAllUsers();
  
  List<Account> getUserAccounts(int userid);
  
  void updateAccount(User user);
  
  void saveUser(User user);
  
  void saveAccount(Account account);
  
  void getUser(User user);
  
  void updateBalance(User user, double money);
  
  void checkBalance();
  
  void withdrawFunds(double money);
  
  void login();
  
  void logout(); 
  
  double viewBalance(int userid);
  
  void registerAccount();
  
  // void showBankMenu();
  
  // void viewAllTransactions();
  
  // viewAccounts();
  
  // transferFunds();

}
