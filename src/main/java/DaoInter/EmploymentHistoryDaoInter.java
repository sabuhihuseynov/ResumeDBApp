package DaoInter;

import entity.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryDaoInter {
    public List<EmploymentHistory> getEmploymentHistoryByUserId(int userId);
}
