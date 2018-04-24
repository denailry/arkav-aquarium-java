import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class Tester {
	public static void main(String[] args) {
		try {
			System.out.println("TESTING " + args[0] + ".class");
			System.out.println(".");
	      	Result result = JUnitCore.runClasses(Class.forName(args[0]));
	      	List<Failure> failures = result.getFailures();

	      	FileOutputStream f;
      		try {
  		      	f = new FileOutputStream("log/log.txt");
				System.setErr(new PrintStream(f));
      		} catch (FileNotFoundException e) {
      			f = null;
      		}

	      	for (Failure failure : failures) {
	      		if (f != null) {
	      			System.err.println(failure.getTrace());
	      		}
	      		System.out.println("FAILURE: " + failure.getMessage());
	      	}

	      	System.out.println(".");
	      	System.out.println(".");
	      	System.out.println(".");
	      	System.out.println("RUNTIME: " + result.getRunTime() + " ms");
	      	System.out.println("SUCCESS: " + (result.getRunCount() - result.getFailureCount()) + 
	      		", FAILED: " + result.getFailureCount() +
	      		", IGNORE: " + result.getIgnoreCount());
	      	if (result.wasSuccessful()) {
	      		System.out.println(args[0] + " is ACCEPTED!");
	      	} else {
	      		System.out.println(args[0] + " is FAILED!");
	      	}
	      	System.out.println();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}