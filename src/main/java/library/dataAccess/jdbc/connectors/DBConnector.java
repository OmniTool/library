package library.dataAccess.jdbc.connectors;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnector {
    Connection getConnection() throws SQLException, NamingException;
}
