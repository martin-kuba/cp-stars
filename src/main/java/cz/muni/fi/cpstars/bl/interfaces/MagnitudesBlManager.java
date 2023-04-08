package cz.muni.fi.cpstars.bl.interfaces;

import cz.muni.fi.cpstars.dal.entities.Magnitude;
import cz.muni.fi.cpstars.dal.entities.Star;

import java.util.List;

/**
 * Unified interface to access CP stars magnitudes.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface MagnitudesBlManager {

	/**
	 * Get all magnitudes for corresponding star.
	 *
	 * @param star star
	 * @return list of magnitudes corresponding to given star
	 */
	List<Magnitude> getAllMagnitudesForStar(Star star);

	/**
	 * Get all magnitudes for corresponding star id.
	 *
	 * @param starId star id
	 * @return list of magnitudes corresponding to given star
	 */
	List<Magnitude> getAllMagnitudesForStarId(long starId);
}
