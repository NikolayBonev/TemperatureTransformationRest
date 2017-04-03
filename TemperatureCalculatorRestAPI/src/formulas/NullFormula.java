package formulas;

public class NullFormula implements TemperatureFormula{

	@Override
	public double execute(double temperature) {
		return 0;
	}

}
