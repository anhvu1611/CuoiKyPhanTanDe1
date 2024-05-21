package dao.impl;

import dao.CandidateDao;
import entity.Candidate;
import entity.Certificate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.*;

public class CandidateImpl implements CandidateDao {
    private EntityManager em;

    public CandidateImpl() {
        em = Persistence.createEntityManagerFactory("mariadb")
                .createEntityManager();
    }
    @Override
    public Map<Candidate, Long> listCadidatesByCompanies() {
        Map<Candidate, Long> map = new HashMap<>();
        List<?> list = em.createQuery("select c.id, count(*) from Candidate c inner join Certificate ce on c.id = ce.candidate.id group by c.id")
                .getResultList();
        list.stream().forEach(o -> {
            Object[] arr = (Object[]) o;
            Candidate c = em.find(Candidate.class, arr[0]);
            map.put(c, (Long) arr[1]);
        });
        return map;
    }

    @Override
    public Map<Candidate, Position> listCandidatesWithLongestWorking() {
        Map<Candidate, Position> map = new HashMap<>();
        String sql = "SELECT c.candidate_id, c.full_name, p.position_id, p.name AS position_name, e.from_date, e.to_date, DATEDIFF(e.to_date, e.from_date) AS tongsongay\n" +
                "FROM candidates c\n" +
                "INNER JOIN experiences e ON c.candidate_id = e.candidate_id\n" +
                "INNER JOIN positions p ON e.position_id = p.position_id\n" +
                "INNER JOIN (\n" +
                "    SELECT position_id, MAX(DATEDIFF(to_date, from_date)) AS max_tongsongay\n" +
                "    FROM experiences\n" +
                "    GROUP BY position_id\n" +
                ") max_exp ON e.position_id = max_exp.position_id AND DATEDIFF(e.to_date, e.from_date) = max_exp.max_tongsongay;";
        List<?> list = em.createNativeQuery(sql)
                .getResultList();
        list.stream().forEach(o -> {
            Object[] arr = (Object[]) o;
            Candidate c = new Candidate();
            c = em.find(Candidate.class, arr[0]);
            Position p = new Position();
            p = em.find(Position.class, arr[2]);
            map.put(c, p);
        });

        return map;
    }

    @Override
    public boolean addCandidate(Candidate candidate) {
        if(candidate.getId().matches("C\\d{3,}")) {
            try {
                em.getTransaction().begin();
                em.persist(candidate);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                em.close();
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
        Map<Candidate, Set<Certificate>> map = new HashMap<>();
        String sql = "SELECT * FROM certificates ce JOIN candidates c ON ce.candidate_id = c.candidate_id";
        List<?> list = em.createNativeQuery(sql)
                .getResultList();
        list.stream().forEach(o -> {
            Object[] arr = (Object[]) o;
            Candidate c = new Candidate();
            c = em.find(Candidate.class, arr[5]);
            Certificate ce = new Certificate();
            ce = em.find(Certificate.class, arr[0]);
            if(map.containsKey(c)) {
                map.get(c).add(ce);
            } else {
                Set<Certificate> set = new HashSet<>();
                set.add(ce);
                map.put(c, set);
            }
        });
        return map;
    }
}
