package cz.muni.fi.cpstars.dal.interfaces.star;

import cz.muni.fi.cpstars.dal.entities.Star;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for Star Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface StarRepository extends CrudRepository<Star, Long>, FragmentStarRepository<Star, Long> {
    Star findById(long id);
}
