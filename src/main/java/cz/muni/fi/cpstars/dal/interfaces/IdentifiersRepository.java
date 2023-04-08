package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.Identifier;
import cz.muni.fi.cpstars.dal.entities.Star;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for Identifier Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface IdentifiersRepository extends CrudRepository<Identifier, Long> {
    Identifier findById(long id);

    List<Identifier> findByStar(Star star);

    @Query(value = "SELECT i " +
            "FROM Identifier i " +
            "WHERE i.star.id=?1")
    List<Identifier> findByStarId(long starId);
}
