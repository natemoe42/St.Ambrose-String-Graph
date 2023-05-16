import java.util.ArrayList;
import java.util.Arrays;

/**
 * Used with the tester program.
 * Holds results of a single result.
 */
public class TestResult {

    private String message;
    private String expectedMsg;
    private String receivedMsg;
    private boolean passed;

    public TestResult(String message, String expected, String received) {
        this.message = message;
        this.expectedMsg = expected;
        this.receivedMsg = received;
        passed = expected.equals(received);
    }

    public TestResult(String message, String[] expected, String[] received) {
        this.message = message;
        expectedMsg = Arrays.toString(expected);
        receivedMsg = Arrays.toString(received);
        passed = Arrays.equals(expected, received);
    }

    public TestResult(String message, String[][] expectedEdges, String[][] receivedEdges) {
        this.message = message;
        if (expectedEdges.length != receivedEdges.length) {
            expectedMsg = "2D array has length " + expectedEdges.length;
            receivedMsg = "2D array has length " + receivedEdges.length;
            passed = false;
            return;
        }

        for (int r = 0; r < expectedEdges.length; r++) {
            if (expectedEdges[r].length != receivedEdges[r].length) {
                expectedMsg = "row " + r + " has length " + expectedEdges[r].length;
                receivedMsg = "row " + r + " has length " + receivedEdges[r].length;
                passed = false;
                return;
            }
            for (int c = 0; c < expectedEdges[r].length; c++) {
                if (!expectedEdges[r][c].equals(receivedEdges[r][c])) {
                    expectedMsg = "[" + r + "][" + c + "] = " + expectedEdges[r][c];
                    receivedMsg = "[" + r + "][" + c + "] = " + receivedEdges[r][c];
                    passed = false;
                    return;
                }
            }
        }
        expectedMsg = "2D string arrays are the same";
        receivedMsg = "2D string arrays are the same";
        passed = true;
    }

    public TestResult(String message, int[] expected, int[] received) {
        this.message = message;
        expectedMsg = Arrays.toString(expected);
        receivedMsg = Arrays.toString(received);
        passed = Arrays.equals(expected, received);
    }
    
    public TestResult(String message, Object expected, Object received) {
        this.message = message;
        this.expectedMsg = expected + "";
        this.receivedMsg = received + "";
        this.passed = expected.equals(received);
    }
    

    public static void reportTestResults(ArrayList<TestResult> testResults) {
        System.out.println("Results of " + testResults.size() + " tests:");
        boolean errorsFound = false;
        for (TestResult result : testResults) {
            if (!result.passed) {
                System.out.println("Error: " + result.message);
                System.out.println("\tExpected: " + result.expectedMsg);
                System.out.println("\tReceived: " + result.receivedMsg);
                errorsFound = true;
            }
        }

        if (!errorsFound) {
            System.out.println("No Errors Found.");
        }
    }

    public static void print(String[][] arr) {
        String s = "";
        for (String[] row : arr) {
            s += Arrays.toString(row) + "\n";
        }
        System.out.println(s);
    }

    public static void print(String[][] expected, String[][] received) {
        System.out.println("Expected");
        print(expected);
        System.out.println("Received");
        print(received);
    }

} // End of class TestResult