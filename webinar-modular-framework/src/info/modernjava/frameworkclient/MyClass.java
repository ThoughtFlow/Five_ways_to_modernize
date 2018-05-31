package info.modernjava.frameworkclient;

import info.modernjava.frameworkservices.api.FrameworkServices;
import info.modernjava.frameworkservices.api.ServicesFactory;
import info.modernjava.frameworkservices.implementation.AlphaImplementation;
import info.modernjava.frameworkservices.implementation.BetaImplementation;
import info.modernjava.frameworkservices.implementation.GammaImplementation;

import static info.modernjava.util.Util.log;

import java.lang.reflect.InvocationTargetException;

public class MyClass {

	public static void main(String[] args) {
		// This is the expect way to work with the framework services
		FrameworkServices services = ServicesFactory.create();
		services.serviceOne();

		// Clients should not do this but they can and you can't stop them (without JPMS)
		if (services instanceof AlphaImplementation) {
			log("Services is an instance of AlphaImplentation");
			AlphaImplementation ai = (AlphaImplementation) services;
			ai.methodOne();
		}
		
		// Clients should not do this but they can and you can't stop them (without JPMS)
		BetaImplementation beta = new BetaImplementation(new GammaImplementation());
		beta.serviceTwo();
		
		// Clients should not do this but they can and you can't stop them (without JPMS)
		try {
			@SuppressWarnings("unchecked")
			Class<GammaImplementation> clazz = (Class<GammaImplementation>) Class.forName("info.modernjava.frameworkservices.implementation.GammaImplementation");
			GammaImplementation gi = clazz.getDeclaredConstructor().newInstance();
			gi.serviceThree();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
