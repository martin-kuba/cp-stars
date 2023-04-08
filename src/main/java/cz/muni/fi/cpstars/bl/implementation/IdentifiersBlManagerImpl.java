package cz.muni.fi.cpstars.bl.implementation;

import cz.muni.fi.cpstars.bl.interfaces.IdentifiersBlManager;
import cz.muni.fi.cpstars.dal.entities.Identifier;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.interfaces.IdentifiersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business layer logic for Identifier manager.
 * Provides functionality to access/work with star's identifiers.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Service
public class IdentifiersBlManagerImpl implements IdentifiersBlManager {

	private final IdentifiersRepository identifiersRepository;

	@Autowired
	public IdentifiersBlManagerImpl(IdentifiersRepository identifiersRepository) {
		this.identifiersRepository = identifiersRepository;
	}

	@Override
	public List<Identifier> getIdentifiersForStar(Star star) {
		return identifiersRepository.findByStar(star);
	}

	@Override
	public List<Identifier> getIdentifiersForStarId(long starId) {
		return identifiersRepository.findByStarId(starId);
	}
}
