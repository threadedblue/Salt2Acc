package iox.hla.ii;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Test;

import iox.sds4emf.Registrar;
import littleints.theseints.TheseintsPackage;
import littleints.thoseInts.ThoseIntsPackage;

public class ReceiveReceptionTest {

	private static final Logger log = LogManager.getLogger(ReceiveReceptionTest.class);
	ReceiveReception sut = new ReceiveReception();

	@Test
	public void testReceiveInteractionDoubleStringMapOfStringString() {
		String interactionName = "Int1";
		Registrar.registerPackage(TheseintsPackage.eNS_URI, TheseintsPackage.eINSTANCE);
		Registrar.registerPackage(ThoseIntsPackage.eNS_URI, ThoseIntsPackage.eINSTANCE);
		EPackage ePackage1 = EPackage.Registry.INSTANCE.getEPackage(TheseintsPackage.eNS_URI);
		EPackage ePackage2 = EPackage.Registry.INSTANCE.getEPackage(ThoseIntsPackage.eNS_URI);
		EList<EClassifier> eClassifiers1 = ePackage1.getEClassifiers();
		EList<EClassifier> eClassifiers2 = ePackage2.getEClassifiers();
		EClassifier eClassifier = ePackage1.getEClassifier(interactionName);
		EClass eClass=(EClass)eClassifier;		
		EAttribute eAttribute = (EAttribute)eClass.getEStructuralFeature("intVal");
		EObject eObject = EcoreUtil.create(eClass);
		EDataType eDataType = eAttribute.getEAttributeType();
		String from = EcoreUtil.convertToString(eDataType, new Integer(123));
		byte[] bytes = from.getBytes();
		Object obj = EcoreUtil.createFromString(eDataType, new String(bytes));
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("boolVal", new Boolean(true).toString());
		parameters.put("intVal", new Integer(123).toString());
		parameters.put("strVal", "ABC");
		assertNotNull(eObject);
	}

}
