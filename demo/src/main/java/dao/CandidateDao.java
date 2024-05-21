package dao;

import entity.Candidate;
import entity.Certificate;
import entity.Position;

import java.util.Map;
import java.util.Set;

public interface CandidateDao {
    Map<Candidate, Long> listCadidatesByCompanies();
    Map<Candidate, Position> listCandidatesWithLongestWorking();
    boolean addCandidate(Candidate candidate);
    Map<Candidate, Set<Certificate>> listCadidatesAndCertificates();
}
