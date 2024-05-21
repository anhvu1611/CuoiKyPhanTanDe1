package test;

import dao.CandidateDao;
import dao.PositionDao;
import dao.impl.CandidateImpl;
import dao.impl.PositionImpl;
import entity.Candidate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        PositionDao positionDao = new PositionImpl();
        positionDao.listPositions("1", 16000, 20000).forEach(System.out::println);

        CandidateDao candidateDao = new CandidateImpl();
        candidateDao.listCadidatesByCompanies().forEach((k, v) -> System.out.println(k + " " + v));
        candidateDao.listCandidatesWithLongestWorking().forEach((k, v) -> System.out.println(k.getFullName() + "      " + v.getName()));

        //cau d: Thêm mới một ứng viên
//        candidateDao.addCandidate(new Candidate("C123","Nguyen Van A", 2000, "Nam", "email", "0839248239", "Mo ta"));
        //Cau e: Tính số năm làm việc trên một vị trí công việc nào đó khi biết mã số ứng viên
        positionDao.listYearsOfExperienceByPosition("C101").forEach((k, v) -> System.out.println(k.getName() + " " + v));
        //cau f: Liệt kê thông tin ứng viên và chứng chỉ của họ
        candidateDao.listCadidatesAndCertificates().forEach((k, v) -> System.out.println(k.getFullName() + " " + v));
    }
}
