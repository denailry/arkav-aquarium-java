import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Runner {
	private static final String TESTING_COMMAND = "java -cp \"bin;lib/junit-4.12.jar;lib/hamcrest-core-1.3.jar\" Tester";
	private static final String[] TEST_CLASSES = {
			"AquariumTest",
			"ElementTest",
			"EntityTest",
			"FishTest",
			"LinkedListTest",
			"TickTest",
			"CoinTest",
			"GuppyTest",
			"PiranhaTest",
			"SnailTest"
		};
	private static final String MAIN_COMMAND = "java -cp \"bin;lib/junit-4.12.jar;lib/hamcrest-core-1.3.jar\" Main";

	public static void main(String[] args) throws IOException {
		Runtime rt = Runtime.getRuntime();

		ArrayList<Integer> failureIndices = new ArrayList<Integer>();
		int iArgument = 0;
		while (iArgument < TEST_CLASSES.length) {
			Process proc = rt.exec(TESTING_COMMAND + " " + TEST_CLASSES[iArgument]);
			String line;
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			Boolean accepted = false;
			while (!accepted && (line = stdInput.readLine()) != null) {
				System.out.println(line);
				accepted = line.contains("ACCEPTED");
			}
			System.out.println();
			if (!accepted) {
				failureIndices.add(Integer.valueOf(iArgument));
			}
		    iArgument++;
		}

		if (failureIndices.size() != 0) {
			System.out.println("There are " + failureIndices.size() + " test FAILURES: ");
			for (Integer index : failureIndices) {
				System.out.println("- " + TEST_CLASSES[index]);
			}
		} else {
			System.out.println("All test are ACCEPTED!");
			Process proc = rt.exec(MAIN_COMMAND);
		}
	}
}