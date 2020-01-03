package com.revature.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.revature.model.User;

public class MainControllerTest {
  
  private static MainController mainController;
  
  @Test
  public void newDepositGetsAddedToBalance() {
    
    User tempUser = new User(0, "testUser", "TestLastName", "TestFirstName", "testPassword");
   // double output = mainController.insertDeposit(user, 1200.00, userAccounts);
   // assert.assertEquals(output, 1500.00);
  }
  
  @Test
  public void testNewUserNameTooShort() {
    // String output = mainController.registerUserName();
    try {
      String userName = "Ais";
      mainController.registerUserName();
      
      fail();
    } catch (Exception e) {
      // TODO: handle exception
      assertEquals("Username must be at least 5 characters", e.getMessage());
    }
  }
  
  @Before
  public void setUp() {     
    mainController = new MainController();
  }
  
  @After
  public void tearDown() {     
    mainController = new MainController();
  }

}
