package book.ch05;

import java.sql.*;

/**
 * Created by Sangjun on 2015. 8. 14..
 */
public class DatabaseRepository implements Repository {
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://52.69.174.164:3306/";
    private final String dbName = "shopdb";
    private final String username = "root";
    private final String password = "1234";

    private Connection conn;

    public DatabaseRepository() throws Exception {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url + dbName, username, password);
    }

    @Override
    public Seller findById(String id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Seller seller = null;

        try {
            String query = "SELECT ID, name, email FROM SELLER WHERE ID = ?";

            stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new SQLException("No Data Found!");
            }
            seller = new Seller(rs.getString(1), rs.getString(2), rs.getString(3));
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seller;
    }

    @Override
    public void add(Seller seller) {
        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO SELLER VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, seller.getId());
            stmt.setString(2, seller.getName());
            stmt.setString(3, seller.getEmail());

            int affectedRows = stmt.executeUpdate();
            if ( affectedRows == 0 ){
                throw new SQLException("Seller adding fail!");
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void remove(Seller seller) {

    }
}
