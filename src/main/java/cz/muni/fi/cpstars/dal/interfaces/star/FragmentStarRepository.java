package cz.muni.fi.cpstars.dal.interfaces.star;

import cz.muni.fi.cpstars.dal.classes.StarBasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Fragment interface for Star Repository that adds custom queries.
 *
 * @param <T>  Entity class
 * @param <ID> ID class
 *
 * @author Ľuboslav Halama <lubo.halam@gmail.com>
 */
@NoRepositoryBean
public interface FragmentStarRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    List<StarBasicInfo> findAllStarsBasicInfo();
}
