package cz.muni.fi.cpstars.bl.implementation;


import astrosearcher.classes.ResponseData;
import cz.muni.fi.cpstars.bl.Constants;
import cz.muni.fi.cpstars.bl.implementation.classes.LightCurveMeasurement;
import cz.muni.fi.cpstars.bl.implementation.classes.astrosearcher.Aliases;
import cz.muni.fi.cpstars.bl.interfaces.AstroSearcherConnector;
import cz.muni.fi.cpstars.bl.interfaces.ConnectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
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
        String paramString = "id=" + URLEncoder.encode(baseIdentifier, StandardCharsets.UTF_8);
        return connectionUtils.sendRequestGetData(Constants.ASTROSEARCHER_IDENTIFIERS_JSON_URL, paramString, RequestMethod.GET.toString(), Aliases.class).getAliases();
    }

    @Override
    public ResponseData getData(String baseIdentifier) {
        String paramString = "id=" + URLEncoder.encode(baseIdentifier, StandardCharsets.UTF_8);
        return connectionUtils.sendRequestGetData(Constants.ASTROSEARCHER_RESULTS_JSON_URL, paramString, RequestMethod.GET.toString(), ResponseData.class);
    }

    @Override
    public ResponseData getData(String baseIdentifier, int queryMast, int queryVizier, int querySimbad) {
        String paramString = "id=" + URLEncoder.encode(baseIdentifier, StandardCharsets.UTF_8)
                + "&queryMast=" + queryMast
                + "&queryVizier=" + queryVizier
                + "&querySimbad=" + querySimbad;
        return connectionUtils.sendRequestGetData(Constants.ASTROSEARCHER_RESULTS_JSON_URL, paramString, RequestMethod.GET.toString(), ResponseData.class);
    }
}
