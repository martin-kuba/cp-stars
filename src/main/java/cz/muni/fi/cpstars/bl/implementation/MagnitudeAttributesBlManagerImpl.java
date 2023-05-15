package cz.muni.fi.cpstars.bl.implementation;


import cz.muni.fi.cpstars.bl.interfaces.MagnitudeAttributesBlManager;
import cz.muni.fi.cpstars.dal.entities.MagnitudeAttribute;
import cz.muni.fi.cpstars.dal.interfaces.MagnitudeAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business layer logic for star-magnitude attributes manager.
 * Provides functionality to access/work with stellar magnitude attributes.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Service
public class MagnitudeAttributesBlManagerImpl implements MagnitudeAttributesBlManager {

	private final MagnitudeAttributeRepository magnitudeAttributeRepository;

	@Autowired
	public MagnitudeAttributesBlManagerImpl(
			MagnitudeAttributeRepository magnitudeAttributeRepository) {
		this.magnitudeAttributeRepository = magnitudeAttributeRepository;
	}

	@Override
	public List<MagnitudeAttribute> getAllAttributesForMagnitudeId(long magnitudeId) {
		return magnitudeAttributeRepository.findAllByMagnitudeId(magnitudeId);
	}

	@Override
	public List<MagnitudeAttribute> getAllAttributesForStarId(long starId) {
		return magnitudeAttributeRepository.findAllByStarId(starId);
	}
}
