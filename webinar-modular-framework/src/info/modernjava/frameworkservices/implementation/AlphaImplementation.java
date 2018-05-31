package info.modernjava.frameworkservices.implementation;

import static info.modernjava.util.Util.log;

import info.modernjava.frameworkservices.api.FrameworkServices;

public class AlphaImplementation implements FrameworkServices {

	public AlphaImplementation() {
		// Do something
	}
	
	@Override
	public void serviceOne() {
		log("AlphaImplementation: serviceOne");
	}

	@Override
	public void serviceTwo() {
		log("AlphaImplementation: serviceTwo");
	}

	@Override
	public void serviceThree() {
		log("AlphaImplementation: serviceThree");
	}

	public void methodOne() {
		log("AlphaImplementation: methodOne");
	}
}
