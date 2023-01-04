import core.FuzzyPetriLogic.Executor.AsyncronRunnableExecutor;
import core.FuzzyPetriLogic.FuzzyDriver;
import core.FuzzyPetriLogic.FuzzyToken;
import core.FuzzyPetriLogic.PetriNet.FuzzyPetriNet;
import core.FuzzyPetriLogic.PetriNet.Recorders.FullRecorder;
import core.FuzzyPetriLogic.Tables.OneXOneTable;
import core.FuzzyPetriLogic.Tables.TwoXOneTable;
import core.TableParser;

import java.util.HashMap;
import java.util.Map;

public class HeaterTankControllerComponent {

// HTC component

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

    String historyMerger = ""//

            + "{[<NL,NL><NL,NL><NL,NL><NM,NM><ZR,ZR>]" //

            + " [<NL,NL><NL,NL><NM,NM><ZR,ZR><PM,PM>]" //

            + " [<NL,NL><NM,NM><ZR,ZR><PM,PM><PL,PL>]"//

            + " [<NM,NM><ZR,ZR><PM,PM><PL,PL><PL,PL>]"//

            + " [<ZR,ZR><PM,PM><PL,PL><PL,PL><PL,PL>]}";

    private final AsyncronRunnableExecutor executor;

    private final FullRecorder rec;

    private final FuzzyDriver tankWaterTemperatureDriver;

    private final int p1RefInp;

    private final int p3SysInp;

    private final FuzzyPetriNet net;

    public HeaterTankControllerComponent(Plant plant, long simPeriod) {

// constructing the Petri net for the HTC component

        TableParser parser = new TableParser();

        net = new FuzzyPetriNet();

        int p0 = net.addPlace();

        net.setInitialMarkingForPlace(p0, FuzzyToken.zeroToken());

// transition t0 executes Reading

        int tr0Reader = net.addTransition(0, parser.parseTwoXOneTable(reader));

        p1RefInp = net.addInputPlace();

        net.addArcFromPlaceToTransition(p0, tr0Reader, 1.0);

        net.addArcFromPlaceToTransition(p1RefInp, tr0Reader, 1.0);

        int p2 = net.addPlace();

        net.addArcFromTransitionToPlace(tr0Reader, p2);

        p3SysInp = net.addInputPlace();

// transition t1 - differentiator

        int tr1Diff = net.addTransition(0, parser.parseTwoXTwoTable(doubleChannelDifferentiator));

        net.addArcFromPlaceToTransition(p2, tr1Diff, 1.0);

        net.addArcFromPlaceToTransition(p3SysInp, tr1Diff, 1.0);

        int p4 = net.addPlace();

        net.addArcFromTransitionToPlace(tr1Diff, p4);

// transition t2 exit

        int tr2Out = net.addOuputTransition(OneXOneTable.defaultTable());

        int p5 = net.addPlace();

        net.addArcFromTransitionToPlace(tr1Diff, p5);

// transition t3 with delay

        int t3 = net.addTransition(1, OneXOneTable.defaultTable());

        net.addArcFromPlaceToTransition(p5, t3, 1.0);

        net.addArcFromTransitionToPlace(t3, p0);

        int p6 = net.addPlace();

        net.setInitialMarkingForPlace(p6, FuzzyToken.zeroToken());

// transition t4 adder

        int t4Adder = net.addTransition(0, parser.parseTwoXTwoTable(historyMerger));

        //net.addArcFromPlaceToTransition(p4, t4Adder, 1.2);

        net.addArcFromPlaceToTransition(p6, t4Adder, 1.0);

        int p7 = net.addPlace();

        net.addArcFromTransitionToPlace(t4Adder, p7);

        net.addArcFromPlaceToTransition(p7, tr2Out, 1.0);

        int p8 = net.addPlace();

        net.addArcFromTransitionToPlace(t4Adder, p8);

        int t5Delay = net.addTransition(1, OneXOneTable.defaultTable());

        net.addArcFromPlaceToTransition(p8, t5Delay, 1.0);

        net.addArcFromTransitionToPlace(t5Delay, p6);

        int p9 = net.addPlace();

        int p10 = net.addPlace();

        int t6Delay = net.addTransition(1, OneXOneTable.defaultTable());

        int t7Adder = net.addTransition(0, TwoXOneTable.defaultTable());

        net.addArcFromPlaceToTransition(p4, t6Delay, 1.0);

        net.addArcFromPlaceToTransition(p4, t7Adder, 1.2);

        net.addArcFromPlaceToTransition(p9, t7Adder, 1.0);

        net.addArcFromPlaceToTransition(p10, t4Adder, 1.0);

        net.addArcFromTransitionToPlace(t6Delay, p9);

        net.addArcFromTransitionToPlace(t7Adder, p10);

// specify the limits for fuzzyfication

        FuzzyDriver tankCommandDriver = FuzzyDriver.createDriverFromMinMax(-1.0, 1.0);

// specify the limits for boiler’s water

        tankWaterTemperatureDriver = FuzzyDriver.createDriverFromMinMax(-75, 75);

        rec = new FullRecorder();

//creating the executor

        executor = new AsyncronRunnableExecutor(net, simPeriod);

        executor.setRecorder(rec);

//adding the action for the output transition t2 – gas command

        net.addActionForOuputTransition(tr2Out, tk -> plant.setHeaterGasCmd(tankCommandDriver.defuzzify(tk)));
    }

    // reading the boiler temperature

    public void setTankWaterTemp ( double tankWaterTemperature){

        Map<Integer, FuzzyToken> inputs = new HashMap<>();

        inputs.put(p3SysInp, tankWaterTemperatureDriver.fuzzifie(tankWaterTemperature));

        executor.putTokenInInputPlace(inputs);
    }

// reading the boiler’s temperature reference

    public void setWaterRefTemp ( double waterRefTemp){

        Map<Integer, FuzzyToken> inputs = new HashMap<>();

        inputs.put(p1RefInp, tankWaterTemperatureDriver.fuzzifie(waterRefTemp));

        executor.putTokenInInputPlace(inputs);
    }

//methods for visualizing the Petri net

    public FuzzyPetriNet getNet() {
        return net;
    }

    public FullRecorder getRecorder() {
        return rec;
    }

    public void start() {
        (new Thread(executor)).start();
    }

    public void stop() {
        executor.stop();
    }
}
