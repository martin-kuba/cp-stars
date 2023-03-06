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
 * Star-Datasource Attribute definition. Contains general information about attribute.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "attribute_definitions")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class AttributeDefinition {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    @NotNull
    private long id;

    @Column(name = "name", length = Constraints.ATTRIBUTE_DEFINITION_NAME_MAX_LENGTH, unique = true)
    @Size(max = Constraints.ATTRIBUTE_DEFINITION_NAME_MAX_LENGTH)
    @NonNull
    @NotNull
    private String name;

    @Column(name = "type", length = Constraints.ATTRIBUTE_DEFINITION_TYPE_MAX_LENGTH)
    @Size(max = Constraints.ATTRIBUTE_DEFINITION_TYPE_MAX_LENGTH)
    @NonNull
    @NotNull
    private String type;

    @Column(name = "description", length = Constraints.ATTRIBUTE_DEFINITION_DESCRIPTION_MAX_LENGTH)
    @Size(max = Constraints.ATTRIBUTE_DEFINITION_DESCRIPTION_MAX_LENGTH)
    @NonNull
    @NotNull
    private String description;
}
