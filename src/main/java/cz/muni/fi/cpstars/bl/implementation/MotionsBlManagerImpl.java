package cz.muni.fi.cpstars.bl.implementation;

import cz.muni.fi.cpstars.bl.interfaces.MotionsBlManager;
import cz.muni.fi.cpstars.dal.entities.Motion;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.interfaces.MotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business layer logic for Motions manager.
 * Provides functionality to access/work with star's motions.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Service
public class MotionsBlManagerImpl implements MotionsBlManager {

	private final MotionRepository motionRepository;

	@Autowired
	public MotionsBlManagerImpl(MotionRepository motionRepository) {
		this.motionRepository = motionRepository;
	}

	@Override
	public List<Motion> getAllMotionsForStar(Star star) {
		return motionRepository.findAllByStar(star);
	}

	@Override
	public List<Motion> getAllMotionsForStarId(long starId) {
		return motionRepository.findAllByStarId(starId);
	}
}
