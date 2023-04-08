package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.Motion;
import cz.muni.fi.cpstars.dal.entities.Star;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for Motion Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface MotionRepository extends CrudRepository<Motion, Long> {
    Motion findById(long id);

    List<Motion> findAllByStar(Star star);

    @Query(value = "SELECT m " +
            "FROM Motion m " +
            "WHERE m.star.id=?1")
    List<Motion> findAllByStarId(long starId);
}
