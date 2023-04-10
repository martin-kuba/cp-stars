package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.Magnitude;
import cz.muni.fi.cpstars.dal.entities.Star;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for Magnitude Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface MagnitudeRepository extends CrudRepository<Magnitude, Long> {
    Magnitude findById(long id);

    List<Magnitude> findAllByStar(Star star);

    @Query(value = "SELECT m " +
            "FROM Magnitude m " +
            "WHERE m.star.id=?1")
    List<Magnitude> findAllByStarId(long starId);

    @Query(value = "SELECT DISTINCT m.name " +
            "FROM Magnitude m")
    List<String> findAllMagnitudeNames();
}
