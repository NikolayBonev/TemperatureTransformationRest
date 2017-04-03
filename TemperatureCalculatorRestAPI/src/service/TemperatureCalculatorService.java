package service;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import calculators.*;
import core.Temperature;

@Path("/temperatureCalculator")
public class TemperatureCalculatorService {
	
	@GET
	@Path("/query")
	@Produces(MediaType.APPLICATION_JSON)
	public Temperature transformTemperature(@QueryParam("mesure") String mesure, @QueryParam("value") double temperature) {
		TemperatureCalculatorFactory calculator = new TemperatureCalculatorFactory();
		
		return calculator.calculate(temperature, mesure);
	}
}
