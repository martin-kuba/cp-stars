package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.Identifiers;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for Identifiers Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface IdentifiersRepository extends CrudRepository<Identifiers, Long> {
    Identifiers findById(long id);
}
