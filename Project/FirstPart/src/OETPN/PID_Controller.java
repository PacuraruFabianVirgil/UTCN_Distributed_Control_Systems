package OETPN;

import Components.*;
import DataObjects.DataFloat;
import DataObjects.DataSubPetriNet;
import DataObjects.DataTransfer;
import DataOnly.SubPetri;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

import java.util.ArrayList;

public class PID_Controller {
    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Main Petri";
        pn.NetworkPort = 1080;

        DataFloat subConstantValue1 = new DataFloat();
        subConstantValue1.SetName("c1");
        subConstantValue1.SetValue(0.8f);
        pn.ConstantPlaceList.add(subConstantValue1);

        DataFloat subConstantValue2 = new DataFloat();
        subConstantValue2.SetName("c2");
        subConstantValue2.SetValue(0.2f);
        pn.ConstantPlaceList.add(subConstantValue2);

        DataFloat subConstantValue3 = new DataFloat();
        subConstantValue3.SetName("c3");
        subConstantValue3.SetValue(0.7f);
        pn.ConstantPlaceList.add(subConstantValue3);

        DataFloat p1 = new DataFloat();
        p1.SetName("p1");
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

        DataFloat p5 = new DataFloat();
        p5.SetName("p5");
        pn.PlaceList.add(p5);

        DataFloat p6 = new DataFloat();
        p6.SetName("p6");
        pn.PlaceList.add(p6);

        DataFloat p7 = new DataFloat();
        p7.SetName("p7");
        pn.PlaceList.add(p7);

        DataFloat p8 = new DataFloat();
        p8.SetName("p8");
        pn.PlaceList.add(p8);

        DataTransfer p9 = new DataTransfer();
        p9.SetName("p9");
        p9.Value = new TransferOperation("localhost", "1080", "p6");
        pn.PlaceList.add(p9);

        DataFloat p10 = new DataFloat();
        p10.SetName("p10");
        pn.PlaceList.add(p10);

        DataFloat p11 = new DataFloat();
        p11.SetName("p11");
        pn.PlaceList.add(p11);

        DataFloat p12 = new DataFloat();
        p12.SetName("p12");
        pn.PlaceList.add(p12);

        DataFloat p13 = new DataFloat();
        p13.SetName("p13");
        pn.PlaceList.add(p13);

        DataFloat p14 = new DataFloat();
        p14.SetName("p14");
        pn.PlaceList.add(p14);

        // T1 ------------------------------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p1");
        t1.InputPlaceName.add("p2");

