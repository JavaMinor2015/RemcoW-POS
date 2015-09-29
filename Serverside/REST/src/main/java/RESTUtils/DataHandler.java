package RESTUtils;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Remco on 29-9-2015.
 */
public class DataHandler {
    private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
    private String userid = "admin";
    private String password = "admin";
    private Connection conn;

    public void getDBConnection() throws SQLException{
        OracleDataSource ds;
        ds = new OracleDataSource();
        ds.setURL(jdbcUrl);
        conn = ds.getConnection(userid, password);
    }

    public void closeDBConnection() throws SQLException{
        conn.close();
    }

    public ResultSet executeStatement(String query){
        try {
            getDBConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
