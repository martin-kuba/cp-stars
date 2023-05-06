package cz.muni.fi.cpstars.dal.interfaces;

import cz.muni.fi.cpstars.dal.implementation.classes.StarBasicInfo;
import cz.muni.fi.cpstars.dal.entities.Star;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for Star Entity.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
//public interface StarRepository extends CrudRepository<Star, Long>, StarRepositoryCustom<Star, Long> {
public interface StarRepository extends CrudRepository<Star, Long> {
//public interface StarRepository extends CrudRepository<Star, Long>, CustomStarRepository {

    String STAR_BASIC_INFO_SELECT = "s.id, " +
            "s.renson, " +
            "s.consideredCategoryAffiliationProbabilityFlag, " +
            "s.binarySystemComponent, " +
            "s.icrsDeclination, " +
            "s.icrsRightAscension, " +
            "s.galacticLatitude, " +
            "s.galacticLongitude";

    Star findById(long id);

    Star findByRenson(String rensonId);

    @Query(value = "" +
            "SELECT new cz.muni.fi.cpstars.dal.implementation.classes.StarBasicInfo(" + STAR_BASIC_INFO_SELECT + ") " +
            "FROM Star s")
    List<StarBasicInfo> findAllStarsBasicInfo();
}
