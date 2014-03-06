package entity;

import java.sql.*;

import sql.Commands;

public class AccountSQL  
{ 
  private static Connection connection = Commands.connect("jdbc:mysql://localhost:3306/entities");
  private static Statement stmt = Commands.createStatement(connection);
  static {
    Commands.createTable(stmt, "account", "uid int", "balance int", "pin varchar(64)");
  }
  
  private long key;
  
  public AccountSQL (int uid, int balance, String pin) {
    key = Commands.insertInto(stmt, "account", "uid, balance, pin", uid, balance, pin);
  }

  public int getUid()
  { 
    return Commands.selectIntCol(stmt, "account", key, "uid");
  }

  public AccountSQL setUid(int uid)
  { 
    Commands.update(stmt, "account", key, "uid", uid);
    return this;
  }

  public int getBalance()
  { 
    return Commands.selectIntCol(stmt, "account", key, "balance");
  }

  public AccountSQL setBalance(int balance)
  { 
    Commands.update(stmt, "account", key, "balance", balance);
    return this;
  }

  public String getPin()
  { 
    return Commands.selectStringCol(stmt, "account", key, "pin");
  }

  public AccountSQL setPin(String pin)
  { 
    Commands.update(stmt, "account", key, "pin", pin);
    return this;
  }
}