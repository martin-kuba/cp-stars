package cz.muni.fi.cpstars.bl.implementation;

import cz.muni.fi.cpstars.bl.interfaces.StarDatasourceAttributeBlManager;
import cz.muni.fi.cpstars.dal.entities.Star;
import cz.muni.fi.cpstars.dal.entities.StarDatasourceAttribute;
import cz.muni.fi.cpstars.dal.interfaces.StarDatasourceAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Business layer logic for Star-Datasource Attributes manager.
 * Provides functionality to access/work with star-datasource attributes.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Service
public class StarDatasourceAttributeBlManagerImpl implements StarDatasourceAttributeBlManager {

	private final StarDatasourceAttributeRepository starDatasourceAttributeRepository;

	@Autowired
	public StarDatasourceAttributeBlManagerImpl(StarDatasourceAttributeRepository starDatasourceAttributeRepository) {
		this.starDatasourceAttributeRepository = starDatasourceAttributeRepository;
	}

	@Override
	public List<StarDatasourceAttribute> getAllAttributesForStar(Star star) {
		return starDatasourceAttributeRepository.findAllByStar(star);
	}

	@Override
	public List<StarDatasourceAttribute> getAllAttributesForStarId(long starId) {
		return starDatasourceAttributeRepository.findAllByStarId(starId);
	}
}
