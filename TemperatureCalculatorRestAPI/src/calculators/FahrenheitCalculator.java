package calculators;

import core.*;
import formulas.TemperatureFormula;

public class FahrenheitCalculator implements Calculator {

	@Override
	public Temperature calculate(Temperature temperatureObject, TemperatureFormula formula) {
		Double tranformedTemperature = formula.execute(temperatureObject.getTemperature());
		
		return new Temperature(tranformedTemperature, "Fahrenheit");
	}

}
