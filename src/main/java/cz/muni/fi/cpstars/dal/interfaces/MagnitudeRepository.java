package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.Magnitude;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for Magnitude Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface MagnitudeRepository extends CrudRepository<Magnitude, Long> {
    Magnitude findById(long id);
}
