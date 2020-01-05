import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

@DisplayName("Profile Coverage Tests")
class ProfileTestCoverage {
	Profile profileUnderTest;
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
	 * Extra tests to test Constructor Throw Exception if Grade List has Fail Grade
	 * using fromPercentage()
	 * ========================================================================
	 */
	@DisplayName("<- Test Profile Constructor to Throw Exception if Grade List has Fail Grade using fromPercentage() ->")
	@Test
	void Constructor_ShouldThrowException_WhenFromPercentageMethodReturnFailGrade() {
		List<Grade> gradeList = new ArrayList<Grade>();
		gradeList.add(Grade.fromPercentage(35));
		assertThrows(IllegalArgumentException.class, () -> {
			profileUnderTest = new Profile(gradeList);
		}, "Constructor should throw IllegalArgumentException because list of grades has fail grades");
	}

	/*
	 * Extra tests for the constructor to test that it does not throw when valid
	 * input is passed
	 * =============================================================================
	 */

	@DisplayName("<- Test Profile Constructor Not to Throw Exception When Valid Input is Passed ->")
	@Test
	public void Constructor_ShouldNotToThrowException_WhenValidInputIsPassed() {
		List<Grade> gradeList = new ArrayList<Grade>();
		gradeList.add(new Grade(1));
		gradeList.add(new Grade(4));
		gradeList.add(new Grade(8));
		gradeList.add(new Grade(13));
		assertAll(" Constructor Not to Throw Exception",
				() -> assertDoesNotThrow(() -> profileUnderTest = new Profile(gradeList),
						"Constructor should not throw exception because the grade list is valid input"),
				() -> assertEquals(gradeList, profileUnderTest.getGradesList(), "Grades List must be [1, 4, 8, 13]"));
	}

	/*
	 * Test Profile Setters and Getters ================================
	 */

	@DisplayName("<- Test getGradesList() to Return Correct Grades List ->")
	@Test
	public void GetGradesList_ShouldReturnGradesList_WhenObjectIsCreated() {
		List<Grade> gradeList = new ArrayList<Grade>();
		gradeList.add(new Grade(1));
		gradeList.add(new Grade(4));
		gradeList.add(new Grade(13));
		gradeList.add(new Grade(13));
		Profile profile = new Profile(gradeList);
		assertAll("Test getGradesList()",
				() -> assertNotNull(profile.getGradesList(), "should not be null list"),
				() -> assertEquals(1, profile.getGradesList().get(0).getPoints(),
						"should be 1 because the first grade in the passed list is 1"),
				() -> assertEquals(4, profile.getGradesList().get(1).getPoints(),
						"should be 4 because the second grade in the passed list is 4"),
				() -> assertEquals(13, profile.getGradesList().get(2).getPoints(),
						"should be 13 because the third grade in the passed list is 13"),
				() -> assertEquals(13, profile.getGradesList().get(3).getPoints(),
						"should be 13 because the fourth grade in the passed list is 13"));
	}

	/*
	 * Extra Test for classify() 
	 */
	
	
	/*
	 * Extra Tests to Test isClear method
	 */





}
