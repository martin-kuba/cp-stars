package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.RadialVelocity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for RadialVelocity Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface RadialVelocityRepository extends CrudRepository<RadialVelocity, Long>  {

    RadialVelocity findById(long id);
}
