package calculators;

import core.Temperature;
import formulas.*;

public class TemperatureCalculatorFactory {
	private FormulaFactory formulaFactory = new FormulaFactory();
	
	public Temperature calculate(double temperature, String mesure){
		Calculator calculator = null;
		
		if(mesure.equalsIgnoreCase("celsius")){
			calculator = new CelsiusCalculator();
			
			return calculator.calculate(new Temperature(temperature, mesure), formulaFactory.getFormula("fahrenheit", "celsius"));
		}else if(mesure.equalsIgnoreCase("fahrenheit")){
			calculator = new FahrenheitCalculator();
			
			return calculator.calculate(new Temperature(temperature, mesure), formulaFactory.getFormula("celsius", "fahrenheit"));
		}else{
			return new Temperature(0, "NaN");
		}
		
	}
}
