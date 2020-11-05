package br.com.fundatec.microservicecourse.repository;

import br.com.fundatec.microservicecourse.domain.Cachorro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JDBCRepository {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:testdb";

    //  Database credentials
    private static final String USER = "admin";
    private static final String PASS = "123456";

    private Connection conn = null;
    private Statement stmt = null;

    public void createTable() {
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "admin", "123456");
            //STEP 3: Execute the SQL
            stmt = conn.createStatement();
            String sql = "CREATE TABLE cachorro_jdbc " +
                    "(id INTEGER not NULL, " +
                    " nome VARCHAR(255) not null, " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void save(Cachorro cachorro) {
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO cachorro_jdbc " +
                    "VALUES (" +
                    cachorro.getId() +
                    ", '" +
                    cachorro.getNome() +
                    "', " +
                    cachorro.getIdade() +
                    ")";

            stmt.executeUpdate(sql);

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public List<Cachorro> findAll() {
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT id, nome, age FROM cachorro_jdbc";
            ResultSet rs = stmt.executeQuery(sql);

            List<Cachorro> resultado = new ArrayList<Cachorro>();

            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String nome = rs.getString("nome");

                Cachorro cachorro = new Cachorro((long) id, nome, age);
                resultado.add(cachorro);
            }
            rs.close();

            return resultado;
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }
}
