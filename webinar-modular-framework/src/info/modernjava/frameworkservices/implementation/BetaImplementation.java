package info.modernjava.frameworkservices.implementation;

import static info.modernjava.util.Util.log;

import info.modernjava.frameworkservices.api.FrameworkServices;

public class BetaImplementation implements FrameworkServices {

	public BetaImplementation(GammaImplementation gi) {
	}
	
	@Override
	public void serviceOne() {
		log("BetaImplementation: serviceOne");
	}

	@Override
	public void serviceTwo() {
		log("BetaImplementation: serviceTwo");
	}

	@Override
	public void serviceThree() {
		log("BetaImplementation: serviceThree");
	}
}
