public class Main {
    public static void main(String[] args) {
        TestCase comparisonCompactorTest = new ComparisonCompactorTest();
        comparisonCompactorTest.execute();
        comparisonCompactorTest.printTestsResults();

        TestCase comparisonCompactorDefactoredTest = new ComparisonCompactorDefactoredTest();
        comparisonCompactorDefactoredTest.execute();
        comparisonCompactorDefactoredTest.printTestsResults();

        TestCase comparisonCompactorRefactoredTest = new ComparisonCompactorRefactoredTest();
        comparisonCompactorRefactoredTest.execute();
        comparisonCompactorRefactoredTest.printTestsResults();
    }
}