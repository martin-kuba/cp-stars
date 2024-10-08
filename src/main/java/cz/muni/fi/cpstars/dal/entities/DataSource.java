package cz.muni.fi.cpstars.dal.entities;

import cz.muni.fi.cpstars.dal.Constraints;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Datasource entity, e.g. APASS.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "datasources")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class DataSource {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    @NotNull
    private long id;

    @Column(name = "name", length = Constraints.DATASOURCE_NAME_MAX_LENGTH)
    @Size(max = Constraints.DATASOURCE_NAME_MAX_LENGTH)
    @NonNull
    @NotNull
    private String name;

    @Column(name = "full_name", length = Constraints.DATASOURCE_FULL_NAME_MAX_LENGTH)
    @Size(max = Constraints.DATASOURCE_FULL_NAME_MAX_LENGTH)
    @NonNull
    @NotNull
    private String fullName;

    @Column(name = "year")
    @NonNull
    @NotNull
    private Integer year;

    @Column(name = "bibcode", length = Constraints.DATASOURCE_BIBCODE_LENGTH)
    @Size(max = Constraints.DATASOURCE_BIBCODE_LENGTH)
    @NonNull
    @NotNull
    private String bibcode;

    @Column(name = "description", length = Constraints.DATASOURCE_DESCRIPTION_MAX_LENGTH)
    @Size(max = Constraints.DATASOURCE_DESCRIPTION_MAX_LENGTH)
    @NonNull
    @NotNull
    private String description;
}
