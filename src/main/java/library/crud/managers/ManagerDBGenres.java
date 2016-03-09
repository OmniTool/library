package library.crud.managers;

import library.objects.Genre;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class ManagerDBGenres {

    public void add(Genre genre) {

        Connection conn = null;
        Statement statement = null;

        String insertSQL = "INSERT INTO genres (title, description) " +
                "VALUES ('" + genre.getTitle() + "', '" + genre.getDescription() + "')";

        try {
            InitialContext initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/library_test");
            conn = ds.getConnection();
            statement = conn.createStatement();
            statement.executeUpdate(insertSQL);
            System.out.println("Genre added!");
            System.out.println();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void selectSearchTitle(String name) {
        System.out.println("===selectSearchTitle: " + name);

        Connection conn = null;
        Statement statement = null;

        String selectSQL = "SELECT * FROM genres WHERE title LIKE '%" + name + "%'";

        try {
            InitialContext initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/library_test");
            conn = ds.getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(selectSQL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");

                System.out.println("id : " + id);
                System.out.print("title : " + title);
                System.out.println(" description : " + description);
            }
            System.out.println("Lines with title LIKE selected!");
            System.out.println();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void selectSpecialId(Genre genre) {
        System.out.println("===selectSpecialId: " + genre.getId());

        Connection conn = null;
        Statement statement = null;

        String selectSQL = "SELECT * FROM genres WHERE id = " + genre.getId();

        try {
            InitialContext initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/library_test");
            conn = ds.getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(selectSQL);
            if(rs.next()) {
                genre.setTitle(rs.getString("title"));
                genre.setDescription(rs.getString("description"));
            }
            System.out.println("Result: id = " + genre.getId() + " title = " + genre.getTitle() + " descr = " + genre.getDescription());
            System.out.println();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void selectAll() {
        System.out.println("===selectAll");

        Connection conn = null;
        Statement statement = null;

        String selectSQL = "SELECT * FROM genres";

        try {
            InitialContext initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/library_test");
            conn = ds.getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(selectSQL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");

                System.out.println("id : " + id);
                System.out.print("title : " + title);
                System.out.println(" description : " + description);
            }
            System.out.println("All lines selected!");
            System.out.println();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void update(Genre genreEdited) {
        System.out.println("===update: " + genreEdited);

        Connection conn = null;
        Statement statement = null;

        String updateSQL = "UPDATE genres SET title = '" + genreEdited.getTitle() + "', description = '" + genreEdited.getDescription() +
                "' WHERE id = " + genreEdited.getId();

        try {
            InitialContext initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/library_test");
            conn = ds.getConnection();
            statement = conn.createStatement();
            statement.executeUpdate(updateSQL);
            System.out.println("Genre updated!");
            System.out.println();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void delete(Genre genre) {
        System.out.println("===delete: " + genre.getId());

        Connection conn = null;
        Statement statement = null;

        String deleteSQL = "DELETE FROM genres WHERE id = " + genre.getId();

        try {
            InitialContext initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/library_test");
            conn = ds.getConnection();
            statement = conn.createStatement();
            statement.executeUpdate(deleteSQL);
            System.out.println("Genre deleted!");
            System.out.println();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
