/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connect;
import java.sql.*;
/**
 *
 * @author Dhruv
 */
public class ConnectManager {
    public Connection connect(String schema) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");  
        String URLDB = "jdbc:mysql://localhost:3306/"+schema;
        Connection con = DriverManager.getConnection(URLDB,"root","root");
        return con;
    }
}
