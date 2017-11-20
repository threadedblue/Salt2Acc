package iox.hla.ii;

import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import iox.hla.core.federatecore.FederatecoreFactory;
import iox.hla.core.federatecore.JoinInteraction;
import littleints.theseints.Int1;
import littleints.theseints.TheseintsFactory;

public class ReceiveInjection extends PublishImpl {

	private static final Logger log = LogManager.getLogger(ReceiveInjection.class);


	public ReceiveInjection(PubSubFederate federate) {
		super();
		this.federate = federate;
	}

	@Override
	public Queue<EObject> getPreSynchInteractions() {
		super.getPreSynchInteractions();
		return publications;
	}
}
