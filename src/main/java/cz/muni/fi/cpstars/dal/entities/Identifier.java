package cz.muni.fi.cpstars.dal.entities;


import cz.muni.fi.cpstars.dal.Constraints;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Star basic identifiers entity class.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "identifiers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Identifier {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    @NotNull
    private long id;

    @ManyToOne
    @JoinColumn(name = "star_id", referencedColumnName = "id")
    @NonNull
    @NotNull
    private Star star;

    @JoinColumn(name = "datasource_id")
    @ManyToOne
    @NonNull
    @NotNull
    private DataSource datasource;

    @Column(name = "name", length = Constraints.IDENTIFIERS_MAX_LENGTH)
    @Size(max = Constraints.IDENTIFIERS_MAX_LENGTH)
    @NonNull
    @NotNull
    private String name;

    public boolean isDefined() {
        return name.length() > 0;
    }
}
