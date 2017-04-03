package core;

public class Temperature {

	private double temperature;
	private String mesure;
	
	public Temperature(double temperature, String mesure){
		this.temperature = temperature;
		this.mesure = mesure;
	}
	
	public double getTemperature() {
		return this.temperature;
	}
	
	public String getMesure() {
		return this.mesure;
	}
	
}
