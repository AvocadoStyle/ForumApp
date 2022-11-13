package com.webservice.modules.data;
import java.sql.*;


/**
 *
 * @author eden
 */
public class DataConsumer {
    private Connection connect;
    protected final int MAX_ROW_DEL = 15;
    public DataConsumer(Connection connect){
        this.connect = connect;
    }
    public Connection getConnection(){
        return this.connect;
    }
}
