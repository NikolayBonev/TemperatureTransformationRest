package formulas;

public class FormulaFactory {
	public TemperatureFormula getFormula(String mesureOld, String mesureNew) {
		TemperatureFormula formula = null;
		
		
		if(mesureOld.equalsIgnoreCase("celsius")) {
			if(mesureNew.equalsIgnoreCase("fahrenheit")) {
				formula = new CelsiusToFahrenheitFormula();
			}
		}else if(mesureOld.equalsIgnoreCase("fahrenheit")) {
			if(mesureNew.equalsIgnoreCase("celsius")) {
				formula = new FahrenheitToCelsiusFormula();
			}
		}
		
		if(formula == null) {
			formula = new NullFormula();
		}
		
		return formula;
	}
}
