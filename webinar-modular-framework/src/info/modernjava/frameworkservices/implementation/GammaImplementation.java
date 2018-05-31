package info.modernjava.frameworkservices.implementation;

import static info.modernjava.util.Util.log;

import info.modernjava.frameworkservices.api.FrameworkServices;

public class GammaImplementation implements FrameworkServices {

	@Override
	public void serviceOne() {
		log("GammaImplementation: serviceOne");
	}

	@Override
	public void serviceTwo() {
		log("GammaImplementation: serviceTwo");
	}

	@Override
	public void serviceThree() {
		log("GammaImplementation: serviceThree");
	}
	
	public void methodFour() {
		log("GammaImplementation: methodFour");
	}
}
