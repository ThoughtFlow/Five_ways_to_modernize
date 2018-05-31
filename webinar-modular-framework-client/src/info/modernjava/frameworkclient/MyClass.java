package info.modernjava.frameworkclient;

import info.modernjava.frameworkservices.api.FrameworkServices;
import info.modernjava.frameworkservices.api.ServicesFactory;

public class MyClass {

	public static void main(String[] args) {
		// This is the expect way to work with the framework services
		FrameworkServices services = ServicesFactory.create();
		services.serviceOne();

		// Can no longer access classed hidden by frameworkservices - compilation error!
//		if (services instanceof AlphaImplementation) {
//			log("Services is an instance of AlphaImplentation");
//			AlphaImplementation ai = (AlphaImplementation) services;
//			ai.methodOne();
//		}
		
		// Can no longer access classed hidden by frameworkservices - compilation error!
//		BetaImplementation beta = new BetaImplementation(new GammaImplementation());
//		beta.serviceTwo();
		
		// Can no longer access classed hidden by frameworkservices - compilation error!
//		try {
//			@SuppressWarnings("unchecked")
//			Class<GammaImplementation> clazz = (Class<GammaImplementation>) Class.forName("info.modernjava.frameworkservices.implementation.GammaImplementation");
//			GammaImplementation gi = clazz.getDeclaredConstructor().newInstance();
//			gi.serviceThree();
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//				| InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}
}
