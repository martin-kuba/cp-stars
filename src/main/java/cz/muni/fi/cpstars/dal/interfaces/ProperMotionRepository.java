package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.ProperMotion;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for ProperMotion Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface ProperMotionRepository extends CrudRepository<ProperMotion, Long> {
    ProperMotion findById(long id);
}
