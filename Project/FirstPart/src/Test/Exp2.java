package Test;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Exp2 {
	public static void main(String[] args) {

		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Main Petri";
		pn.NetworkPort = 1081;
		
		DataCar p1 = new DataCar();
		p1.SetName("P1");
		pn.PlaceList.add(p1);

		DataCarQueue p2 = new DataCarQueue();
		p2.SetName("P2");
		p2.Value.Size = 3;
		pn.PlaceList.add(p2);

		DataCarQueue p3 = new DataCarQueue();
		p3.SetName("P3");
		p3.Value.Size = 3;
		pn.PlaceList.add(p3);

		DataCarQueue p4 = new DataCarQueue();
		p4.SetName("P4");
		p4.Value.Size = 3;
		pn.PlaceList.add(p4);

		DataCar p5 = new DataCar();
		p5.SetName("P5");
		pn.PlaceList.add(p5);

		DataCar p6 = new DataCar();
		p6.SetName("P6");
		pn.PlaceList.add(p6);

		DataCar p7 = new DataCar();
		p7.SetName("P7");
		pn.PlaceList.add(p7);

		DataCar p8 = new DataCar();
		p8.SetName("P8");
		pn.PlaceList.add(p8);

		// T1 ------------------------------------------------
		PetriTransition t1 = new PetriTransition(pn);
		t1.TransitionName = "T1";
		t1.InputPlaceName.add("P1");

		Condition T1Ct1 = new Condition(t1, "P1", TransitionCondition.NotNull);
		Condition T1Ct2 = new Condition(t1, "P2", TransitionCondition.CanAddCars);
		T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

		GuardMapping grdT1 = new GuardMapping();
		grdT1.condition = T1Ct1;
		grdT1.Activations.add(new Activation(t1, "P1", TransitionOperation.AddElement, "P2"));
		t1.GuardMappingList.add(grdT1);
		
		t1.Delay = 0;
		pn.Transitions.add(t1);

		// T2 ------------------------------------------------
		PetriTransition t2 = new PetriTransition(pn);
		t2.TransitionName = "T2";
		t2.InputPlaceName.add("P2");

		Condition T2Ct1 = new Condition(t2, "P2", TransitionCondition.HaveCarForMe);
		Condition T2Ct2 = new Condition(t2, "P3", TransitionCondition.CanAddCars);
		T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

		GuardMapping grdT2 = new GuardMapping();
		grdT2.condition = T2Ct1;
		grdT2.Activations.add(new Activation(t2, "P2", TransitionOperation.PopElementWithTargetToQueue, "P3"));
		t2.GuardMappingList.add(grdT2);
		
		t2.Delay = 0;
		pn.Transitions.add(t2);

		// T3 ------------------------------------------------
		PetriTransition t3 = new PetriTransition(pn);
		t3.TransitionName = "T3";
		t3.InputPlaceName.add("P3");

		Condition T3Ct1 = new Condition(t3, "P3", TransitionCondition.HaveCarForMe);
		Condition T3Ct2 = new Condition(t3, "P4", TransitionCondition.CanAddCars);
		T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

		GuardMapping grdT3 = new GuardMapping();
		grdT3.condition = T3Ct1;
		grdT3.Activations.add(new Activation(t3, "P3", TransitionOperation.PopElementWithTargetToQueue, "P4"));
		t3.GuardMappingList.add(grdT3);

		t3.Delay = 0;
		pn.Transitions.add(t3);

		// T4 ------------------------------------------------
		PetriTransition t4 = new PetriTransition(pn);
		t4.TransitionName = "T4";
		t4.InputPlaceName.add("P4");

		Condition T4Ct1 = new Condition(t4, "P4", TransitionCondition.HaveCarForMe);
		Condition T4Ct2 = new Condition(t4, "P2", TransitionCondition.CanAddCars);
		T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

		GuardMapping grdT4 = new GuardMapping();
		grdT4.condition = T4Ct1;
		grdT4.Activations.add(new Activation(t4, "P4", TransitionOperation.PopElementWithTargetToQueue, "P2"));
		t4.GuardMappingList.add(grdT4);

		t4.Delay = 0;
		pn.Transitions.add(t4);

		// T1e -----------------------------------------------
		PetriTransition t1e = new PetriTransition(pn);
		t1e.TransitionName = "T1e";
		t1e.InputPlaceName.add("P2");

		Condition T1eCt1 = new Condition(t1e, "P2", TransitionCondition.HaveCarForMe);

		GuardMapping grdT1e = new GuardMapping();
		grdT1e.condition = T1eCt1;
		grdT1e.Activations.add(new Activation(t1e, "P2", TransitionOperation.PopElementWithTarget, "P6"));
		t1e.GuardMappingList.add(grdT1e);

		t1e.Delay = 0;
		pn.Transitions.add(t1e);

		// T2e -----------------------------------------------
		PetriTransition t2e = new PetriTransition(pn);
		t2e.TransitionName = "T2e";
		t2e.InputPlaceName.add("P3");

		Condition T2eCt1 = new Condition(t2e, "P3", TransitionCondition.HaveCarForMe);

		GuardMapping grdT2e = new GuardMapping();
		grdT2e.condition = T2eCt1;
		grdT2e.Activations.add(new Activation(t2e, "P3", TransitionOperation.PopElementWithTarget, "P7"));
		t2e.GuardMappingList.add(grdT2e);

		t2e.Delay = 0;
		pn.Transitions.add(t2e);

		// T3e -----------------------------------------------
		PetriTransition t3e = new PetriTransition(pn);
		t3e.TransitionName = "T3e";
		t3e.InputPlaceName.add("P4");

		Condition T3eCt1 = new Condition(t3e, "P4", TransitionCondition.HaveCarForMe);

		GuardMapping grdT3e = new GuardMapping();
		grdT3e.condition = T3eCt1;
		grdT3e.Activations.add(new Activation(t3e, "P4", TransitionOperation.PopElementWithTarget, "P8"));
		t3e.GuardMappingList.add(grdT3e);

		t3e.Delay = 0;
		pn.Transitions.add(t3e);

		// T4e -----------------------------------------------
		PetriTransition t4e = new PetriTransition(pn);
		t4e.TransitionName = "T4e";
		t4e.InputPlaceName.add("P2");

		Condition T4eCt1 = new Condition(t4e, "P2", TransitionCondition.HaveCarForMe);

		GuardMapping grdT4e = new GuardMapping();
		grdT4e.condition = T4eCt1;
		grdT4e.Activations.add(new Activation(t4e, "P2", TransitionOperation.PopElementWithTarget, "P5"));
		t4e.GuardMappingList.add(grdT4e);

		t4e.Delay = 0;
		pn.Transitions.add(t4e);

		System.out.println("Exp2 started \n ------------------------------");
		pn.Delay = 2000;
		//pn.Start();
		
		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = pn;
		frame.setVisible(true);

	}
}
