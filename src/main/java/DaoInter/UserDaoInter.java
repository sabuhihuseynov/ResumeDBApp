package DaoInter;

import entity.User;
import entity.UserSkill;

import java.util.List;

public interface UserDaoInter {
    public List<User> getAll();
    public User getByID(int userId);

    public boolean updateUser(User u);

    public boolean addUser(User u);
    public boolean removeUser(int id);



}
