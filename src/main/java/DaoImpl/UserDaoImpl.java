package DaoImpl;

import DaoInter.AbstractDAO;
import DaoInter.UserDaoInter;
import entity.Country;
import entity.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {
    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalitystr = rs.getString("nationality");
        String birthplacestr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");

        Country nationality = new Country(nationalityId, nationalitystr, null);
        Country birthplace = new Country(birthplaceId, birthplacestr, null);
        return new User(id, name, surname, email, phone, profileDesc,address,birthdate, nationality, birthplace);
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect();) {
            Statement stmt = c.createStatement();
            stmt.execute("select " +
                    "u.*, " +
                    "n.nationality as nationality, " +
                    "c.name as birthplace " +
                    "from user u " +
                    "left join country n on u.nationality_id = n.id " +
                    "left join country c on u.birthplace_id = c.id ");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getByID(int userId) {
        User result = null;
        try (Connection c = connect();) {
            Statement stmt = c.createStatement();
            stmt.execute("select " +
                    "u.*," +
                    "n.nationality as nationality, " +
                    "c.name as birthplace " +
                    "from user u " +
                    "left join country n on u.nationality_id = n.id " +
                    "left join country c on u.birthplace_id = c.id where id = " + userId);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect();) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,email=?,phone=?,profile_description=?,address=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAddress());
            stmt.setInt(7, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect();) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,email,phone,profile_description,address) values(?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAddress());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int userId) {
        try (Connection c = connect();) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id = " + userId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
