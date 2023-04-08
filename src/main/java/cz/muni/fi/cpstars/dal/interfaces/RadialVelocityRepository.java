package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.RadialVelocity;
import cz.muni.fi.cpstars.dal.entities.Star;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for RadialVelocity Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface RadialVelocityRepository extends CrudRepository<RadialVelocity, Long>  {

    RadialVelocity findById(long id);

    List<RadialVelocity> findAllByStar(Star star);

    @Query(value = "SELECT r " +
            "FROM RadialVelocity r " +
            "WHERE r.star.id=?1")
    List<RadialVelocity> findAllByStarId(long starId);
}
