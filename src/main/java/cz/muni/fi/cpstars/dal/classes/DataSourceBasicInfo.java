package cz.muni.fi.cpstars.dal.classes;


import cz.muni.fi.cpstars.dal.Constraints;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * Class for storing basic (general) information about data source.
 * Information are to be used in data sources overview.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataSourceBasicInfo {

	@NonNull
	@NotNull
	private long id;

	@Size(max = Constraints.DATASOURCE_NAME_MAX_LENGTH)
	@NonNull
	@NotNull
	private String name;

	@NonNull
	@NotNull
	private Integer year;

	@Size(max = Constraints.DATASOURCE_BIBCODE_LENGTH)
	@NonNull
	@NotNull
	private String bibcode;
}
