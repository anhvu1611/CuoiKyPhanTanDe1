package dao;

import entity.Position;

import java.util.List;
import java.util.Map;

public interface PositionDao {
    List<Position> listPositions(String name, double salary, double salaryTo);
    Map<Position, Integer> listYearsOfExperienceByPosition(String candidateId);
}
