package formulas;

public class CelsiusToFahrenheitFormula implements TemperatureFormula {

	@Override
	public double execute(double temperature) {
		double fahrenheit = (temperature*1.8) + 32;
		
		return fahrenheit;
	}

}
