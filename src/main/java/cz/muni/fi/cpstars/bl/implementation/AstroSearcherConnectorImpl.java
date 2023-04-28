package cz.muni.fi.cpstars.bl.implementation;


import astrosearcher.classes.ResponseData;
import cz.muni.fi.cpstars.bl.Constants;
import cz.muni.fi.cpstars.bl.classes.LightCurveMeasurement;
import cz.muni.fi.cpstars.bl.classes.astrosearcher.Aliases;
import cz.muni.fi.cpstars.bl.interfaces.AstroSearcherConnector;
import cz.muni.fi.cpstars.bl.interfaces.ConnectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
//        System.out.println("PARAM: " + paramString);
        return connectionUtils.sendRequestGetData(Constants.ASTROSEARCHER_RESULTS_JSON_URL, paramString, RequestMethod.GET.toString(), ResponseData.class);
    }

    @Override
    public ResponseData getData(String baseIdentifier, int queryMast, int queryVizier, int querySimbad) {
        String paramString = "id=" + URLEncoder.encode(baseIdentifier, StandardCharsets.UTF_8)
                + "&queryMast=" + queryMast
                + "&queryVizier=" + queryVizier
                + "&querySimbad=" + querySimbad;
        System.out.println("PARAM: " + paramString);
        return connectionUtils.sendRequestGetData(Constants.ASTROSEARCHER_RESULTS_JSON_URL, paramString, RequestMethod.GET.toString(), ResponseData.class);
    }

    @Override
    public List<LightCurveMeasurement> getStarLightCurveMeasurements(String baseIdentifier) {
        // TODO: send actual query, remove 'fake' data
        List<LightCurveMeasurement> measurements = new ArrayList<>(){{
            add(new LightCurveMeasurement(1983.8402407806395,1.0183275339020887,0.0));
            add(new LightCurveMeasurement(1983.8610742086548,1.018579416215989,0.0001668747027644896));
            add(new LightCurveMeasurement(1983.8819076361262,1.012654748217414,0.006416644653626203));
            add(new LightCurveMeasurement(1983.902741063058,1.0137012069290978,0.009055916595057336));
            add(new LightCurveMeasurement(1983.9444079154212,1.017220236839136,0.015224097692008227));
            add(new LightCurveMeasurement(1984.0069081905758,1.018399926798136,0.020304724862844594));
            add(new LightCurveMeasurement(1984.048575038697,1.0110955473012926,0.025267175678094493));
            add(new LightCurveMeasurement(1984.0694084622246,1.0092846915491334,0.027530119030861912));
            add(new LightCurveMeasurement(1984.2777426823284,1.0030203214153528,0.04508963392477554));
            add(new LightCurveMeasurement(1984.3610763648956,0.9983492582609059,0.05344751962985044));
            add(new LightCurveMeasurement(1984.4235766255947,0.9979866315514218,0.05808469863142303));
            add(new LightCurveMeasurement(1984.4860768855924,1.002174764549608,0.055765344235131326));
            add(new LightCurveMeasurement(1984.5694105650023,1.001311988125851,0.05897165871124272));
            add(new LightCurveMeasurement(1984.590243984817,1.0040309160836112,0.05727575789315557));
            add(new LightCurveMeasurement(1984.6110774046294,0.9992947073619556,0.06023858063337683));
            add(new LightCurveMeasurement(1984.6944110840834,1.0051944540759905,0.054543359923940285));
            add(new LightCurveMeasurement(1984.7569113441057,1.003103022066255,0.0587285494231053));
            add(new LightCurveMeasurement(1984.8402450250742,1.0008382619145966,0.057970410494655754));
            add(new LightCurveMeasurement(1984.9444121284255,1.0035992192541376,0.05949784307767546));
            add(new LightCurveMeasurement(1984.9652455494454,1.0020243610918633,0.05969321013801663));
            add(new LightCurveMeasurement(1984.986078970587,1.00041466893403,0.061988725291056264));
            add(new LightCurveMeasurement(1985.0069123918693,1.0011485650305805,0.06519535847362824));
            add(new LightCurveMeasurement(1985.1319129226451,1.0011873059876926,0.06827986334050246));
            add(new LightCurveMeasurement(1985.1527463450002,1.0005227423667105,0.06921054994671498));
            add(new LightCurveMeasurement(1985.194413190242,1.0048040853786746,0.06815435673632245));
        }};

        return measurements;
    }

}
