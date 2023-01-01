import java.util.*;

public class Plant {

    private volatile boolean heaterOn = false;

    private volatile double gasCmd = 0.0;

    private int tickCounter = 0;

    private final long period;

    private final RoomModel room;

    private final Scenario scenario;

    private final HeaterTank tank;

    /* for logs */

    ArrayList<Double> heaterWaterTempLog = new ArrayList<>();

    ArrayList<Double> roomTempLog = new ArrayList<>();

    ArrayList<Double> waterHeaterCmdLog = new ArrayList<>();

    ArrayList<Double> heatOnCmdLog = new ArrayList<>();

    int heatOnCounter = 0;

    int continuousHeatOnMax = 0;

    int continuousHeatOnCurrent = 0;

    double tankGasCommandSum = 0.0;

    public Plant(long simPeriod, Scenario scene) {

        this.period = simPeriod;

        room = new RoomModel();

        tank = new HeaterTank();

        scenario = scene; }

    public void setHeatingOn(boolean heaterOn) {

        this.heaterOn = heaterOn; }

    public void setHeaterGasCmd(double cmd) {

        gasCmd = cmd; }

    public double getRoomTemperature() {

        return room.getCurrentTemperature(); }

    public double heatingOnRatio() {

        return ((double) heatOnCounter / (double) tickCounter); }

    public double gasConsumption() {

        return tankGasCommandSum; }

    public int maxContinuousHeaterOn() {

        return continuousHeatOnMax; }

    public void start() {

        Timer myTimer = new Timer();

        TimerTask task = new TimerTask() {

            @Override

            public void run() {

                if (tickCounter < scenario.getScenarioLength()) {

                    tank.updateSystem(heaterOn, gasCmd);

                    room.updateModel(heaterOn, tank.getHotWaterTemperature(), scenario.getWindowOpen(tickCounter),

                            scenario.getOutSideTemperature(tickCounter));

                    makeLogs();

                    tickCounter++; } else {

                    myTimer.cancel();

                    myTimer.purge(); }

            }

        };

        myTimer.scheduleAtFixedRate(task, period, period); }

    private void makeLogs() {

        heaterWaterTempLog.add(tank.getHotWaterTemperature());

        roomTempLog.add(room.getCurrentTemperature());

        waterHeaterCmdLog.add(gasCmd);

        heatOnCmdLog.add(heaterOn ? 1.0 : 0.0);

        heatOnCounter += (heaterOn ? 1.0 : 0.0);

        if (heaterOn) {

            continuousHeatOnCurrent++;

        } else if (continuousHeatOnCurrent > 0) {

            if (continuousHeatOnCurrent > continuousHeatOnMax) {

                continuousHeatOnMax = continuousHeatOnCurrent; }

            continuousHeatOnCurrent = 0; }

        tankGasCommandSum += Math.max(gasCmd, 0.0);

    }

    public Double getTankWaterTemperature() {

        return tank.getHotWaterTemperature(); }

    public Map<String, List<Double>> getTemperatureLogs() {

        HashMap<String, List<Double>> logMap = new HashMap<>();

        logMap.put("tankTemp", heaterWaterTempLog);

        logMap.put("roomTemp", roomTempLog);

        return logMap; }

    public Map<String, List<Double>> getCommandLogs() {

        HashMap<String, List<Double>> logMap = new HashMap<>();

        logMap.put("waterCmd", waterHeaterCmdLog);

        logMap.put("heaterOn", heatOnCmdLog);

        return logMap; }

}