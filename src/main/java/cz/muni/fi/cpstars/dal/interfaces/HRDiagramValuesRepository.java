package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.entities.HRDiagramValues;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for HRDiagramValues Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface HRDiagramValuesRepository extends CrudRepository<HRDiagramValues, Long> {
    HRDiagramValues findById(long id);
}
