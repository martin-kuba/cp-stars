package cz.muni.fi.cpstars.bl.implementation.export;

import cz.muni.fi.cpstars.bl.interfaces.export.ExportTxtBlManager;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business layer class for storing and providing all format-specific export managers.
 *
 * @author Ľuboslav Halama
 */
@Service
@Getter
public class ExportBlManagers {

	private final ExportTxtBlManager txtBlManager;

	@Autowired
	public ExportBlManagers(ExportTxtBlManagerImpl exportTxtBlManagerImpl) {
		this.txtBlManager = exportTxtBlManagerImpl;
	}
}
