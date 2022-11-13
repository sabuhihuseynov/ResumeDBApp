package DaoImpl;

import DaoInter.AbstractDAO;
import DaoInter.SkillDaoInter;
import entity.Country;
import entity.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter{
    private Skill getSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Skill(id,name);
    }

    @Override
    public List<Skill> getAllSkills() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect();) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from skill");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Skill skill = getSkill(rs);
                result.add(skill);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
