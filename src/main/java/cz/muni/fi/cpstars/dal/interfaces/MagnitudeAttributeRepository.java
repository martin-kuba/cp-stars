package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.MagnitudeAttribute;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for Magnitude Attribute Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface MagnitudeAttributeRepository extends CrudRepository<MagnitudeAttribute, Long> {
    MagnitudeAttribute findById(long id);

    @Query(value = "SELECT a " +
            "FROM MagnitudeAttribute a " +
            "WHERE a.magnitude.id=?1")
    List<MagnitudeAttribute> findAllByMagnitudeId(long magnitudeId);

    @Query(value = "SELECT a " +
            "FROM MagnitudeAttribute a " +
            "WHERE a.magnitude.star.id = ?1")
    List<MagnitudeAttribute> findAllByStarId(long starId);
}
