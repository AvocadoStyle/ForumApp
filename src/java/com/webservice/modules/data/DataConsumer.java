package com.webservice.modules.data;
import java.sql.*;


/**
 *
 * @author eden
 */
public class DataConsumer {
    private Connection connect;
    public DataConsumer(Connection connect){
        this.connect = connect;
    }
    public Connection getConnection(){
        return this.connect;
    }
}
