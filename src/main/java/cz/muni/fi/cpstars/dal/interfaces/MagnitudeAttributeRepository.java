package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.MagnitudeAttribute;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for Magnitude Attribute Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface MagnitudeAttributeRepository extends CrudRepository<MagnitudeAttribute, Long> {
    MagnitudeAttribute findById(long id);
}
