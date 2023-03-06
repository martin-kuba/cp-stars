package cz.muni.fi.cpstars.dal.implementation.star;

import cz.muni.fi.cpstars.dal.classes.StarBasicInfo;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.interfaces.star.FragmentStarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Fragment interface implementation for Star Repository that adds custom queries.
 *
 * @param <T>  Entity class
 * @param <ID> ID class
 *
 * @author Ľuboslav Halama <lubo.halam@gmail.com>
 */
public class FragmentStarRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements FragmentStarRepository<T, ID> {

    private EntityManager entityManager;

    public FragmentStarRepositoryImpl(JpaEntityInformation<T, ?>
                                          entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<StarBasicInfo> findAllStarsBasicInfo() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StarBasicInfo> query = cb.createQuery(StarBasicInfo.class);
        Root<Star> root = query.from(Star.class);
        query.select(cb.construct(StarBasicInfo.class,
                        root.get("id"),
                        root.get("id_2009_A_AND_A_498_961_R"),
                        root.get("consideredCategoryAffiliationProbabilityFlag"),
                        root.get("binarySystemComponent"),
                        root.get("icrsRightAscension"),
                        root.get("icrsDeclination"),
                        root.get("galacticLongitude"),
                        root.get("galacticLatitude")
                ));

        return entityManager.createQuery(query).getResultList();
    }
}
