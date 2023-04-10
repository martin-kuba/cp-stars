package cz.muni.fi.cpstars.dal.classes;

import astrosearcher.classes.ResponseData;
import cz.muni.fi.cpstars.dal.entities.Star;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class extending star entity. Contains additional fetched data from external services.
 * This class is used for export to contain all the necessary information.
 *
 * @author Ľuboslav Halama
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExtendedStar extends Star {

	private ExternalDetails externalDetails;

	public ExtendedStar(Star star, ResponseData externalData) {
		super(
				star.getId(),
				star.getConsideredCategoryAffiliationProbabilityFlag(),
				star.getId_2009_A_AND_A_498_961_R(),
				star.getBinarySystemComponent(),
				star.getIcrsRightAscension(),
				star.getIcrsRightAscensionError(),
				star.getIcrsDeclination(),
				star.getIcrsDeclinationError(),
				star.getGalacticLongitude(),
				star.getGalacticLatitude(),
				star.getAlpha(),
				star.getDelta(),
				star.getMagnitudes(),
				star.getIdentifiers(),
				star.getMotions(),
				star.getRadialVelocities(),
				star.getStarDatasourceAttributes()
		);
		externalDetails = new ExternalDetails(externalData);
	}
}
