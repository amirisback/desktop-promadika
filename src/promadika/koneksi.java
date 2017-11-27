/*
 * Muhammad Faisal Amir
 * id.amirisback.bandung
 * Copyright 2017
 */
package promadika;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
/**
 *
 * @author Faisal Amir
 */
public class koneksi {
    private PreparedStatement preStatement;
    private Statement statement;
    private Connection connect;
    private ResultSet resultSet;
    private String driver = "com.mysql.jdbc.Driver";
    private String url_db = "jdbc:mysql://localhost:3306/promadika";
    private String user_db = "root";
    private String pw_db = "";

    public koneksi() {
    }

    public PreparedStatement getPreStatement() {
        return preStatement;
    }

    public void setPreStatement(PreparedStatement preStatement) {
        this.preStatement = preStatement;
    }
    
    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl_db() {
        return url_db;
    }

    public void setUrl_db(String url_db) {
        this.url_db = url_db;
    }

    public String getUser_db() {
        return user_db;
    }

    public void setUser_db(String user_db) {
        this.user_db = user_db;
    }

    public String getPw_db() {
        return pw_db;
    }

    public void setPw_db(String pw_db) {
        this.pw_db = pw_db;
    }

    public void ConnectToDB(){
        try{
            Class.forName(driver);
            connect = DriverManager.getConnection(url_db, user_db, pw_db);
        }catch (Exception e){
            System.out.println("Koneksi Gagal Hidupkan XAMPP");
        }
    }
    
    
}
