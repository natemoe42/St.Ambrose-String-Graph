import java.util.ArrayList;

/**
 *
 * @author Dr. Lillis
 */
public class Graph_2_Tester {

    static ArrayList<TestResult> tests = new ArrayList<>();
    static Graph g;

    public static void main(String[] args) {
        prevTests();
        spTests();
        exceptionTests();

        TestResult.reportTestResults(tests);
    }

    public static void prevTests() {
        g = GraphUtils.SP_Graph1();
        String[] solution = new String[]{"D", "I", null, "I", "F", "C", "F", "D", "J", "E", "G"};
        String[] result = g.bfsSSSP("C");
        tests.add(new TestResult("Previous Test 1", solution, result));

        g = GraphUtils.isolated5();
        solution = new String[]{null, null, null, null, null};
        result = g.bfsSSSP("C");
        tests.add(new TestResult("Previous Test 2", solution, result));

        g = GraphUtils.disconnected10();
        solution = new String[]{"F", "F", "A", "A", "F", null, null, null, null, null};
        result = g.bfsSSSP("F");
        tests.add(new TestResult("Previous Test 3", solution, result));
    }

    public static void spTests() {
        g = GraphUtils.SP_Graph1();
        tests.add(new TestResult("SP Test 1-1", GraphUtils.SP_Sol1_1(), g.shortestPath("A", "C")));

        g = GraphUtils.SP_Graph2();
        tests.add(new TestResult("SP Test 2-1", GraphUtils.SP_Sol2_1(), g.shortestPath("E", "F")));
        tests.add(new TestResult("SP Test 2-2", GraphUtils.SP_Sol2_2(), g.shortestPath("D", "H")));
        tests.add(new TestResult("SP Test 2-3", GraphUtils.SP_Sol2_3(), g.shortestPath("G", "E")));

        g = GraphUtils.disconnected10();
        tests.add(new TestResult("SP Test 3-1", new String[]{}, g.shortestPath("I", "F")));
        tests.add(new TestResult("SP Test 3-2", new String[]{}, g.shortestPath("I", "I")));
    }
    
    public static void exceptionTests(){
        g = GraphUtils.SP_Graph1();
        try{
            g.bfsSSSP("Z");
            tests.add(new TestResult("Exceptions 1-1", "Exception expected", "Exception not thrown"));
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 1-2", "Exception thrown", "Exception thrown"));
        }

        try{
            g.bfsSSSP("A");
            tests.add(new TestResult("Exceptions 2-1", "No exception", "No exception"));            
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 2-2", "Exception not expected", "Exception thrown"));
        }

        try{
            g.shortestPath("Z", "A");
            tests.add(new TestResult("Exceptions 3-1", "Exception expected", "Exception not thrown"));
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 3-2", "Exception thrown", "Exception thrown"));
        }

        try{
            g.shortestPath("A", "Z");
            tests.add(new TestResult("Exceptions 4-1", "Exception expected", "Exception not thrown"));
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 4-2", "Exception thrown", "Exception thrown"));
        }

        try{
            g.shortestPath("Y", "Z");
            tests.add(new TestResult("Exceptions 5-1", "Exception expected", "Exception not thrown"));
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 5-2", "Exception thrown", "Exception thrown"));
        }
        
        try{
            g.shortestPath("A", "B");
            tests.add(new TestResult("Exceptions 6-1", "No exception", "No exception"));
        } catch (GraphException e){
            tests.add(new TestResult("Exceptions 6-2", "Exception not expected", "Exception thrown"));
        }
        
    }
}