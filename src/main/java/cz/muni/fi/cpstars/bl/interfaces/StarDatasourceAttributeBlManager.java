package cz.muni.fi.cpstars.bl.interfaces;

import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.entities.StarDatasourceAttribute;

import java.util.List;

/**
 * Unified interface to access CP star-datasource attributes.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface StarDatasourceAttributeBlManager {

	/**
	 * Get all star-datasource attributes for corresponding star.
	 *
	 * @param star star
	 * @return list of star-datasource attributes corresponding to given star
	 */
	List<StarDatasourceAttribute> getAllAttributesForStar(Star star);

	/**
	 * Get all star-datasource attributes for corresponding star id.
	 *
	 * @param starId star id
	 * @return list of star-datasource attributes corresponding to given star
	 */
	List<StarDatasourceAttribute> getAllAttributesForStarId(long starId);
}
