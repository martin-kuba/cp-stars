package cz.muni.fi.cpstars.dal.implementation.classes;

import astrosearcher.classes.ResponseData;
import astrosearcher.classes.simbad.SimbadData;
import astrosearcher.classes.vizier.VizierTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.muni.fi.cpstars.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class containing star details obtained from external sources (archives), e.g. Simbad.
 *
 * @author Ľuboslav Halama
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalDetails {

	@JsonIgnore
	private final String EFFECTIVE_TEMPERATURE_VALUES_REGEX = "(\\d*)\\s*(\\[\\s*[a-zA-Z]*\\s*\\])?";

	private Double effectiveTemperature;
	private String effectiveTemperatureUnit;
	private Double redshift;

	private List<VizierTable> vizierTables;

	public ExternalDetails(ResponseData externalData) {

		// if present, fetch Simbad data
		if (externalData.containsSimbadResponse()
				&& externalData.getSimbadResponse().getAssignedData() != null
				&& externalData.getSimbadResponse().getAssignedData().size() > 0) {
			SimbadData simbadData = externalData.getSimbadResponse().getAssignedData().get(0);

			// redshift
			this.setRedshift(StringUtils.ApplyIfNotEmptyOrNull(
					simbadData.getRedshift(),
					Double::parseDouble,
					null
			));

			// effective temperature
			this.setEffectiveTemperatureValues(simbadData.getEffectiveTemperature());
		}

		// if present, fetch Vizier data
		if (externalData.containsVizierResponse()) {
			this.vizierTables = externalData.getVizierResponse().getTables();
		}
	}

	public void setEffectiveTemperatureValues(String effectiveTemperatureValues) {
		// string may contain additional information besides value itself
		Matcher matcher = Pattern.compile(EFFECTIVE_TEMPERATURE_VALUES_REGEX).matcher(effectiveTemperatureValues);

		if (matcher.matches()) {
			this.effectiveTemperature = StringUtils.ApplyIfNotEmptyOrNull(
					matcher.group(1),
					Double::parseDouble,
					null
			);

			// check if there are units specified
			if (matcher.groupCount() == 2) {
				this.effectiveTemperatureUnit = matcher.group(2);
			}
		}
	}
}
