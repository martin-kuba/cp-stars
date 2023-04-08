package cz.muni.fi.cpstars.bl.interfaces;

import cz.muni.fi.cpstars.dal.entities.Identifier;
import cz.muni.fi.cpstars.dal.entities.Star;

import java.util.List;

/**
 * Unified interface to access CP star's identifiers.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface IdentifiersBlManager {

	/**
	 * Get all identifiers for corresponding star.
	 *
	 * @param star star
	 * @return identifiers corresponding to given star
	 */
	List<Identifier> getIdentifiersForStar(Star star);

	/**
	 * Get all identifiers for corresponding star id.
	 *
	 * @param starId star id
	 * @return list of identifiers corresponding to given star
	 */
	List<Identifier> getIdentifiersForStarId(long starId);
}
