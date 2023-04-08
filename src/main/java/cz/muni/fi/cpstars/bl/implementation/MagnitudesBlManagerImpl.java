package cz.muni.fi.cpstars.bl.implementation;

import cz.muni.fi.cpstars.bl.interfaces.MagnitudesBlManager;
import cz.muni.fi.cpstars.dal.entities.Magnitude;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.interfaces.MagnitudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business layer logic for Magnitudes manager.
 * Provides functionality to access/work with star's magnitudes.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Service
public class MagnitudesBlManagerImpl implements MagnitudesBlManager {

	private final MagnitudeRepository magnitudeRepository;

	@Autowired
	public MagnitudesBlManagerImpl(MagnitudeRepository magnitudeRepository) {
		this.magnitudeRepository = magnitudeRepository;
	}

	@Override
	public List<Magnitude> getAllMagnitudesForStar(Star star) {
		return magnitudeRepository.findAllByStar(star);
	}

	@Override
	public List<Magnitude> getAllMagnitudesForStarId(long starId) {
		return magnitudeRepository.findAllByStarId(starId);
	}
}
