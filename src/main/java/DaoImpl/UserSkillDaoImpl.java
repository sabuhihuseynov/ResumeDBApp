package DaoImpl;

import DaoInter.AbstractDAO;
import DaoInter.UserSkillDaoInter;
import entity.Skill;
import entity.User;
import entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception{
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        return new UserSkill(null,new User(userId),new Skill(skillId,skillName),power);
    }
    @Override
    public List<UserSkill> getUserSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect();) {
            PreparedStatement stmt = c.prepareStatement("SELECT " +
                    "u.*, " +
                    " us.skill_id, " +
                    "s.NAME AS skill_name, " +
                    "us.power " +
                    "FROM " +
                    "user_skill us " +
                    "LEFT JOIN USER u ON us.user_id = u.id " +
                    "LEFT JOIN skill s ON us.skill_id = s.id " +
                    " where us.user_id = ?");
            stmt.setInt(1,userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
