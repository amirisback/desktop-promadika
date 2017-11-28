/*
 * Muhammad Faisal Amir
 * f.miir117@gmail.com
 * id.amirisback.bandung
 * Copyright 2017
 */
package promadika;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Faisal Amir
 */
public class connection {
    private PreparedStatement preStatement;
    private Statement statement;
    private Connection connect;
    private ResultSet resultSet;
    private String driver = "com.mysql.jdbc.Driver";
    private String Folder_Data = "C:/promadika";
    private String Folder_Foto = "C:/promadika/Data_Foto";
    private String Folder_Excel = "C:/promadika/Data_Excel";
    private String Nama_database = "DataBasePromadika.db";
    private String url = Folder_Data + "/" + Nama_database;
    private String url_db = "jdbc:sqlite:" + url;
    private String Folder_Foto_Petugas = Folder_Foto + "/Foto_Petugas";
    private String Folder_Foto_Calon = Folder_Foto + "/Foto_Calon";
    private String sql_table_data_petugas;
    private String sql_table_data_calon_nikah;
    private String sql_table_data_jadwal_nikah;

    public connection() {
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

    public String getFolder_Data() {
        return Folder_Data;
    }

    public void setFolder_Data(String Folder_Data) {
        this.Folder_Data = Folder_Data;
    }

    public String getFolder_Foto() {
        return Folder_Foto;
    }

    public void setFolder_Foto(String Folder_Foto) {
        this.Folder_Foto = Folder_Foto;
    }

    public String getFolder_Excel() {
        return Folder_Excel;
    }

    public void setFolder_Excel(String Folder_Excel) {
        this.Folder_Excel = Folder_Excel;
    }

    public String getNama_database() {
        return Nama_database;
    }

    public void setNama_database(String Nama_database) {
        this.Nama_database = Nama_database;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFolder_Foto_Petugas() {
        return Folder_Foto_Petugas;
    }

    public void setFolder_Foto_Petugas(String Folder_Foto_Petugas) {
        this.Folder_Foto_Petugas = Folder_Foto_Petugas;
    }

    public String getFolder_Foto_Calon() {
        return Folder_Foto_Calon;
    }

    public void setFolder_Foto_Calon(String Folder_Foto_Calon) {
        this.Folder_Foto_Calon = Folder_Foto_Calon;
    }

    public String getSql_table_data_petugas() {
        return sql_table_data_petugas;
    }

    public void setSql_table_data_petugas(String sql_table_data_petugas) {
        this.sql_table_data_petugas = sql_table_data_petugas;
    }

    public String getSql_table_data_calon_nikah() {
        return sql_table_data_calon_nikah;
    }

    public void setSql_table_data_calon_nikah(String sql_table_data_calon_nikah) {
        this.sql_table_data_calon_nikah = sql_table_data_calon_nikah;
    }

    public String getSql_table_data_jadwal_nikah() {
        return sql_table_data_jadwal_nikah;
    }

    public void setSql_table_data_jadwal_nikah(String sql_table_data_jadwal_nikah) {
        this.sql_table_data_jadwal_nikah = sql_table_data_jadwal_nikah;
    }
    
    public void createAllTable(){
        sql_table_data_petugas = "CREATE TABLE data_petugas (\n"
                + "    id_petugas   VARCHAR (15)  PRIMARY KEY,\n"
                + "    nama_petugas VARCHAR (25),\n"
                + "    tempat_kua   VARCHAR (30),\n"
                + "    foto         VARCHAR (100) \n"
                + ");";
        
        sql_table_data_calon_nikah = "CREATE TABLE data_calon_nikah (\n"
                + "    id_calon      VARCHAR (15)  PRIMARY KEY,\n"
                + "    nik_csuami    VARCHAR (25),\n"
                + "    nama_csuami   VARCHAR (25),\n"
                + "    ttl_csuami    VARCHAR (30),\n"
                + "    alamat_csuami VARCHAR (40),\n"
                + "    foto_csuami   VARCHAR (100),\n"
                + "    nik_cistri    VARCHAR (25),\n"
                + "    nama_cistri   VARCHAR (25),\n"
                + "    ttl_cistri    VARCHAR (30),\n"
                + "    alamat_cistri VARCHAR (40),\n"
                + "    foto_cistri   VARCHAR (100) \n"
                + ");";
        
        sql_table_data_jadwal_nikah = "CREATE TABLE data_jadwal_nikah (\n"
                + "    id_nikah     VARCHAR (10) PRIMARY KEY,\n"
                + "    tgl_nikah    DATE,\n"
                + "    tempat_nikah VARCHAR (50),\n"
                + "    biaya_nikah  INTEGER (20),\n"
                + "    id_calon     VARCHAR (15) REFERENCES data_calon_nikah (id_calon) ON DELETE CASCADE\n"
                + "                                                                     ON UPDATE CASCADE,\n"
                + "    id_petugas   VARCHAR (15) REFERENCES data_petugas (id_petugas) ON DELETE CASCADE\n"
                + "                                                                   ON UPDATE CASCADE\n"
                + ");";
        
        try {
            statement = connect.createStatement();
            //Create Table data_petugas
            statement.execute(sql_table_data_petugas);
            //Create Table data_calon_nikah
            statement.execute(sql_table_data_calon_nikah);
            //Create Table data_jadwal_nikah
            statement.execute(sql_table_data_jadwal_nikah);      
        } catch (SQLException e) {
        }

    }
    
    
    public void ConnectToDB(){
        try{
            File folder = new File(Folder_Data);
            if (!folder.exists()) {
                folder.mkdir();
            }
            connect = DriverManager.getConnection(url_db);
            DatabaseMetaData meta = connect.getMetaData();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
        
    public void dataPromadika() {
        try {
            //create folder baru

            File folder_foto = new File(Folder_Foto);
            if (!folder_foto.exists()) {
                folder_foto.mkdir();
            }

            File folder_excel = new File(Folder_Excel);
            if (!folder_excel.exists()) {
                folder_excel.mkdir();
            }
            
            File foto_petugas = new File(Folder_Foto_Petugas);
            if (!foto_petugas.exists()){
                foto_petugas.mkdir();
            }
            
            File foto_calon = new File(Folder_Foto_Calon);
            if (!foto_calon.exists()){
                foto_calon.mkdir();
            }
 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    

}
