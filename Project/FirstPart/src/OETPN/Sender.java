package OETPN;

import java.util.ArrayList;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFloat;
import DataObjects.DataSubPetriNet;
import DataObjects.DataTransfer;
import DataOnly.SubPetri;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Sender {

    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Sender";
        pn.NetworkPort = 1080;

        DataFloat p1 = new DataFloat();
        p1.SetName("p1");
        p1.SetValue(1.0f);
        pn.PlaceList.add(p1);

        DataFloat p2 = new DataFloat();
        p2.SetName("p2");
        pn.PlaceList.add(p2);

        DataFloat p3 = new DataFloat();
        p3.SetName("p3");
        pn.PlaceList.add(p3);

        DataFloat p4 = new DataFloat();
        p4.SetName("p4");
        pn.PlaceList.add(p4);

        DataTransfer p5 = new DataTransfer();
        p5.SetName("p5");
        p5.Value = new TransferOperation("localhost", "1081", "p2");
        pn.PlaceList.add(p5);

        DataFloat constantValue1 = new DataFloat();
        constantValue1.SetName("constantValue1");
        constantValue1.SetValue(10.0f);
        pn.ConstantPlaceList.add(constantValue1);

        DataFloat constantValue2 = new DataFloat();
        constantValue2.SetName("constantValue2");
        constantValue2.SetValue(0.01f);
        pn.ConstantPlaceList.add(constantValue2);

        // T1 ------------------------------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p1");
        t1.InputPlaceName.add("p2");

        Condition T1Ct1 = new Condition(t1, "p1", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "p2", TransitionCondition.MoreThan, "constantValue1");
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        ArrayList<String> p2p1 = new ArrayList<>();
        p2p1.add("p2");
        p2p1.add("p1");
        grdT1.Activations.add(new Activation(t1, p2p1, TransitionOperation.Sub, "p3"));

        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        pn.Transitions.add(t1);

        // T2 ------------------------------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("p3");

        Condition T2Ct1 = new Condition(t2, "p3", TransitionCondition.NotNull);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;

        ArrayList<String> prod = new ArrayList<>();
        prod.add("p3");
        prod.add("constantValue2");
        grdT1.Activations.add(new Activation(t2, prod, TransitionOperation.Prod, "p4"));

        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        pn.Transitions.add(t2);

        // T3 ------------------------------------------------
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("p4");

        Condition T3Ct1 = new Condition(t3, "p4", TransitionCondition.NotNull);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(t3, "p4", TransitionOperation.Copy, "p1"));
        grdT3.Activations.add(new Activation(t3, "p4", TransitionOperation.SendOverNetwork, "p5"));

        t3.GuardMappingList.add(grdT3);
        t3.Delay = 0;
        pn.Transitions.add(t3);

        System.out.println("Sender started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}

