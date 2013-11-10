package ch.ethz.pa.test.frameworkTests;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import soot.Modifier;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.VoidType;
import soot.jimple.Jimple;
import soot.jimple.JimpleBody;
import soot.toolkits.graph.BriefUnitGraph;
import ch.ethz.pa.Analysis;

public class AnalysisTest {
	
	private final JimpleBody simpleBody = createSimpleBody();

	/**
	 * Provides a body that consists of only a return statement.
	 * @return
	 */
	private static JimpleBody createSimpleBody() {
		// setup global object
		Scene.v().loadClassAndSupport("java.lang.Object");
		
		// setup class
		SootClass sClass = new SootClass("MyClass", Modifier.PUBLIC);
		sClass.setSuperclass(Scene.v().getSootClass("java.lang.Object"));
		Scene.v().addClass(sClass);
		
		// setup method
		SootMethod method = new SootMethod("myMethod",                 
		        Arrays.asList(new Type[] {}), VoidType.v(), Modifier.PUBLIC | Modifier.STATIC);
		sClass.addMethod(method);
		
		// setup body
		JimpleBody body = Jimple.v().newBody(method);
	    method.setActiveBody(body);
	    
	    // add return stmt
	    body.getUnits().add(Jimple.v().newReturnVoidStmt());
		return body;
	}
	
	Analysis analysis;
	
	@Before
	public void setup() {
		analysis = new Analysis(new BriefUnitGraph(simpleBody));
	}
	
	@Test
	public void testErrorFeedback() {
		List<String> problems = analysis.getProblems();
		Assert.assertNotNull("expected a list", problems);
		Assert.assertEquals("expected an empty list", 0, problems.size());
	} 

}
