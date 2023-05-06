package cz.muni.fi.cpstars.bl.implementation.classes.astrosearcher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Response object for astrosearcher aliases query (obtain all object's aliases).
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aliases {

    String id;

    List<String> aliases;
}
