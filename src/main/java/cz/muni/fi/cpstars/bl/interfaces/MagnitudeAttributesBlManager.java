package cz.muni.fi.cpstars.bl.interfaces;


import cz.muni.fi.cpstars.dal.entities.MagnitudeAttribute;

import java.util.List;

/**
 * Unified interface to access CP star-magnitude attributes.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
public interface MagnitudeAttributesBlManager {

	/**
	 * Get all (star) magnitude attributes for corresponding magnitude id.
	 *
	 * @param magnitudeId magnitude id
	 * @return list of star-magnitude attributes corresponding to given magnitude
	 */
	List<MagnitudeAttribute> getAllAttributesForMagnitudeId(long magnitudeId);

	/**
	 * Get all (star) magnitude attributes for corresponding star id.
	 *
	 * @param starId star id
	 * @return list of star-magnitude attributes corresponding to given star
	 */
	List<MagnitudeAttribute> getAllAttributesForStarId(long starId);
}
