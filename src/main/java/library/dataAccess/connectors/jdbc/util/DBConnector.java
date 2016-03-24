package library.dataAccess.connectors.jdbc.util;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnector {
    Connection getConnection() throws SQLException, NamingException;
}
