package iox.hla.ii;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReceiveHook extends TimeStepHookImpl {

	private static final Logger log = LogManager.getLogger(ReceiveHook.class);

	@Override
	public void afterReadytoPopulate() {
		log.info("afterReadytoPopulate");
	}

	@Override
	public void afterReadytoRun() {
		log.info("afterReadytoRun");
	}

	@Override
	public void afterAdvanceLogicalTime() {
		log.info("afterAdvanceLogicalTime");
	}

	@Override
	public void beforeReadytoPopulate() {
		log.info("beforeReadytoPopulate");
	}

	@Override
	public void beforeReadytoRun() {
		log.info("beforeReadytoRun");
	}

	@Override
	public void beforeAdvanceLogicalTime() {
		log.info("beforeAdvanceLogicalTime");
	}

}