        Condition T1Ct1 = new Condition(t1, "p1", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "p2", TransitionCondition.NotNull);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(t1, "p1", TransitionOperation.Move, "p3"));

        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        pn.Transitions.add(t1);

        // T2 ------------------------------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("p3");
        t2.InputPlaceName.add("p4");

        Condition T2Ct1 = new Condition(t2, "p3", TransitionCondition.NotNull);
        Condition T2Ct2 = new Condition(t2, "p4", TransitionCondition.NotNull);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        grdT2.Activations.add(new Activation(t2, "p3", TransitionOperation.Move, "p5"));
        grdT2.Activations.add(new Activation(t2, "p4", TransitionOperation.Sub, "p5"));
        grdT2.Activations.add(new Activation(t2, "p4", TransitionOperation.Move, "p6"));
        grdT2.Activations.add(new Activation(t2, "p3", TransitionOperation.Sub, "p6"));

        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        pn.Transitions.add(t2);

        // T3 ------------------------------------------------
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("p5");
        t3.InputPlaceName.add("p14");

        Condition T3Ct1 = new Condition(t3, "p5", TransitionCondition.NotNull);
        Condition T3Ct2 = new Condition(t3, "p14", TransitionCondition.NotNull);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(t3, "c1", TransitionOperation.Prod, "p5"));
        grdT3.Activations.add(new Activation(t3, "c2", TransitionOperation.Prod, "p14"));
        grdT3.Activations.add(new Activation(t3, "p5", TransitionOperation.Move, "p8"));
        grdT3.Activations.add(new Activation(t3, "p14", TransitionOperation.Add, "p8"));
        grdT3.Activations.add(new Activation(t3, "c1", TransitionOperation.Div, "p5"));
        grdT3.Activations.add(new Activation(t3, "c2", TransitionOperation.Div, "p14"));

        t3.GuardMappingList.add(grdT3);
        t3.Delay = 0;
        pn.Transitions.add(t3);

        // T4 ------------------------------------------------
        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "t4";
        t4.InputPlaceName.add("p6");

        Condition T4Ct1 = new Condition(t4, "p6", TransitionCondition.NotNull);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;
        grdT4.Activations.add(new Activation(t4, "p6", TransitionOperation.Move, "p7"));
        grdT4.Activations.add(new Activation(t4, "p6", TransitionOperation.Move, "p12"));

        t4.GuardMappingList.add(grdT4);
        t4.Delay = 1;
        pn.Transitions.add(t4);

        // T5 ------------------------------------------------
        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "t5";
        t5.InputPlaceName.add("p8");
        t5.InputPlaceName.add("p11");

        Condition T5Ct1 = new Condition(t5, "p8", TransitionCondition.NotNull);
        Condition T5Ct2 = new Condition(t5, "p11", TransitionCondition.NotNull);
        T5Ct1.SetNextCondition(LogicConnector.AND, T5Ct2);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;
        grdT5.Activations.add(new Activation(t5, "p8", TransitionOperation.Move, "p7"));
        grdT5.Activations.add(new Activation(t5, "c3", TransitionOperation.Prod, "p7"));
        grdT5.Activations.add(new Activation(t5, "p11", TransitionOperation.Add, "p7"));
        grdT5.Activations.add(new Activation(t5, "p8", TransitionOperation.Move, "p7"));
        grdT5.Activations.add(new Activation(t5, "c3", TransitionOperation.Prod, "p12"));
        grdT5.Activations.add(new Activation(t5, "p11", TransitionOperation.Add, "p7"));

        t5.GuardMappingList.add(grdT5);
        t5.Delay = 0;
        pn.Transitions.add(t5);

        // T6 ------------------------------------------------
        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "t6";
        t6.InputPlaceName.add("p10");

        Condition T6Ct1 = new Condition(t6, "p10", TransitionCondition.NotNull);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;
        grdT6.Activations.add(new Activation(t6, "p10", TransitionOperation.Move, "p11"));
        grdT6.Activations.add(new Activation(t6, "p10", TransitionOperation.Move, "p1"));

        t6.GuardMappingList.add(grdT6);
        t6.Delay = 1;
        pn.Transitions.add(t6);

        // T7 ------------------------------------------------
        PetriTransition t7 = new PetriTransition(pn);
        t7.TransitionName = "t7";
        t7.InputPlaceName.add("p9");

        Condition T7Ct1 = new Condition(t7, "p9", TransitionCondition.NotNull);

        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct1;
        grdT6.Activations.add(new Activation(t6, "p9", TransitionOperation.SendOverNetwork, "p6"));

        t7.GuardMappingList.add(grdT7);
        t7.Delay = 0;
        pn.Transitions.add(t7);

        // T8 ------------------------------------------------
        PetriTransition t8 = new PetriTransition(pn);
        t8.TransitionName = "t8";
        t8.InputPlaceName.add("p12");

        Condition T8Ct1 = new Condition(t8, "p12", TransitionCondition.NotNull);

        GuardMapping grdT8 = new GuardMapping();
        grdT8.condition = T8Ct1;
        grdT8.Activations.add(new Activation(t8, "p12", TransitionOperation.Move, "p13"));

        t8.GuardMappingList.add(grdT8);
        t8.Delay = 1;
        pn.Transitions.add(t8);

        // T9 ------------------------------------------------
        PetriTransition t9 = new PetriTransition(pn);
        t9.TransitionName = "t9";
        t9.InputPlaceName.add("p7");
        t9.InputPlaceName.add("p13");

        Condition T9Ct1 = new Condition(t9, "p7", TransitionCondition.NotNull);
        Condition T9Ct2 = new Condition(t9, "p13", TransitionCondition.NotNull);
        T9Ct1.SetNextCondition(LogicConnector.AND, T9Ct2);

        GuardMapping grdT9 = new GuardMapping();
        grdT9.condition = T9Ct1;
        grdT9.Activations.add(new Activation(t9, "c1", TransitionOperation.Prod, "p7"));
        grdT9.Activations.add(new Activation(t9, "c2", TransitionOperation.Prod, "p13"));
        grdT9.Activations.add(new Activation(t9, "p7", TransitionOperation.Move, "p14"));
        grdT9.Activations.add(new Activation(t9, "p13", TransitionOperation.Add, "p14"));
        grdT9.Activations.add(new Activation(t9, "c1", TransitionOperation.Div, "p7"));
        grdT9.Activations.add(new Activation(t9, "c2", TransitionOperation.Div, "p13"));
        

        t9.GuardMappingList.add(grdT9);
        t9.Delay = 0;
        pn.Transitions.add(t9);
        
        
        pn.Delay = 3000;
        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
