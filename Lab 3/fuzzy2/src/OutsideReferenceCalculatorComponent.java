import core.FuzzyPetriLogic.Executor.AsyncronRunnableExecutor;
import core.FuzzyPetriLogic.FuzzyDriver;
import core.FuzzyPetriLogic.FuzzyToken;
import core.FuzzyPetriLogic.PetriNet.FuzzyPetriNet;
import core.FuzzyPetriLogic.PetriNet.Recorders.FullRecorder;
import core.FuzzyPetriLogic.Tables.OneXOneTable;
import core.TableParser;

import java.util.HashMap;
import java.util.Map;

public class OutsideReferenceCalculatorComponent {
    static String reader = "" +
            "{[<ZR,NL><ZR,NM><ZR,ZR><ZR,PM><ZR,PL>]" +
            " [<ZR,NL><ZR,NM><ZR,ZR><ZR,PM><ZR,PL>]" +
            " [<ZR,NL><ZR,NM><ZR,ZR><ZR,PM><ZR,PL>]" +
            " [<ZR,NL><ZR,NM><ZR,ZR><ZR,PM><ZR,PL>]" +
            " [<ZR,NL><ZR,NM><ZR,ZR><ZR,PM><ZR,PL>]}";

    static String t2Table = "{[<PL><PM><ZR><NM><NL>]}";

    private final FuzzyPetriNet net;
    private int p1OutsideTemInp;
    private int t2Out;
    private final FuzzyDriver outsideTempDriver;
    private final FuzzyDriver tankWaterTempDriver;
    private final FullRecorder rec;
    private final AsyncronRunnableExecutor executor;

    public OutsideReferenceCalculatorComponent(Plant plant, HeaterTankControllerComponent comp, long simPeriod) {

        TableParser parser = new TableParser();

        net = new FuzzyPetriNet();

        int p0 = net.addPlace();
        net.setInitialMarkingForPlace(p0, FuzzyToken.zeroToken());
        p1OutsideTemInp = net.addInputPlace();
        int p2 = net.addPlace();
        int p3 = net.addPlace();

        int tr0Reader = net.addTransition(0, parser.parseTwoXTwoTable(reader));
        int tr1Delay = net.addTransition(1, OneXOneTable.defaultTable());
        t2Out = net.addOuputTransition(parser.parseOneXOneTable(t2Table));

        net.addArcFromPlaceToTransition(p0, tr0Reader, 1.0);
        net.addArcFromPlaceToTransition(p1OutsideTemInp, tr0Reader, 1.0);
        net.addArcFromPlaceToTransition(p2, tr1Delay, 1.0);
        net.addArcFromPlaceToTransition(p3, t2Out, 1.0);

        net.addArcFromTransitionToPlace(tr0Reader, p2);
        net.addArcFromTransitionToPlace(tr0Reader, p3);
        net.addArcFromTransitionToPlace(tr1Delay, p0);

        outsideTempDriver = FuzzyDriver.createDriverFromMinMax(-30, 10);

        tankWaterTempDriver = FuzzyDriver.createDriverFromMinMax(45, 68);

        net.addActionForOuputTransition(t2Out, tk -> { //<<<<<<<<<< this is the t2 action

            comp.setWaterRefTemp(tankWaterTempDriver.defuzzify(tk));//<<< that is connected to

//<<< The water tank

        });

        rec = new FullRecorder();

        executor = new AsyncronRunnableExecutor(net, simPeriod);

        executor.setRecorder(rec);
    }

        // These are the start, stop, get net , and get recorder methods

        public void start() {

            (new Thread(executor)).start(); }

        public void stop() { executor.stop(); }

        public void setOutsideTemp(double waterRefTemp) {

            Map<Integer, FuzzyToken> inputs = new HashMap<>();

            inputs.put(p1OutsideTemInp, outsideTempDriver.fuzzifie(waterRefTemp));

            executor.putTokenInInputPlace(inputs); }

        public FuzzyPetriNet getNet() { return net; }

        public FullRecorder getRecorder() { return rec; }
}
