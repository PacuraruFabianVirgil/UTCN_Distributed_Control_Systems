import core.FuzzyPetriLogic.Executor.AsyncronRunnableExecutor;
import core.FuzzyPetriLogic.FuzzyDriver;
import core.FuzzyPetriLogic.FuzzyToken;
import core.FuzzyPetriLogic.PetriNet.FuzzyPetriNet;
import core.FuzzyPetriLogic.PetriNet.Recorders.FullRecorder;
import core.FuzzyPetriLogic.Tables.OneXOneTable;
import core.TableParser;

import java.util.HashMap;
import java.util.Map;

public class RoomTemperatureControllerComponent {

    static String reader = "" +

            "{[<NL><NM><ZR><PM><PL>]" +

            " [<NL><NM><ZR><PM><PL>]" +

            " [<NL><NM><ZR><PM><PL>]" +

            " [<NL><NM><ZR><PM><PL>]" +

            " [<NL><NM><ZR><PM><PL>]}";

    static String doubleChannelDifferentiator = ""//

            + "{[<ZR,ZR><NM,NM><NL,NL><NL,NL><NL,NL>]" //

            + " [<PM,PM><ZR,ZR><NM,NM><NL,NL><NL,NL>]" //

            + " [<PL,PL><PM,PM><ZR,ZR><NM,NM><NL,NL>]"//

            + " [<PL,PL><PL,PL><PM,PM><ZR,ZR><NM,NM>]"//

            + " [<PL,PL><PL,PL><PL,PL><PM,PM><ZR,ZR>]}";

    static String t3Table = "{[<FF,ZR>,<FF,FF>, <FF,FF>, <FF,FF>, <ZR, FF>]}";

    private final int p1RefInp;

    private final FuzzyPetriNet net;

    private final FuzzyDriver temperatureDriver;

    private final FullRecorder rec;

    private final AsyncronRunnableExecutor executor;

    private final int p3RealInp;

//receive the reference period for the plant by the constructor

    public RoomTemperatureControllerComponent(Plant plant, long simPeriod) {

        net = new FuzzyPetriNet();

        TableParser parser = new TableParser();

        int p0 = net.addPlace();

        net.setInitialMarkingForPlace(p0, FuzzyToken.zeroToken());

        p1RefInp = net.addInputPlace();

        int t0 = net.addTransition(0, parser.parseTwoXOneTable(reader));

        net.addArcFromPlaceToTransition(p0, t0, 1.0);

        net.addArcFromPlaceToTransition(p1RefInp, t0, 1.0);

        int p2 = net.addPlace();

        net.addArcFromTransitionToPlace(t0, p2);

        p3RealInp = net.addInputPlace();

        int t1 = net.addTransition(0, parser.parseTwoXTwoTable(doubleChannelDifferentiator));

        net.addArcFromPlaceToTransition(p2, t1, 1.0);

        net.addArcFromPlaceToTransition(p3RealInp, t1, 1.0);

        int p4 = net.addPlace();

        net.addArcFromTransitionToPlace(t1, p4);

        int t2 = net.addTransition(1, OneXOneTable.defaultTable());

        net.addArcFromPlaceToTransition(p4, t2, 1.0);

        net.addArcFromTransitionToPlace(t2, p0);

        int p5 = net.addPlace();

        net.addArcFromTransitionToPlace(t1, p5);

        int t3 = net.addTransition(0, parser.parseOneXTwoTable(t3Table));

        int p6 = net.addPlace();

        net.addArcFromTransitionToPlace(t3, p6);

        int t4 = net.addOuputTransition(OneXOneTable.defaultTable());

        net.addArcFromPlaceToTransition(p6, t4, 1.0);

        int p7 = net.addPlace();

        net.addArcFromTransitionToPlace(t3, p7);

        int t5 = net.addOuputTransition(OneXOneTable.defaultTable());

        net.addArcFromPlaceToTransition(p7, t5, 1.0);

        net.addArcFromPlaceToTransition(p5, t3, 120.0);

        net.addActionForOuputTransition(t4, t -> plant.setHeatingOn(true));

        net.addActionForOuputTransition(t5, t -> plant.setHeatingOn(false));

        temperatureDriver = FuzzyDriver.createDriverFromMinMax(-40, 40);

        rec = new FullRecorder();

        executor = new AsyncronRunnableExecutor(net, simPeriod);

        executor.setRecorder(rec);

    }

    public void start() { (new Thread(executor)).start(); }

    public void stop() { executor.stop(); }

    public void setInput(double roomTemperatureRef, double roomTemperature) {

        Map<Integer, FuzzyToken> inputs = new HashMap<>();

        inputs.put(p1RefInp, temperatureDriver.fuzzifie(roomTemperatureRef));

        inputs.put(p3RealInp, temperatureDriver.fuzzifie(roomTemperature));

        executor.putTokenInInputPlace(inputs);

    }

    public FuzzyPetriNet getNet() { return net; }

    public FullRecorder getRecorder() { return rec; }

}
