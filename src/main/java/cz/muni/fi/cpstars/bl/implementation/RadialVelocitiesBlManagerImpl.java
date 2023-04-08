package cz.muni.fi.cpstars.bl.implementation;

import cz.muni.fi.cpstars.bl.interfaces.RadialVelocitiesBlManager;
import cz.muni.fi.cpstars.dal.entities.RadialVelocity;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.interfaces.RadialVelocityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business layer logic for RadialVelocities manager.
 * Provides functionality to access/work with star's radial velocities.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Service
public class RadialVelocitiesBlManagerImpl implements RadialVelocitiesBlManager {

	private final RadialVelocityRepository radialVelocityRepository;

	@Autowired
	public RadialVelocitiesBlManagerImpl(RadialVelocityRepository radialVelocityRepository) {
		this.radialVelocityRepository = radialVelocityRepository;
	}

	@Override
	public List<RadialVelocity> getAllRadialVelocitiesForStar(Star star) {
		return radialVelocityRepository.findAllByStar(star);
	}

	@Override
	public List<RadialVelocity> getAllRadialVelocitiesForStarId(long starId) {
		return radialVelocityRepository.findAllByStarId(starId);
	}
}
