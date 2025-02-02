public class ComparisonCompactorRefactoredTest extends TestCase {

    public ComparisonCompactorRefactoredTest() {
        super();
    }
    
    public void testMessage() {
        String failure = new ComparisonCompactorRefactored(0, "b", "c")
            .formatCompactedComparison("a");
        assertTrue("a expected:<[b]> but was:<[c]>".equals(failure));
    }

    public void testStartSame() {
        String failure = new ComparisonCompactorRefactored(1, "ba", "bc")
            .formatCompactedComparison(null);
        assertEquals("expected:<b[a]> but was:<b[c]>", failure);
    }

    public void testEndSame() {
        String failure = new ComparisonCompactorRefactored(1, "ab", "cb")
            .formatCompactedComparison(null);
        assertEquals("expected:<[a]b> but was:<[c]b>", failure);
    }

    public void testSame() {
        String failure = new ComparisonCompactorRefactored(1, "ab", "ab")
            .formatCompactedComparison(null);
        assertEquals("expected:<ab> but was:<ab>", failure);
    }

    public void testNoContextStartAndEndSame() {
        String failure = new ComparisonCompactorRefactored(0, "abc", "adc")
            .formatCompactedComparison(null);
        assertEquals("expected:<...[b]...> but was:<...[d]...>", failure);
    }

    public void testStartAndEndContext() {
        String failure = new ComparisonCompactorRefactored(1, "abc", "adc")
            .formatCompactedComparison(null);
        assertEquals("expected:<a[b]c> but was:<a[d]c>", failure);
    }

    public void testStartAndEndContextWithEllipses() {
        String failure = new ComparisonCompactorRefactored(1, "abcde", "abfde")
            .formatCompactedComparison(null);
        assertEquals("expected:<...b[c]d...> but was:<...b[f]d...>", failure);
    }

    public void testComparisonErrorStartSameComplete() {
        String failure = new ComparisonCompactorRefactored(2, "ab", "abc")
            .formatCompactedComparison(null);
        assertEquals("expected:<ab[]> but was:<ab[c]>", failure);
    }

    public void testComparisonErrorEndSameComplete() {
        String failure = new ComparisonCompactorRefactored(0, "bc", "abc")
            .formatCompactedComparison(null);
        assertEquals("expected:<[]...> but was:<[a]...>", failure);
    }

    public void testComparisonErrorOverlapingMatches() {
        String failure = new ComparisonCompactorRefactored(0, "abc", "abbc")
            .formatCompactedComparison(null);
        assertEquals("expected:<...[]...> but was:<...[b]...>", failure);
    }

    public void testComparisonErrorOverlapingMatchesContext() {
        String failure = new ComparisonCompactorRefactored(2, "abc", "abbc")
            .formatCompactedComparison(null);
        assertEquals("expected:<ab[]c> but was:<ab[b]c>", failure);
    }

    public void testComparisonErrorOverlapingMatches2() {
        String failure = new ComparisonCompactorRefactored(0, "abcdde", "abcde")
            .formatCompactedComparison(null);
        assertEquals("expected:<...[d]...> but was:<...[]...>", failure);
    }
    
    public void testComparisonErrorOverlapingMatches2Context() {
        String failure = new ComparisonCompactorRefactored(2, "abcdde", "abcde")
            .formatCompactedComparison(null);
        assertEquals("expected:<...cd[d]e> but was:<...cd[]e>", failure);
    }

    public void testComparisonErrorWithActualNull() {
        String failure = new ComparisonCompactorRefactored(0, "a", null)
            .formatCompactedComparison(null);
        assertEquals("expected:<a> but was:<null>", failure);
    }

    public void testComparisonErrorWithActualNullContext() {
        String failure = new ComparisonCompactorRefactored(2, "a", null)
            .formatCompactedComparison(null);
        assertEquals("expected:<a> but was:<null>", failure);
    }

    public void testComparisonErrorWithExpectedNull() {
        String failure = new ComparisonCompactorRefactored(0, null, "a")
            .formatCompactedComparison(null);
        assertEquals("expected:<null> but was:<a>", failure);
    }

    public void testComparisonErrorWithExpectedNullContext() {
        String failure = new ComparisonCompactorRefactored(2, null, "a")
            .formatCompactedComparison(null);
        assertEquals("expected:<null> but was:<a>", failure);
    }

    public void testBug609972() {
        String failure = new ComparisonCompactorRefactored(10, "S&P500", "0")
            .formatCompactedComparison(null);
        assertEquals("expected:<[S&P50]0> but was:<[]0>", failure);
    }
}