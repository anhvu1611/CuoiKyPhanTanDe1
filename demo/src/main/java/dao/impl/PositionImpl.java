package dao.impl;

import dao.PositionDao;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionImpl implements PositionDao {
    private EntityManager em;

    public PositionImpl() {
        em = Persistence.createEntityManagerFactory("mariadb")
                .createEntityManager();
    }


    @Override
    public List<Position> listPositions(String name, double salary, double salaryTo) {
        return em.createQuery("select p from Position p  where p.salary >= :salary and p.salary <= :salaryTo order by p.name", Position.class)
                .setParameter("salaryTo", salaryTo)
                .setParameter("salary", salary)
                .getResultList();
    }

    @Override
    public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateId) {
        Map<Position, Integer> map = new HashMap<>();
        String sql = "SELECT p.position_id, p.name AS position_name, FLOOR(MAX(DATEDIFF(e.to_date ,e.from_date) / 365)) AS years\n" +
                "FROM experiences e\n" +
                "INNER JOIN positions p ON e.position_id = p.position_id\n" +
                "WHERE e.candidate_id = 'C101'\n" +
                "GROUP BY p.position_id\n";
        List<?> list = em.createNativeQuery(sql).getResultList();
        list.stream()
                .map(o -> (Object[]) o)
                .forEach(arr -> {
                    Position p = new Position();
                    p = em.find(Position.class, arr[0]);
                    map.put(p, (Integer) arr[2]);
                });
        return map;
    }
}
