package iox.hla.ii;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import iox.hla.core.federatecore.FederatecorePackage;
import iox.sds4emf.Registrar;
import littleints.theseints.TheseintsPackage;
import littleints.thoseInts.ThoseIntsPackage;

public class ReceiveMain {

	private static final Logger log = LogManager.getLogger(ReceiveMain.class);

	public static String configFile;

	public static void main(String[] args) {
		if (args.length != 1) {
			log.error("Command line argument for config file not specified. Using default " + PubSubFederate.DEFAULT_CONFIG_FILE);
			configFile = PubSubFederate.DEFAULT_CONFIG_FILE;
		} else {
			configFile = args[0];
		}
		log.debug("configFile=" + configFile);
		try {
			// We have to register all the packages we intent to use.
			Registrar.registerPackage(TheseintsPackage.eNS_URI, TheseintsPackage.eINSTANCE);
			Registrar.registerPackage(ThoseIntsPackage.eNS_URI, ThoseIntsPackage.eINSTANCE);
			PubSubFederate app = new PubSubFederate();
			app.loadConfiguration(args[0]);
			
			ReceiveInjection inj = new ReceiveInjection(app);
			app.setInterObjectInjection(inj);
			// Set the injection and reception here.
			ReceiveReception recp = new ReceiveReception();
			app.setInterObjectReception(recp);
			ReceiveHook hook = new ReceiveHook();
			app.setTimeStepHook(hook);
			app.init();
			app.run();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
