import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Grade Specification Tests")
class GradeTestRequirements {
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
	 * TWO tests for inputs below and above the valid range for the constructor.
	 * =========================================================================
	 */

	@DisplayName("<- Test Grade Constructor To Throw Exception if Input Below Valid Constructor Range ->")
	@Test
	void Constructor_ShouldThrowException_IfInputBelowValidRange() {
		assertThrows(IllegalArgumentException.class, () -> gradeUnderTest = new Grade(0),
				"Constructor should throw IllegalArgumentException because the input is below 1 which is the minimum valid range");
	}

	@DisplayName("<- Test Grade Constructor To Throw Exception if Input Above Valid Constructor Range ->")
	@Test
	void Constructor_ShouldThrowException_IfInputAboveValidRange() {
		assertThrows(IllegalArgumentException.class, () -> gradeUnderTest = new Grade(21),
				"Constructor should throw IllegalArgumentException because the input is above 20 which is the maximum valid range");
	}

	/*
	 * ONE test for a valid input, checking that getPoints returns the right value
	 * ===========================================================================
	 */

	@DisplayName("<- Test getPoints() Method to Return Current Grade Object Points ->")
	@Test
	void GetPoints_ShouldReturnThePointsValue_IfObjectCreatedWithoutException() {
		gradeUnderTest = new Grade(15);
		assertEquals(15, gradeUnderTest.getPoints(),
				"getPoints() should return 15 because the value passed to constructor is 15");
	}

	/*
	 * FIVE tests for classify, using Classifications as equivalence classes
	 * ===========================================================================
	 */

	@ParameterizedTest(name = "<- #{index} - Test classify({0}) to Return {1} ->")
	@MethodSource("providePointsClassificationPairs")
	@DisplayName("<- Test classify() Method To Return Classification Based On Grade Points ->")
	void Classify_ShouldReturnFirst_WhenDegreeLowerOrEqualFour(int points, Classification expectedClassification) {
		gradeUnderTest = new Grade(points);
		assertSame(expectedClassification, gradeUnderTest.classify(),
				"classify() should return" + expectedClassification + " because the grade points is " + points);
	}

	// method source used to provide pair of points and classification to test
	static Stream<Arguments> providePointsClassificationPairs() {
		return Stream.of(Arguments.of(3, Classification.First), Arguments.of(7, Classification.UpperSecond),
				Arguments.of(10, Classification.LowerSecond), Arguments.of(13, Classification.Third),
				Arguments.of(17, Classification.Fail));
	}

	/*
	 * TWO tests for inputs below and above the valid range for fromPercentage
	 * =======================================================================
	 */

	@DisplayName("<- Test fromPercentage() Method To Throw Exception When Input is Below Valid Range ->")
	@Test
	void FromPercentage_ShouldThrowException_WhenInputBelowValidRange() {
		assertThrows(IllegalArgumentException.class, () -> Grade.fromPercentage(-2),
				"fromPercentage() should throw IllegalArgumentException because percentage is -2 and valid range is -1 and 0 to 100");
	}

	@DisplayName("<- Test fromPercentage() Method To Throw Exception When Input is Above Valid Range ->")
	@Test
	void FromPercentage_ShouldThrowException_WhenInputAboveValidRange() {
		assertThrows(IllegalArgumentException.class, () -> Grade.fromPercentage(101),
				"fromPercentage() should throw IllegalArgumentException because percentage is 101 and valid range is -1 and 0 to 100");
	}

	/*
	 * TWENTY tests for fromPercentage, using each point in the 20-point scale as an
	 * equivalence class Try to used parametrised test
	 * =============================================================================
	 */

	@ParameterizedTest(name = "<- #{index} - Test fromPercentage({0}) to Return {1} Point ->")
	@CsvSource({ "79, 1", "78, 2", "75, 3", "72, 4", "69, 5", "66, 6", "64, 7", "61, 8", "59, 9", "56, 10", "54, 11",
			"51, 12", "49, 13", "46, 14", "44, 15", "41, 16", "39, 17", "34, 18", "29, 19", "-1, 20" })
	@DisplayName("<- Test fromPercentage() Method To Create Grade Object With Correct Points ->")
	void FromPercentage_ShouldCreateGradeObject_WhenInputIsValid(int percentage, int points) {
		gradeUnderTest = Grade.fromPercentage(percentage);
		assertEquals(points, gradeUnderTest.getPoints(),
				"points should be " + gradeUnderTest.getPoints() + " as the input is above " + percentage);
	}

}
