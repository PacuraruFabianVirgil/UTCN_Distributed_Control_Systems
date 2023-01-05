package CommonProject;

import Components.*;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Intersections {
    public static void main(String[] args) {

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Intersections";

        pn.NetworkPort = 1080;

        DataString full = new DataString();
        full.SetName("full");
        full.SetValue("full");
        pn.ConstantPlaceList.add(full);

        DataString green = new DataString();
        green.Printable = false;
        green.SetName("green");
        green.SetValue("green");
        pn.ConstantPlaceList.add(green);




        // ********************************************************************************************************* //
        // ********************************************* Places **************************************************** //
        // ********************************************************************************************************* //


        // --------------------------------------------------------------------------------------------------------- //
        // ------------------------------------------ Intersection 1 ----------------------------------------------- //
        // --------------------------------------------------------------------------------------------------------- //

        // ---------------------------------------------- Lane 1 --------------------------------------------------- //

        DataCar p1 = new DataCar();
        p1.SetName("P_a1");
        pn.PlaceList.add(p1);

        DataCarQueue p2 = new DataCarQueue();
        p2.Value.Size = 3;
        p2.SetName("P_x1");
        pn.PlaceList.add(p2);

        DataString p3 = new DataString();
        p3.SetName("P_TL1");
        pn.PlaceList.add(p3);

        DataCar p4 = new DataCar();
        p4.SetName("P_b1");
        pn.PlaceList.add(p4);

        DataTransfer op1 = new DataTransfer();
        op1.SetName("OP1");
        op1.Value = new TransferOperation("localhost", "1081", "in1");
        pn.PlaceList.add(op1);

        // ---------------------------------------------- Lane 2 --------------------------------------------------- //

        DataCar p5 = new DataCar();
        p5.SetName("P_a2");
        pn.PlaceList.add(p5);

        DataCarQueue p6 = new DataCarQueue();
        p6.Value.Size = 3;
        p6.SetName("P_x2");
        pn.PlaceList.add(p6);

        DataString p7 = new DataString();
        p7.SetName("P_TL2");
        pn.PlaceList.add(p7);

        DataCar p8 = new DataCar();
        p8.SetName("P_b2");
        pn.PlaceList.add(p8);

        DataTransfer op2 = new DataTransfer();
        op2.SetName("OP2");
        op2.Value = new TransferOperation("localhost", "1081", "in2");
        pn.PlaceList.add(op2);

        // -------------------------------------------- Exit lane 1 ------------------------------------------------ //

        DataCarQueue p9 = new DataCarQueue();
        p9.Value.Size = 3;
        p9.SetName("P_o1");
        pn.PlaceList.add(p9);

        DataCar p10 = new DataCar();
        p10.SetName("P_o1Exit");
        pn.PlaceList.add(p10);

        // -------------------------------------------- Exit lane 2 ------------------------------------------------ //

        DataCarQueue p11 = new DataCarQueue();
        p11.Value.Size = 3;
        p11.SetName("P_o2");
        pn.PlaceList.add(p11);

        DataCar p12 = new DataCar();
        p12.SetName("P_o2Exit");
        pn.PlaceList.add(p12);

        // -------------------------------------------- Exit lane 3 ------------------------------------------------ //

        DataCarQueue p13 = new DataCarQueue();
        p13.Value.Size = 3;
        p13.SetName("P_o3");
        pn.PlaceList.add(p13);

        DataCar p14 = new DataCar();
        p14.SetName("P_o3Exit");
        pn.PlaceList.add(p14);

        // ---------------------------------------------- Center --------------------------------------------------- //

        DataCarQueue p15 = new DataCarQueue();
        p15.Value.Size = 3;
        p15.SetName("P_I1");
        pn.PlaceList.add(p15);

        // --------------------------------------------------------------------------------------------------------- //
        // ------------------------------------------ Intersection 2 ----------------------------------------------- //
        // --------------------------------------------------------------------------------------------------------- //

        // ---------------------------------------------- Lane 1 --------------------------------------------------- //

        DataCar p16 = new DataCar();
        p16.SetName("P_a3");
        pn.PlaceList.add(p16);

        DataCarQueue p17 = new DataCarQueue();
        p17.Value.Size = 3;
        p17.SetName("P_x3");
        pn.PlaceList.add(p17);

        DataString p18 = new DataString();
        p18.SetName("P_TL3");
        pn.PlaceList.add(p18);

        DataCar p19 = new DataCar();
        p19.SetName("P_b3");
        pn.PlaceList.add(p19);

        DataTransfer op3 = new DataTransfer();
        op3.SetName("OP3");
        op3.Value = new TransferOperation("localhost", "1081", "in3");
        pn.PlaceList.add(op3);

        // ---------------------------------------------- Lane 2 --------------------------------------------------- //

        DataCar p20 = new DataCar();
        p20.SetName("P_a4");
        pn.PlaceList.add(p20);

        DataCarQueue p21 = new DataCarQueue();
        p21.Value.Size = 3;
        p21.SetName("P_x4");
        pn.PlaceList.add(p21);

        DataString p22 = new DataString();
        p22.SetName("P_TL4");
        pn.PlaceList.add(p22);

        DataCar p23 = new DataCar();
        p23.SetName("P_b4");
        pn.PlaceList.add(p23);

        DataTransfer op4 = new DataTransfer();
        op4.SetName("OP4");
        op4.Value = new TransferOperation("localhost", "1081", "in4");
        pn.PlaceList.add(op4);

        // -------------------------------------------- Exit lane 1 ------------------------------------------------ //

        DataCarQueue p24 = new DataCarQueue();
        p24.Value.Size = 3;
        p24.SetName("P_o4");
        pn.PlaceList.add(p24);

        DataCar p25 = new DataCar();
        p25.SetName("P_o4Exit");
        pn.PlaceList.add(p25);

        // -------------------------------------------- Exit lane 2 ------------------------------------------------ //

        DataCarQueue p26 = new DataCarQueue();
        p26.Value.Size = 3;
        p26.SetName("P_o5");
        pn.PlaceList.add(p26);

        DataCar p27 = new DataCar();
        p27.SetName("P_o5Exit");
        pn.PlaceList.add(p27);

        // ---------------------------------------------- Center --------------------------------------------------- //

        DataCarQueue p28 = new DataCarQueue();
        p28.Value.Size = 3;
        p28.SetName("P_I2");
        pn.PlaceList.add(p28);

        DataCarQueue p29 = new DataCarQueue();
        p29.Value.Size = 3;
        p29.SetName("P_I3");
        pn.PlaceList.add(p29);




        // ********************************************************************************************************* //
        // ******************************************* Transitions ************************************************* //
        // ********************************************************************************************************* //


        // --------------------------------------------------------------------------------------------------------- //
        // ------------------------------------------ Intersection 1 ----------------------------------------------- //
        // --------------------------------------------------------------------------------------------------------- //

        // ---------------------------------------------- Lane 1 --------------------------------------------------- //

        // T1
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "T_u1";
        t1.InputPlaceName.add("P_a1");
        t1.InputPlaceName.add("P_x1");

        Condition T1Ct1 = new Condition(t1, "P_a1", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "P_x1", TransitionCondition.CanAddCars);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(t1, "P_a1", TransitionOperation.AddElement, "P_x1"));
        t1.GuardMappingList.add(grdT1);

        Condition T1Ct3 = new Condition(t1, "P_a1", TransitionCondition.NotNull);
        Condition T1Ct4 = new Condition(t1, "P_x1", TransitionCondition.CanNotAddCars);
        T1Ct3.SetNextCondition(LogicConnector.AND, T1Ct4);

        GuardMapping grdT1_1 = new GuardMapping();
        grdT1_1.condition = T1Ct3;
        grdT1_1.Activations.add(new Activation(t1, "full", TransitionOperation.SendOverNetwork, "OP1"));
        grdT1_1.Activations.add(new Activation(t1, "P_a1", TransitionOperation.Move, "P_a1"));
        t1.GuardMappingList.add(grdT1_1);

        t1.Delay = 0;
        pn.Transitions.add(t1);

        // T2
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "T_e1";
        t2.InputPlaceName.add("P_x1");
        t2.InputPlaceName.add("P_TL1");

        Condition T2Ct1 = new Condition(t2, "P_TL1", TransitionCondition.Equal, "green");
        Condition T2Ct2 = new Condition(t2, "P_x1", TransitionCondition.HaveCar);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        grdT2.Activations.add(new Activation(t2, "P_x1", TransitionOperation.PopElementWithoutTarget, "P_b1"));
        grdT2.Activations.add(new Activation(t2, "P_TL1", TransitionOperation.Move, "P_TL1"));

        t2.GuardMappingList.add(grdT2);

		t2.Delay = 0;
        pn.Transitions.add(t2);

        // T3
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "T_i1";
        t3.InputPlaceName.add("P_b1");
        t3.InputPlaceName.add("P_I1");

        Condition T3Ct1 = new Condition(t3, "P_b1", TransitionCondition.NotNull);
        Condition T3Ct2 = new Condition(t3, "P_I1", TransitionCondition.CanAddCars);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(t3, "P_b1", TransitionOperation.AddElement, "P_I1"));
        t3.GuardMappingList.add(grdT3);

        t3.Delay = 0;
        pn.Transitions.add(t3);

        // ---------------------------------------------- Lane 2 --------------------------------------------------- //

        // T4
        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "T_u2";
        t4.InputPlaceName.add("P_a2");
        t4.InputPlaceName.add("P_x2");

        Condition T4Ct1 = new Condition(t4, "P_a2", TransitionCondition.NotNull);
        Condition T4Ct2 = new Condition(t4, "P_x2", TransitionCondition.CanAddCars);
        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;
        grdT4.Activations.add(new Activation(t4, "P_a2", TransitionOperation.AddElement, "P_x2"));
        t4.GuardMappingList.add(grdT4);

        Condition T4Ct3 = new Condition(t4, "P_a2", TransitionCondition.NotNull);
        Condition T4Ct4 = new Condition(t4, "P_x2", TransitionCondition.CanNotAddCars);
        T4Ct3.SetNextCondition(LogicConnector.AND, T4Ct4);

        GuardMapping grdT41 = new GuardMapping();
        grdT41.condition = T4Ct3;
        grdT41.Activations.add(new Activation(t4, "full", TransitionOperation.SendOverNetwork, "OP2"));
        grdT41.Activations.add(new Activation(t4, "P_a2", TransitionOperation.Move, "P_a2"));
        t4.GuardMappingList.add(grdT41);

        t4.Delay = 0;
        pn.Transitions.add(t4);

        // T5
        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "T_e2";
        t5.InputPlaceName.add("P_x2");
        t5.InputPlaceName.add("P_TL2");

        Condition T5Ct1 = new Condition(t5, "P_TL2", TransitionCondition.Equal, "green");
        Condition T5Ct2 = new Condition(t5, "P_x2", TransitionCondition.HaveCar);
        T5Ct1.SetNextCondition(LogicConnector.AND, T5Ct2);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;
        grdT5.Activations.add(new Activation(t5, "P_x2", TransitionOperation.PopElementWithoutTarget, "P_b2"));
        grdT5.Activations.add(new Activation(t5, "P_TL2", TransitionOperation.Move, "P_TL2"));
        t5.GuardMappingList.add(grdT2);

        t5.Delay = 0;
        pn.Transitions.add(t5);

        // T6
        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "T_i2";
        t6.InputPlaceName.add("P_b2");
        t6.InputPlaceName.add("P_I1");

        Condition T6Ct1 = new Condition(t6, "P_b2", TransitionCondition.NotNull);
        Condition T6Ct2 = new Condition(t6, "P_I1", TransitionCondition.CanAddCars);
        T6Ct1.SetNextCondition(LogicConnector.AND, T6Ct2);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;
        grdT6.Activations.add(new Activation(t6, "P_b2", TransitionOperation.AddElement, "P_I1"));
        t6.GuardMappingList.add(grdT6);

        t6.Delay = 0;
        pn.Transitions.add(t6);

        // -------------------------------------------- Exit lane 1 ------------------------------------------------ //

        // T7
        PetriTransition t7 = new PetriTransition(pn);
        t7.TransitionName = "T_g1";
        t7.InputPlaceName.add("P_I1");
        t7.InputPlaceName.add("P_o1");

        Condition T7Ct1 = new Condition(t7, "P_I1", TransitionCondition.HaveCarForMe);
        Condition T7Ct2 = new Condition(t7, "P_o1", TransitionCondition.CanAddCars);
        T7Ct1.SetNextCondition(LogicConnector.AND, T7Ct2);

        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct1;
        grdT7.Activations.add(new Activation(t7, "P_I1", TransitionOperation.PopElementWithTargetToQueue, "P_o1"));
        t7.GuardMappingList.add(grdT7);

        t7.Delay = 0;
        pn.Transitions.add(t7);

        // T8
        PetriTransition t8 = new PetriTransition(pn);
        t8.TransitionName = "T_g1Exit";
        t8.InputPlaceName.add("P_o1");

        Condition T8Ct1 = new Condition(t8, "P_o1", TransitionCondition.HaveCar);

        GuardMapping grdT8 = new GuardMapping();
        grdT8.condition = T8Ct1;
        grdT8.Activations.add(new Activation(t8, "P_o1", TransitionOperation.PopElementWithoutTarget, "P_o1Exit"));
        t8.GuardMappingList.add(grdT8);

        t8.Delay = 0;
        pn.Transitions.add(t8);

        // -------------------------------------------- Exit lane 2 ------------------------------------------------ //

        // T9
        PetriTransition t9 = new PetriTransition(pn);
        t9.TransitionName = "T_g2";
        t9.InputPlaceName.add("P_I1");
        t9.InputPlaceName.add("P_o2");

        Condition T9Ct1 = new Condition(t9, "P_I1", TransitionCondition.HaveCarForMe);
        Condition T9Ct2 = new Condition(t9, "P_o2", TransitionCondition.CanAddCars);
        T9Ct1.SetNextCondition(LogicConnector.AND, T9Ct2);

        GuardMapping grdT9 = new GuardMapping();
        grdT9.condition = T9Ct1;
        grdT9.Activations.add(new Activation(t9, "P_I1", TransitionOperation.PopElementWithTargetToQueue, "P_o2"));
        t9.GuardMappingList.add(grdT9);

        t9.Delay = 0;
        pn.Transitions.add(t9);

        // T10
        PetriTransition t10 = new PetriTransition(pn);
        t10.TransitionName = "T_g2Exit";
        t10.InputPlaceName.add("P_o2");

        Condition T10Ct1 = new Condition(t10, "P_o2", TransitionCondition.HaveCar);

        GuardMapping grdT10 = new GuardMapping();
        grdT10.condition = T10Ct1;
        grdT10.Activations.add(new Activation(t10, "P_o2", TransitionOperation.PopElementWithoutTarget, "P_o2Exit"));
        t10.GuardMappingList.add(grdT10);

        t10.Delay = 0;
        pn.Transitions.add(t10);

        // -------------------------------------------- Exit lane 3 ------------------------------------------------ //

        // T11
        PetriTransition t11 = new PetriTransition(pn);
        t11.TransitionName = "T_g3";
        t11.InputPlaceName.add("P_I1");
        t11.InputPlaceName.add("P_o3");

        Condition T11Ct1 = new Condition(t11, "P_I1", TransitionCondition.HaveCarForMe);
        Condition T11Ct2 = new Condition(t11, "P_o3", TransitionCondition.CanAddCars);
        T11Ct1.SetNextCondition(LogicConnector.AND, T11Ct2);

        GuardMapping grdT11 = new GuardMapping();
        grdT11.condition = T11Ct1;
        grdT11.Activations.add(new Activation(t11, "P_I1", TransitionOperation.PopElementWithTargetToQueue, "P_o3"));
        t11.GuardMappingList.add(grdT11);

        t11.Delay = 0;
        pn.Transitions.add(t11);

        // T12
        PetriTransition t12 = new PetriTransition(pn);
        t12.TransitionName = "T_g3Exit";
        t12.InputPlaceName.add("P_o3");

        Condition T12Ct1 = new Condition(t12, "P_o3", TransitionCondition.HaveCar);

        GuardMapping grdT12 = new GuardMapping();
        grdT12.condition = T12Ct1;
        grdT12.Activations.add(new Activation(t12, "P_o3", TransitionOperation.PopElementWithoutTarget, "P_o3Exit"));
        t12.GuardMappingList.add(grdT12);

        t12.Delay = 0;
        pn.Transitions.add(t12);

        // ---------------------------------------------- Linkage -------------------------------------------------- //

        // T13 - links intersection 1 to intersection 2

        PetriTransition t13 = new PetriTransition(pn);
        t13.TransitionName = "T_link";
        t13.InputPlaceName.add("P_o3Exit");

        Condition T13Ct1 = new Condition(t13, "P_o3Exit", TransitionCondition.NotNull);

        GuardMapping grdT13 = new GuardMapping();
        grdT13.condition = T13Ct1;
        grdT13.Activations.add(new Activation(t13, "P_o3Exit", TransitionOperation.Move, "P_a3"));
        t13.GuardMappingList.add(grdT13);

        t13.Delay = 0;
        pn.Transitions.add(t13);

        // --------------------------------------------------------------------------------------------------------- //
        // ------------------------------------------ Intersection 2 ----------------------------------------------- //
        // --------------------------------------------------------------------------------------------------------- //

        // ---------------------------------------------- Lane 1 --------------------------------------------------- //

        // T14
        PetriTransition t14 = new PetriTransition(pn);
        t14.TransitionName = "T_u3";
        t14.InputPlaceName.add("P_a3");
        t14.InputPlaceName.add("P_x3");

        Condition T14Ct1 = new Condition(t14, "P_a3", TransitionCondition.NotNull);
        Condition T14Ct2 = new Condition(t14, "P_x3", TransitionCondition.CanAddCars);
        T14Ct1.SetNextCondition(LogicConnector.AND, T14Ct2);

        GuardMapping grdT14 = new GuardMapping();
        grdT14.condition = T14Ct1;
        grdT14.Activations.add(new Activation(t14, "P_a3", TransitionOperation.AddElement, "P_x3"));
        t14.GuardMappingList.add(grdT14);

        Condition T14Ct3 = new Condition(t14, "P_a3", TransitionCondition.NotNull);
        Condition T14Ct4 = new Condition(t14, "P_x3", TransitionCondition.CanNotAddCars);
        T14Ct3.SetNextCondition(LogicConnector.AND, T14Ct4);

        GuardMapping grdT141 = new GuardMapping();
        grdT141.condition = T14Ct3;
        grdT141.Activations.add(new Activation(t14, "full", TransitionOperation.SendOverNetwork, "OP3"));
        grdT141.Activations.add(new Activation(t14, "P_a3", TransitionOperation.Move, "P_a3"));
        t14.GuardMappingList.add(grdT141);

        t14.Delay = 0;
        pn.Transitions.add(t14);

        // T15
        PetriTransition t15 = new PetriTransition(pn);
        t15.TransitionName = "T_e3";
        t15.InputPlaceName.add("P_x3");
        t15.InputPlaceName.add("P_TL3");

        Condition T15Ct1 = new Condition(t15, "P_TL3", TransitionCondition.Equal, "green");
        Condition T15Ct2 = new Condition(t15, "P_x3", TransitionCondition.HaveCar);
        T15Ct1.SetNextCondition(LogicConnector.AND, T15Ct2);

        GuardMapping grdT15 = new GuardMapping();
        grdT15.condition = T15Ct1;
        grdT15.Activations.add(new Activation(t15, "P_x3", TransitionOperation.PopElementWithoutTarget, "P_b3"));
        grdT15.Activations.add(new Activation(t15, "P_TL3", TransitionOperation.Move, "P_TL3"));
        t15.GuardMappingList.add(grdT15);

        t15.Delay = 0;
        pn.Transitions.add(t15);

        // T16
        PetriTransition t16 = new PetriTransition(pn);
        t16.TransitionName = "T_i3";
        t16.InputPlaceName.add("P_b3");
        t16.InputPlaceName.add("P_I2");

        Condition T16Ct1 = new Condition(t16, "P_b3", TransitionCondition.NotNull);
        Condition T16Ct2 = new Condition(t16, "P_I2", TransitionCondition.CanAddCars);
        T16Ct1.SetNextCondition(LogicConnector.AND, T16Ct2);

        GuardMapping grdT16 = new GuardMapping();
        grdT16.condition = T16Ct1;
        grdT16.Activations.add(new Activation(t16, "P_b3", TransitionOperation.AddElement, "P_I2"));
        t16.GuardMappingList.add(grdT16);

        t16.Delay = 0;
        pn.Transitions.add(t16);

        // ---------------------------------------------- Lane 2 --------------------------------------------------- //

        // T17
        PetriTransition t17 = new PetriTransition(pn);
        t17.TransitionName = "T_u4";
        t17.InputPlaceName.add("P_a4");
        t17.InputPlaceName.add("P_x4");

        Condition T17Ct1 = new Condition(t17, "P_a4", TransitionCondition.NotNull);
        Condition T17Ct2 = new Condition(t17, "P_x4", TransitionCondition.CanAddCars);
        T17Ct1.SetNextCondition(LogicConnector.AND, T17Ct2);

        GuardMapping grdT17 = new GuardMapping();
        grdT17.condition = T17Ct1;
        grdT17.Activations.add(new Activation(t17, "P_a4", TransitionOperation.AddElement, "P_x4"));
        t17.GuardMappingList.add(grdT17);

        Condition T17Ct3 = new Condition(t17, "P_a4", TransitionCondition.NotNull);
        Condition T17Ct4 = new Condition(t17, "P_x4", TransitionCondition.CanNotAddCars);
        T17Ct3.SetNextCondition(LogicConnector.AND, T17Ct4);

        GuardMapping grdT171 = new GuardMapping();
        grdT171.condition = T17Ct3;
        grdT171.Activations.add(new Activation(t17, "full", TransitionOperation.SendOverNetwork, "OP4"));
        grdT171.Activations.add(new Activation(t17, "P_a4", TransitionOperation.Move, "P_a4"));
        t1.GuardMappingList.add(grdT1);

        t17.Delay = 0;
        pn.Transitions.add(t17);

        // T18
        PetriTransition t18 = new PetriTransition(pn);
        t18.TransitionName = "T_e4";
        t18.InputPlaceName.add("P_x4");
        t18.InputPlaceName.add("P_TL4");

        Condition T18Ct1 = new Condition(t18, "P_TL4", TransitionCondition.Equal, "green");
        Condition T18Ct2 = new Condition(t18, "P_x4", TransitionCondition.HaveCar);
        T6Ct1.SetNextCondition(LogicConnector.AND, T18Ct2);

        GuardMapping grdT18 = new GuardMapping();
        grdT18.condition = T18Ct1;
        grdT18.Activations.add(new Activation(t18, "P_x4", TransitionOperation.PopElementWithoutTarget, "P_b4"));
        grdT18.Activations.add(new Activation(t18, "P_TL4", TransitionOperation.Move, "P_TL4"));
        t18.GuardMappingList.add(grdT18);

        t18.Delay = 0;
        pn.Transitions.add(t18);

        // T19
        PetriTransition t19 = new PetriTransition(pn);
        t19.TransitionName = "T_i4";
        t19.InputPlaceName.add("P_b4");
        t19.InputPlaceName.add("P_I3");

        Condition T19Ct1 = new Condition(t19, "P_b4", TransitionCondition.NotNull);
        Condition T19Ct2 = new Condition(t19, "P_I3", TransitionCondition.CanAddCars);
        T19Ct1.SetNextCondition(LogicConnector.AND, T19Ct2);

        GuardMapping grdT19 = new GuardMapping();
        grdT19.condition = T19Ct1;
        grdT19.Activations.add(new Activation(t19, "P_b4", TransitionOperation.PopElementWithoutTarget, "P_I3"));
        t19.GuardMappingList.add(grdT19);

        t19.Delay = 0;
        pn.Transitions.add(t19);

        // -------------------------------------------- Exit lane 1 ------------------------------------------------ //

        // T20
        PetriTransition t20 = new PetriTransition(pn);
        t20.TransitionName = "T_g4";
        t20.InputPlaceName.add("P_I2");
        t20.InputPlaceName.add("P_o4");

        Condition T20Ct1 = new Condition(t20, "P_I2", TransitionCondition.HaveCarForMe);
        Condition T20Ct2 = new Condition(t20, "P_o4", TransitionCondition.CanAddCars);
        T20Ct1.SetNextCondition(LogicConnector.AND, T20Ct2);

        GuardMapping grdT20 = new GuardMapping();
        grdT20.condition = T20Ct1;
        grdT20.Activations.add(new Activation(t20, "P_I2", TransitionOperation.PopElementWithTargetToQueue, "P_o4"));
        t20.GuardMappingList.add(grdT20);

        t20.Delay = 0;
        pn.Transitions.add(t20);

        // T21
        PetriTransition t21 = new PetriTransition(pn);
        t21.TransitionName = "T_g4Exit";
        t21.InputPlaceName.add("P_o4");

        Condition T21Ct1 = new Condition(t21, "P_o4", TransitionCondition.HaveCar);

        GuardMapping grdT21 = new GuardMapping();
        grdT21.condition = T21Ct1;
        grdT21.Activations.add(new Activation(t21, "P_o4", TransitionOperation.PopElementWithoutTarget, "P_o4Exit"));
        t21.GuardMappingList.add(grdT21);

        t21.Delay = 0;
        pn.Transitions.add(t21);

        // -------------------------------------------- Exit lane 2 ------------------------------------------------ //

        // T22
        PetriTransition t22 = new PetriTransition(pn);
        t22.TransitionName = "T_g5";
        t22.InputPlaceName.add("P_I3");
        t22.InputPlaceName.add("P_o5");

        Condition T22Ct1 = new Condition(t22, "P_I3", TransitionCondition.HaveCarForMe);
        Condition T22Ct2 = new Condition(t22, "P_o5", TransitionCondition.CanAddCars);
        T22Ct1.SetNextCondition(LogicConnector.AND, T22Ct2);

        GuardMapping grdT22 = new GuardMapping();
        grdT22.condition = T22Ct1;
        grdT22.Activations.add(new Activation(t22, "P_I3", TransitionOperation.PopElementWithTargetToQueue, "P_o5"));
        t22.GuardMappingList.add(grdT22);

        t22.Delay = 0;
        pn.Transitions.add(t22);

        // T23
        PetriTransition t23 = new PetriTransition(pn);
        t23.TransitionName = "T_g4Exit";
        t23.InputPlaceName.add("P_o5");

        Condition T23Ct1 = new Condition(t23, "P_o5", TransitionCondition.HaveCar);

        GuardMapping grdT23 = new GuardMapping();
        grdT23.condition = T23Ct1;
        grdT23.Activations.add(new Activation(t23, "P_o5", TransitionOperation.PopElementWithoutTarget, "P_o5Exit"));
        t23.GuardMappingList.add(grdT23);

        t23.Delay = 0;
        pn.Transitions.add(t23);

        // ---------------------------------------------- Center --------------------------------------------------- //

        // T24
        PetriTransition t24 = new PetriTransition(pn);
        t24.TransitionName = "T_c1";
        t24.InputPlaceName.add("P_I2");
        t24.InputPlaceName.add("P_I3");

        Condition T24Ct1 = new Condition(t24, "P_I2", TransitionCondition.HaveCarForMe);
        Condition T24Ct2 = new Condition(t24, "P_I3", TransitionCondition.CanAddCars);
        T24Ct1.SetNextCondition(LogicConnector.AND, T24Ct2);

        GuardMapping grdT24 = new GuardMapping();
        grdT24.condition = T24Ct1;
        grdT24.Activations.add(new Activation(t24, "P_I2", TransitionOperation.PopElementWithTargetToQueue, "P_I3"));
        t24.GuardMappingList.add(grdT24);

        t24.Delay = 0;
        pn.Transitions.add(t24);

        // T25
        PetriTransition t25 = new PetriTransition(pn);
        t25.TransitionName = "T_c2";
        t25.InputPlaceName.add("P_I3");
        t25.InputPlaceName.add("P_I2");

        Condition T25Ct1 = new Condition(t25, "P_I3", TransitionCondition.HaveCarForMe);
        Condition T25Ct2 = new Condition(t25, "P_I2", TransitionCondition.CanAddCars);
        T25Ct1.SetNextCondition(LogicConnector.AND, T25Ct2);

        GuardMapping grdT25 = new GuardMapping();
        grdT25.condition = T25Ct1;
        grdT25.Activations.add(new Activation(t25, "P_I3", TransitionOperation.PopElementWithTargetToQueue, "P_I2"));
        t25.GuardMappingList.add(grdT25);

        t25.Delay = 0;
        pn.Transitions.add(t25);
    }
}
