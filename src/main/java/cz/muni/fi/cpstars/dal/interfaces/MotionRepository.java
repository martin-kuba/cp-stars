package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.Motion;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for Motion Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface MotionRepository extends CrudRepository<Motion, Long> {
    Motion findById(long id);
}
