package info.modernjava.frameworkservices.api;

import info.modernjava.frameworkservices.implementation.AlphaImplementation;

public interface ServicesFactory {

	static FrameworkServices create() {
		return new AlphaImplementation();
	}
}
