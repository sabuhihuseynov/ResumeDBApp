package main;


import DaoImpl.EmploymentHistoryDaoImpl;
import DaoImpl.UserSkillDaoImpl;
import DaoInter.*;
import com.mysql.cj.x.protobuf.MysqlxExpect;
import entity.EmploymentHistory;

public class Main {

    public static void main(String[] args) throws Exception{
        UserDaoInter userDaoInter = Context.instanceUserDao();
        System.out.println(userDaoInter.getAll());

        UserSkillDaoInter userSkillDaoInter = Context.instanceUserSkillDao();
        System.out.println(userSkillDaoInter.getUserSkillByUserId(1));

        EmploymentHistoryDaoInter employmentHistory = Context.instanceEmploymentHistoryDao();
        System.out.println(employmentHistory.getEmploymentHistoryByUserId(1));

        CountryDaoInter countryDaoInter = Context.instanceCountryDao();
        System.out.println(countryDaoInter.getAllCountry());

        SkillDaoInter skillDaoInter = Context.instanceSkillDao();
        System.out.println(skillDaoInter.getAllSkills());
    }
}