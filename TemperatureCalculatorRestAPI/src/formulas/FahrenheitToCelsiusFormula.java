package formulas;

public class FahrenheitToCelsiusFormula implements TemperatureFormula{

	@Override
	public double execute(double temperature) {
		double celsius = (temperature - 32) / 1.8;
		
		return celsius;
	}

}
