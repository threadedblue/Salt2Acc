package iox.hla.ii;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.accumulo.core.client.AccumuloException;
import org.apache.accumulo.core.client.AccumuloSecurityException;
import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.TableExistsException;
import org.apache.accumulo.core.client.TableNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import accumulo.provider.AccumuloProvider;
import accumulo.provider.impl.AccumuloProviderImpl;
import iox.e2a2e.PutEMF;
import iox.e2a2e.impl.PutEMFImpl;

public class ReceiveReception extends SubscribeImpl {

	private static final Logger log = LogManager.getLogger(ReceiveReception.class);

	PutEMF putEMF;

	public ReceiveReception() {
		Properties properties = new Properties();
		File file = new File("conf/accumulo.properties");
		try {
			InputStream is = new FileInputStream(file);
			log.error("What? " + is);
			Connector conn;
			properties.load(is);
			AccumuloProvider provider = new AccumuloProviderImpl(properties);
			conn = provider.getConnection();
			putEMF = new PutEMFImpl(conn);
		} catch (IOException e) {
			log.error("", e);
		}

	}

	// We disposition interactions sent elsewhere in the federation. Here we simply
	// log.
	public EObject receiveInteraction(Double timeStep, EObject interaction) {
		log.info(String.format("recd: time=%f interaction=%s", timeStep, interaction));
		try {
			putEMF.putDBObject(timeStep.toString(), interaction);
		} catch (TableNotFoundException | AccumuloException | AccumuloSecurityException | TableExistsException e) {
			log.error(e);
		}
		return interaction;
	}

	// Same thing for objects.
	@Override
	public EObject receiveObject(Double timeStep, EObject object) {
		log.info(String.format("recd: time=%f object=%s", timeStep, object));
		receiveInteraction(timeStep, object);
		return object;
	}

	@Override
	public void receiveMessage(Double timeStep, EObject message) {
		log.info(String.format("recd: time=%f object=%s", timeStep, message));
		receiveInteraction(timeStep, message);
	}
}
