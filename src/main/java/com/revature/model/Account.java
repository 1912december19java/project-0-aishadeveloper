package com.revature.model;

public class Account {
	
	private int id;
	private int userId;
	private int accountNumber;
	private String accountType;	
	private double accountBalance;
	
	
  public Account(int id, int userId, int accountNumber, String accountType, double accountBalance) {
    super();
    this.id = id;
    this.userId = userId;
    this.accountNumber = accountNumber;
    this.accountType = accountType;
    this.accountBalance = accountBalance;
  }
  
  


  public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }


  public int getUserId() {
    return userId;
  }


  public void setUserId(int userId) {
    this.userId = userId;
  }


  public int getAccountNumber() {
    return accountNumber;
  }


  public void setAccountNumber(int accountNumber) {
    this.accountNumber = accountNumber;
  }


  public String getAccountType() {
    return accountType;
  }


  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }


  public double getAccountBalance() {
    return accountBalance;
  }


  public void setAccountBalance(double accountBalance) {
    this.accountBalance = accountBalance;
  }


  @Override
  public String toString() {
    return "Account [id=" + id + ", userId=" + userId + ", accountNumber=" + accountNumber
        + ", accountType=" + accountType + ", accountBalance=" + accountBalance + "]";
  }
  
  
  
	
	
	
	

}
