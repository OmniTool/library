package library.dataAccess.jdbc.connectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectorPool implements DBConnector {
    private static String CONTEXT = "java:/comp/env";
    private static String DATA_SOURCE = "jdbc/library_test";

    @Override
    public Connection getConnection() throws SQLException, NamingException {
        InitialContext initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup(CONTEXT);
        DataSource ds = (DataSource)envContext.lookup(DATA_SOURCE);
        return ds.getConnection();
    }
}
