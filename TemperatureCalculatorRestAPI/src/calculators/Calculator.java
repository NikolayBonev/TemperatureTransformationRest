package calculators;

import core.*;
import formulas.TemperatureFormula;

public interface Calculator {
	public abstract Temperature calculate(Temperature temperatureObject, TemperatureFormula formula);
}
