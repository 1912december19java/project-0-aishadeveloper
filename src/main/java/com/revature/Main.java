package com.revature;

import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.controller.MainController;
import com.revature.repository.BankDao;
import com.revature.repository.BankDaoPostgres;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {

  public static void main(String[] args){

    MainController mainController = new MainController();    
    
    // BankDao bankDao = new BankDaoPostgres();
    
    // System.out.println(bankDao.get("aglasby"));
    // System.out.println(bankDao.getAllUsers());

    mainController.showLoginScreen();
    


    // BankDao bankDao = new BankDaoPostgres();

    // final PrintWriter userFile = new PrintWriter("userAccounts.txt");



  }
}
