package DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.stuffit.www.data.DataManager;
import entity.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bikramjit
 */
public class UserDAO {

    private DataManager dm; // data object

    private DataManager getDataManager() {
        dm = new DataManager();
        return dm;
    }

    public User getUser(String name, String pass) {
        return null;

    }

    public User getUser(int user_id) {
        String query = "SELECT * FROM user WHERE id = ?";
        User u = new User();
        Connection conn = getDataManager().getConnectionToDB();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, "" + user_id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u.setEmail(rs.getString("email"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM user";

        List<User> userslist = new ArrayList<User>();
        Connection conn = getDataManager().getConnectionToDB(); // get a Connection object
//        PreparedStatement pstmt = null;

        try {
            //Statement stmt = (Statement) conn.createStatement();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                userslist.add(u);
                //;
            }
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userslist;
    }

    public void updateUser(User user) {

        String query = "UPDATE user set username = ?, email = ?, date_of_birth = ? WHERE id = ?";

        Connection conn = getDataManager().getConnectionToDB(); // get a Connection object
//        PreparedStatement pstmt = null;

        try {
            //Statement stmt = (Statement) conn.createStatement();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            // ps.setDate(3, new Date(user.getDateOfBirth().getTime()));
            int yes = ps.executeUpdate();
            if (yes > 0) {
                System.out.println(" User updated with details (username,email) : " + user.getUsername() + "," + user.getEmail());
            }
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(User user) {
        int user_id = user.getId();
        String query = "SELECT * FROM user WHERE id = " + user_id;
        String SQL = "DELETE FROM inventory WHERE ItemCode = ? ";

        Connection conn = getDataManager().getConnectionToDB(); // get a Connection object
        PreparedStatement pstmt = null;

        try {
            //Statement stmt = (Statement) conn.createStatement();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            int yes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteUser(User user) {
        //nt user_id = user.getId();i
        String query = "DELETE FROM user WHERE id = ?";
        boolean done = false;
        Connection conn = getDataManager().getConnectionToDB(); // get a Connection object
        //PreparedStatement pstmt = null;

        try {
            //Statement stmt = (Statement) conn.createStatement();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, user.getId().toString());
            int yes = ps.executeUpdate();

            if (yes > 0) {
                done = true;
            }
            conn.close();

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return done;
    }

}
