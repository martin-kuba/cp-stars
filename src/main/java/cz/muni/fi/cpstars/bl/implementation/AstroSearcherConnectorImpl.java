package cz.muni.fi.cpstars.bl.implementation;


import cz.muni.fi.cpstars.bl.Constants;
import cz.muni.fi.cpstars.bl.classes.astrosearcher.Aliases;
import cz.muni.fi.cpstars.bl.interfaces.AstroSearcherConnector;
import cz.muni.fi.cpstars.bl.interfaces.ConnectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Service
public class AstroSearcherConnectorImpl implements AstroSearcherConnector {

    private final ConnectionUtils connectionUtils;

    @Autowired
    public AstroSearcherConnectorImpl(ConnectionUtilsImpl connectionUtilsImpl) {
        this.connectionUtils = connectionUtilsImpl;
    }

    @Override
    public List<String> getAliases(String baseIdentifier) {
        String paramString = "id=" + baseIdentifier;
        return ((Aliases) connectionUtils.sendRequestGetData(Constants.ASTROSEARCHER_IDENTIFIERS_JSON_URL, paramString, RequestMethod.GET.toString(), Aliases.class)).getAliases();
    }
}
