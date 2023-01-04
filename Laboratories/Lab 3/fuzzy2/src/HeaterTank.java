public class HeaterTank {

    private static final double pipeWaterTemperature = 7;

    private static final double maxWaterTemperature = 75;

    private static final double startTemperature = 23;

    private static final double theoreticalRoomTemp = 23;

    double currentWaterTemperature;

    public HeaterTank() {

        currentWaterTemperature = startTemperature; }

    public void updateSystem(boolean heaterOn, double gasCmd){

        gasCmd = (gasCmd < 0.0) ? 0.0 : (Math.min(gasCmd, 1.0));

        currentWaterTemperature += -(currentWaterTemperature - pipeWaterTemperature) * 0.1 * ((heaterOn) ? 1.0 : 0.0) +

                (maxWaterTemperature - currentWaterTemperature) * 0.4 * gasCmd

                - (currentWaterTemperature - theoreticalRoomTemp) * 0.005; }

    public double getHotWaterTemperature() {

        return currentWaterTemperature; }

}
