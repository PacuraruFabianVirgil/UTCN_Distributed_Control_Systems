import View.MainView;
import Main.FuzzyPVizualzer;
import Main.Plotter;

import java.util.List;

public class SimpleMain {

    private static final int SIM_PERIOD = 10;

    public static void main(String[] args) {

        Scenario scenario = Scenario.winterDay();

        Plant plant = new Plant(SIM_PERIOD, scenario);

        HeaterTankControllerComponent tankController = new HeaterTankControllerComponent(plant, SIM_PERIOD);

        RoomTemperatureControllerComponent roomController = new RoomTemperatureControllerComponent(plant, SIM_PERIOD);

        OutsideReferenceCalculatorComponent outsideCalculator = new OutsideReferenceCalculatorComponent(plant, tankController, SIM_PERIOD);

        roomController.start();

        tankController.start();

        outsideCalculator.start();

        plant.start();

        double waterRefTemp = 48.0;

        double roomTemperature = 24.0;

        for (int i = 0; i < scenario.getScenarioLength(); i++) {

            outsideCalculator.setOutsideTemp(waterRefTemp);

            tankController.setTankWaterTemp(plant.getTankWaterTemperature());

            roomController.setInput(roomTemperature, plant.getRoomTemperature());

            try {Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace(); }
        }

        tankController.stop();

        roomController.stop();

        outsideCalculator.stop();

        MainView windowTankController = FuzzyPVizualzer.visualize(tankController.getNet(), tankController.getRecorder());

        MainView windowThermostat = FuzzyPVizualzer.visualize(roomController.getNet(), roomController.getRecorder());

        MainView windowOutsideReference = FuzzyPVizualzer.visualize(outsideCalculator.getNet(), outsideCalculator.getRecorder());

        Plotter plotterTemperatureLog = new Plotter(plant.getTemperatureLogs());

        Plotter plotterCommandLog = new Plotter(plant.getCommandLogs());

        windowTankController.addInteractivePanel("TempLogs", plotterTemperatureLog.makeInteractivePlot());

        windowThermostat.addInteractivePanel("TempLogs", plotterTemperatureLog.makeInteractivePlot());

        windowOutsideReference.addInteractivePanel("TempLogs", plotterTemperatureLog.makeInteractivePlot());

        windowTankController.addInteractivePanel("CommandLogs", plotterCommandLog.makeInteractivePlot());

        windowThermostat.addInteractivePanel("CommandLogs", plotterCommandLog.makeInteractivePlot());

        windowOutsideReference.addInteractivePanel("CommandLogs", plotterCommandLog.makeInteractivePlot());

        double[] tankTempStats = SimpleMain.calcStatistics(plant.getTemperatureLogs().get("tankTemp"));

        double[] roomTempStats = SimpleMain.calcStatistics(plant.getTemperatureLogs().get("roomTemp"));

        System.out.println("max tank temp :" + tankTempStats[0]);

        System.out.println("min tank temp :" + tankTempStats[1]);

        System.out.println("avg tank temp :" + tankTempStats[2]);

        System.out.println("max room temp :" + roomTempStats[0]);

        System.out.println("min room temp :" + roomTempStats[1]);

        System.out.println("avg room temp :" + roomTempStats[2]);

        System.out.println("heater on ratio:" + plant.heatingOnRatio());

        System.out.println("max nr of minutes continuous heating on:" + plant.maxContinuousHeaterOn());

        System.out.println("all consumption ::" + plant.gasConsumption());

        System.out.println("avg consumption in a min ::" + plant.gasConsumption() / scenario.getScenarioLength()); }

    public static double[] calcStatistics(List<Double> list) {

        double min = 1000.0;

        double max = 0.0;

        double sum = 0.0;

        for (Double d : list) {

            min = (min > d) ? d : min;

            max = (max < d) ? d : max;

            sum += d; }

        return new double[] { max, min, sum / list.size() }; }

}
