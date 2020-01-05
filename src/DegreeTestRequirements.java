import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

@DisplayName("Degree Specification Tests")
class DegreeTestRequirements {
	Degree degreeUnderTest;
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
	 * THREE tests for the constructor, one for each distinct way input can be
	 * invalid
	 * =============================================================================
	 */

	@DisplayName("<- Test Degree Constructor To Throw Exception if One Grade List is null ->")
	@Test
	public void DegreeConstructor_ShouldThrowException_IfOneYearGradeListIsNull() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(3));
		yearTwoGradeList.add(new Grade(4));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(6));
		List<Grade> yearThreeGradeList = null;
		assertThrows(IllegalArgumentException.class,
				() -> degreeUnderTest = new Degree(yearTwoGradeList, yearThreeGradeList),
				"Constructor should throw IllegalArgumentException because year three grade list is null");
	}

	@DisplayName("<- Test Degree Constructor To Throw Exception if One Grade List does not Contain Four Grades ->")
	@Test
	public void DegreeConstructor_ShouldThrowException_IfYearOneListDoesNotContainFourGrades() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(3));
		yearTwoGradeList.add(new Grade(4));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(6));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(3));
		yearThreeGradeList.add(new Grade(4));
		yearThreeGradeList.add(new Grade(5));
		assertThrows(IllegalArgumentException.class,
				() -> degreeUnderTest = new Degree(yearTwoGradeList, yearThreeGradeList),
				"Constructor should throw IllegalArgumentException because year three grade list does not contain four grades");
	}

	@DisplayName("<- Test Degree Constructor To Throw Exception if One Grade List Has Fail Grades ->")
	@Test
	public void DegreeConstructor_ShouldThrowException_IfOneYearHasFailGrades() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(3));
		yearTwoGradeList.add(new Grade(4));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(6));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(3));
		yearThreeGradeList.add(new Grade(4));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(17));
		assertThrows(IllegalArgumentException.class, () -> new Degree(yearTwoGradeList, yearThreeGradeList),
				"Constructor should throw IllegalArgumentException because year three grade list has fail grades");
	}

	/*
	 * FIVE tests, using Classifications as equivalence classes
	 * =========================================================
	 */

	@DisplayName("<- Test Classify()  To Return Third When Both Profiles are Classified Third ->")
	@Test
	public void Classify_ShouldReturnProfileFiveClassification_WhenBothProfilesClassifiedSame() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(14));
		yearTwoGradeList.add(new Grade(13));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(14));
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(12));
		yearThreeGradeList.add(new Grade(16));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.Third, degree.classifyLevelSixProfile(),
				"Profile classify() should be Third class");
		assertEquals(Classification.Third, degree.classifyLevelFiveProfile(),
				"Profile classify() should be Third class");
		assertEquals(Classification.Third, degree.classify(),
				"Degree classify() should be Third class because both profile classified Third");
	}

	@DisplayName("<- Test Degree Classify To Return First When Level Six Classified First and Clear and Only One Level Better Than Level Five ->")
	@Test
	public void Classify_ShouldReturnFirst_WhenProfileSixIsFirstAndBetterThanLevelFive() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(4));
		yearThreeGradeList.add(new Grade(4));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.First, degree.classifyLevelSixProfile(),
				"Profile classify() should be First class");
		assertEquals(Classification.UpperSecond, degree.classifyLevelFiveProfile(),
				"Profile classify() should be First class");
		assertEquals(Classification.First, degree.classify(),
				"Degree classify() should be First class because level six is First and Clear and only one Level better than level five");
	}

	@DisplayName("<- Test Degree Classify To Return UpperSecond When Level Six is UpperSecond and Clear and Only One Level Better Than Level Five ->")
	@Test
	public void Classify_ShouldReturnUpperSecond__WhenProfileSixIsUpperSecondAndBetterThanLevelFive() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(5));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(9));
		yearThreeGradeList.add(new Grade(6));
		yearThreeGradeList.add(new Grade(6));
		yearThreeGradeList.add(new Grade(9));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.UpperSecond, degree.classifyLevelSixProfile(),
				"Profile classify() should be UpperSecond class");
		assertEquals(Classification.LowerSecond, degree.classifyLevelFiveProfile(),
				"Profile classify() should be LowerSecond class");
		assertEquals(Classification.UpperSecond, degree.classify(),
				"Degree classify() should be UpperSecond class because level six UpperSecond and Clear and only one Level better than level five");
	}

	@DisplayName("<- Test Degree Classify To Return LowerSecond When Level Six is LowerSecond and Clear and Only One Level Better Than Level Five ->")
	@Test
	public void Classify_ShouldReturnLowerSecond_WhenProfileSixIsLowerSecondAndBetterThanLevelFive() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(14));
		yearTwoGradeList.add(new Grade(13));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(9));
		yearThreeGradeList.add(new Grade(9));
		yearThreeGradeList.add(new Grade(6));
		yearThreeGradeList.add(new Grade(16));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.LowerSecond, degree.classifyLevelSixProfile(),
				"Profile classify() should be LowerSecond class");
		assertEquals(Classification.Third, degree.classifyLevelFiveProfile(),
				"Profile classify() should be Third class");
		assertEquals(Classification.LowerSecond, degree.classify(),
				"Degree classify() should be LowerSecond class because level six LowerSecond and Clear and only one Level better than level five");
	}

	@DisplayName("<- Test Degree Classify To Return Discretion When Level Six is First and Better and More Than One Level Above Level Five ->")
	@Test
	public void Classify_ShouldReturnDiscretion_WhenLevelSixProfileBetterByMoreThanOneClass() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(12));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(9));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.First, degree.classifyLevelSixProfile(),
				"Profile classify() should be First class");
		assertEquals(Classification.LowerSecond, degree.classifyLevelFiveProfile(),
				"Profile classify() should be LowerSecond class");
		assertEquals(Classification.Discretion, degree.classify(),
				"Degree classify() should be Discretion because level six is First better Than level five by more than one level above");
	}
}
