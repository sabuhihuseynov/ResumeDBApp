package DaoInter;

import entity.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {
    public List<UserSkill> getUserSkillByUserId(int userId);
}
