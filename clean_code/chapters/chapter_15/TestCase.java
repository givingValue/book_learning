import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class TestCase {
    
    private ArrayList<Boolean> testMethodsResults;
    private ArrayList<String> testMethodsNames;
    private int successfulTests;

    public TestCase() {
        testMethodsResults = new ArrayList<Boolean>();
        testMethodsNames = new ArrayList<String>();
    }

    public void execute() {
        Class testObjectClass = this.getClass();
        Method[] testObjectMethods = testObjectClass.getDeclaredMethods();
        for (Method method: testObjectMethods) {
            if (isTestMethod(method)) {
                testMethodsNames.add(method.getName());
                invokeTestMethod(method);
            }
        }
    }

    private boolean isTestMethod(Method method) {
        String methodName = method.getName();
        if (methodName.startsWith("test")) {
            return true;
        }
        return false;
    }

    private void invokeTestMethod(Method testMethod) {
        try {
            testMethod.invoke(this);
        }  catch (InvocationTargetException e) {
            Throwable errorCause = e.getCause();
            System.out.printf("Invocation of %s failed: %s./n",
                testMethod.getName(), 
                errorCause.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void assertTrue(boolean bValue) {
        testMethodsResults.add(bValue);
    }

    public void assertEquals(String expected, String actual) {
        boolean bValue = expected.equals(actual);
        testMethodsResults.add(bValue);
    }

    public void printTestsResults() {
        printTestsResultsDelimiter();
        printTestsResultsDetails();
        printTestsResultsSummary();
        printTestsResultsDelimiter();
    }

    private void printTestsResultsDelimiter() {
        String testObjectClassName = this.getClass().getName();
        System.out.println();
        System.out.printf("#################### %s Suite Results ####################\n",
            testObjectClassName);
        System.out.println();
    }

    private void printTestsResultsDetails() {
        successfulTests = 0;
        int testNumber;
        for (int index = 0; index < testMethodsResults.size(); index++) {
            testNumber = index + 1;
            if (isSuccessfulTest(index)) {
                successfulTests++;
                System.out.printf("[#%d] '%s' test was successful!\n", 
                    testNumber,
                    testMethodsNames.get(index));
            } else {
                System.out.printf("[#%d] '%s' test was not successful*\n", 
                    testNumber,
                    testMethodsNames.get(index));
            }
        }
    }

    private void printTestsResultsSummary() {
        System.out.println();
        System.out.println("Test suite Summary:");
        System.out.printf("* Number of tests: %d.\n", testMethodsResults.size());
        System.out.printf("* Number of successful tests: %d.\n", successfulTests);
        System.out.printf("* Success ratio: %.2f%%.\n", 
            (float)(successfulTests/testMethodsResults.size()*100));
    }

    private boolean isSuccessfulTest(int index) {
        return testMethodsResults.get(index);
    }
}