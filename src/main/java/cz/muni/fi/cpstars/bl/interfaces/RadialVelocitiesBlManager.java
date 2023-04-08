package cz.muni.fi.cpstars.bl.interfaces;

import cz.muni.fi.cpstars.dal.entities.RadialVelocity;
import cz.muni.fi.cpstars.dal.entities.Star;

import java.util.List;

/**
 * Unified interface to access CP stars radial velocities.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface RadialVelocitiesBlManager {

	/**
	 * Get all radial velocities for corresponding star.
	 *
	 * @param star star
	 * @return list of radial velocities corresponding to given star
	 */
	List<RadialVelocity> getAllRadialVelocitiesForStar(Star star);

	/**
	 * Get all radial velocities for corresponding star id.
	 *
	 * @param starId star id
	 * @return list of radial velocities corresponding to given star
	 */
	List<RadialVelocity> getAllRadialVelocitiesForStarId(long starId);
}
