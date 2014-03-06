package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Commands {

  public static Connection connect(String string) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      return DriverManager.getConnection("jdbc:mysql://localhost:3306/entities");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static Statement createStatement(Connection conn) {
    try {
      return conn.createStatement();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static void createTable(Statement stmt, String name, String... cols) {
    StringBuilder colString = new StringBuilder();
    
    colString.append("primkey int PRIMARY KEY AUTO_INCREMENT");
    if (cols.length > 0)
      colString.append(", ");
    
    for (int i = 0; i < cols.length; i++) {
      colString.append(cols[i]);
      if (i + 1 < cols.length)
        colString.append(", ");
    }
    
    String query =  "CREATE TABLE IF NOT EXISTS " + name + " ( " + colString + " );";
    System.out.println(query);
    try {
      stmt.execute(query);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    
    query =  "DELETE FROM " + name;
    System.out.println(query);
    
    try {
      stmt.execute(query);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }
  
  public static long insertInto(Statement stmt, String table, String colString, Object... vals) {
    StringBuilder valString = new StringBuilder();
    for (int i = 0; i < vals.length; i++) {
      valString.append(sqlVal(vals[i]));
      if (i + 1 < vals.length)
        valString.append(", ");
    }

    String query = "INSERT INTO " + table + "(" + colString + ")" + " VALUES ( " + valString + " )";
    System.out.println(query);
    
    try {
      stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
      ResultSet rs = stmt.getGeneratedKeys();
      rs.next();
      return rs.getLong(1);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static ResultSet selectKey(Statement stmt, String table, long key) {
    String query = "SELECT * FROM " + table + " WHERE  primkey=" + key;
    System.out.println(query);
    try {
      ResultSet rs = stmt.executeQuery(query);
      rs.next();
      return rs;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static int selectIntCol(Statement stmt, String table, long key, String colLabel) {
    try {
      return selectKey(stmt, table, key).getInt(colLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static String selectStringCol(Statement stmt, String table, long key, String colLabel) {
    try {
      return selectKey(stmt, table, key).getString(colLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static int update(Statement stmt, String table, long key, String colLabel, Object val) {
    String query = "UPDATE " + table + " SET " + colLabel + "=" + sqlVal(val) + " WHERE primkey=" + key;
    System.out.println(query);
    try {
      return stmt.executeUpdate(query);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private static String sqlVal(Object val) {
    if (val instanceof Integer || val instanceof Long)
      return val.toString();
    else
      return "'" + val + "'";
  }
}
