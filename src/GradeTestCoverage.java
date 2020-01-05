

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Grade Coverage Tests")
class GradeTestCoverage {
	Grade gradeUnderTest;
	TestInfo testInfo;
	TestReporter testReporter;

	@BeforeAll
	public static void classSetUp(TestInfo testInfo, TestReporter testReporter) {
		testReporter.publishEntry("<- Executing " + testInfo.getTestClass() + " Tests->");
	}

	@AfterAll
	public static void classCleanUp(TestInfo testInfo, TestReporter testReporter) {
		testReporter.publishEntry("<- Finished Executing " + testInfo.getTestClass() + " Tests->");
	}

	@BeforeEach
	void testSetUp(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		testReporter.publishEntry("<- Executing " + testInfo.getDisplayName() + " ->");
	}

	@AfterEach
	void testCleanUp() {
		testReporter.publishEntry("<- Finished Executing " + testInfo.getDisplayName() + " ->");
	}

	/*
	 * Extra Tests to Test that Grade constructor does not throw when valid grade is
	 * passed. Tests also test getPoints method to return correct points.
	 * =======================================================================
	 */

	@DisplayName("<- Test Grade Constructor Not To Throw IllegalArgumentException If Input At Maximum Constructor Range ->")
	@Test
	public void Constructor_ShouldNotThrowException_WhenInputAtMaximumConstructorRange() {
		assertAll("Grade Constructor Not To Throw", () -> assertDoesNotThrow(() -> gradeUnderTest = new Grade(20),
				"Constructor should not throw IllegalArgumentException because points is 20 which is the maximum valid input (1 to 20)"),
				() -> assertEquals(20, gradeUnderTest.getPoints(), "getPoints() should return 20"));
	}

	@DisplayName("<- Test Grade Constructor Not To Throw IllegalArgumentException If Input At Minimum Constructor Range ->")
	@Test
	public void Constructor_ShouldNotThrowException_WhenInputAtMinimumConstructorRange() {
		assertAll("Grade Constructor Not To Throw", () -> assertDoesNotThrow(() -> gradeUnderTest = new Grade(1),
				"Constructor should not throw IllegalArgumentException because points is 1 which the minimum valid input (1 to 20)"),
				() -> assertEquals(1, gradeUnderTest.getPoints(), "getPoints() should return 1"));
	}

	/*
	 * Extra Tests to Test getPoints() Method To Throw Exception if Input
	 * Constructor Throws Exception and Object is not Created Also, test that
	 * getPoints method should throw null pointer exception.
	 * =======================================================================
	 */

	@DisplayName("<- Test getPoints() Method To Throw Exception if Input Constructor Throws Exception and Object is not Created ->")
	@Test
	void GetPoints_ShouldThrowException_IfObjectIsNotCreated() {
		assertAll("GetPoints Should Throw Exception", () -> assertThrows(IllegalArgumentException.class,
				() -> gradeUnderTest = new Grade(0),
				"Constructor should throw IllegalArgumentException because the input is below 1 which is the minimum valid range"),
				() -> assertThrows(NullPointerException.class, () -> gradeUnderTest.getPoints(),
						"getPoints() should throw NullPointerException because the object is not created"));
	}

	/*
	 * Extra Tests to test that getPoints returns the right value at maximum and
	 * minimum constructor range
	 * ===========================================================================
	 */
	@DisplayName("<- Test getPoints() Method To Return Correct Points At Minimum Grade Constructor Range ->")
	@Test
	void GetPoints_ShouldReturnCorrectPoints_AtMinimumConstructorRange() {
		gradeUnderTest = new Grade(1);
		assertEquals(1, gradeUnderTest.getPoints(), "getPoints() should return 1 point because the passed input is 1");
	}

	@DisplayName("<- Test getPoints() Method To Return Correct Points At Maximum Grade Constructor Range ->")
	@Test
	void GetPoints_ShouldReturnCorrectPoints_AtMaximumConstructorRange() {
		Grade grade = new Grade(20);
		assertEquals(20, grade.getPoints(), "getPoints() should return 20 point because the passed input is 20");
	}

	/*
	 * Extra Tests to classify() method at Classifications minimum and maximum
	 * boundary
	 * =============================================================================
	 */
	@ParameterizedTest(name = "<- #{index} - Test classify({0}) to Return {1} ->")
	@MethodSource("providePointsClassificationPairs")
	@DisplayName("<- Test classify() Method To Return Classification Based On Grade Points ->")
	void Classify_ShouldReturnCorrectClassification_AtMinAndMaxBoundary(int points,
			Classification expectedClassification) {
		gradeUnderTest = new Grade(points);
		assertEquals(expectedClassification, gradeUnderTest.classify(),
				"Classification should be " + expectedClassification + " because the grade points is " + points);
	}

	// method source used to provide pair of points and classification
	static Stream<Arguments> providePointsClassificationPairs() {
		return Stream.of(Arguments.of(1, Classification.First), Arguments.of(4, Classification.First),
				Arguments.of(5, Classification.UpperSecond), Arguments.of(8, Classification.UpperSecond),
				Arguments.of(9, Classification.LowerSecond), Arguments.of(12, Classification.LowerSecond),
				Arguments.of(13, Classification.Third), Arguments.of(16, Classification.Third),
				Arguments.of(17, Classification.Fail), Arguments.of(20, Classification.Fail));
	}

	/*
	 * Extra Tests to Test That fromPercentage Does not Throw at Maximum and Minimum
	 * Valid Range Input
	 * =======================================================================
	 */

	@DisplayName("<- Test fromPercentage() Method Does not Throw at Maximum Valid Range (100) ->")
	@Test
	void FromPercentage_ShouldNotToThrow_AtMaxInputRange() {
		assertAll("fromPercentage() Method Does not Throw",
				() -> assertDoesNotThrow(() -> gradeUnderTest = Grade.fromPercentage(100),
						"fromPercentage() should not throw IllegalArgumentException because the grade is within 0-100"),
				() -> assertEquals(1, gradeUnderTest.getPoints(), "should be 1 because percentage passed is over 79"));
	}

	@DisplayName("<- Test fromPercentage Method Does not Throw at Minimum Valid Range (0) ->")
	@Test
	void FromPercentage_ShouldNotToThrow_AtMinInputRange() {
		assertAll("fromPercentage() Method Does not Throw",
				() -> assertDoesNotThrow(() -> gradeUnderTest = Grade.fromPercentage(0),
						"fromPercentage() should not throw IllegalArgumentException because the grade is within 0-100"),
				() -> assertEquals(19, gradeUnderTest.getPoints(),
						"Should be 19 because percentage passed is less than 29"));
	}

	@DisplayName("<- Test fromPercentage Method Does not Throw at Non Participation (-1) ->")
	@Test
	void FromPercentage_ShouldNotToThrowAt_NonParticipation() {
		assertAll("fromPercentage() Method Does not Throw", () -> assertDoesNotThrow(
				() -> gradeUnderTest = Grade.fromPercentage(-1),
				"fromPercentage() should not throw IllegalArgumentException because the grade Non Participation (-1)"),
				() -> assertEquals(20, gradeUnderTest.getPoints(),
						"Should be 20 because percentage passed Non Participation"));
	}

}
