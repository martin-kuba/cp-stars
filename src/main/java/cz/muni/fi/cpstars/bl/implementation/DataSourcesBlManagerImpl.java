package cz.muni.fi.cpstars.bl.implementation;

import cz.muni.fi.cpstars.bl.interfaces.DataSourcesBlManager;
import cz.muni.fi.cpstars.dal.implementation.classes.DataSourceBasicInfo;
import cz.muni.fi.cpstars.dal.entities.DataSource;
import cz.muni.fi.cpstars.dal.interfaces.DataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSourcesBlManagerImpl implements DataSourcesBlManager {

	private final DataSourceRepository dataSourceRepository;

	@Autowired
	public DataSourcesBlManagerImpl(DataSourceRepository dataSourceRepository) {
		this.dataSourceRepository = dataSourceRepository;
	}

	@Override
	public List<DataSourceBasicInfo> getAllDatasourcesBasicInfo() {
		return dataSourceRepository.loadBasicInfo();
	}

	@Override
	public DataSource getDatasource(long id) {
		return dataSourceRepository.findById(id);
	}
}
