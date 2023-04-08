package cz.muni.fi.cpstars.bl.interfaces;

import cz.muni.fi.cpstars.dal.entities.Motion;
import cz.muni.fi.cpstars.dal.entities.Star;

import java.util.List;

/**
 * Unified interface to access CP stars motions.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface MotionsBlManager {

	/**
	 * Get all motions for corresponding star.
	 *
	 * @param star star
	 * @return list of motions corresponding to given star
	 */
	List<Motion> getAllMotionsForStar(Star star);

	/**
	 * Get all motion-related values for corresponding star id.
	 *
	 * @param starId star id
	 * @return list of motion-related values corresponding to given star
	 */
	List<Motion> getAllMotionsForStarId(long starId);
}
