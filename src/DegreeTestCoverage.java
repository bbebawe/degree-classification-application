import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

@DisplayName("Degree Coverage Tests")
class DegreeTestCoverage {
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
	 * Extra tests for the constructor to achieve max branch coverage
	 * =============================================================================
	 */
	
	@DisplayName("<- Test Degree Constructor To Throw Exception if Year Three Grade List is null ->")
	@Test
	public void DegreeConstructor_ShouldThrowException_IfYearThreeGradeListIsNull() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(3));
		yearTwoGradeList.add(new Grade(4));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(6));
		List<Grade> yearThreeGradeList = null;
		assertAll("Degree Constructor To Throw",
				() -> assertThrows(IllegalArgumentException.class,
						() -> degreeUnderTest = new Degree(yearTwoGradeList, yearThreeGradeList),
						"Constructor should throw IllegalArgumentException because year three grade list is null"),
				() -> assertThrows(NullPointerException.class, () -> degreeUnderTest.getLevelFiveProfile(),
						"getLevelFiveProfile() should throw NullPointerException because the object is not created"),
				() -> assertThrows(NullPointerException.class, () -> degreeUnderTest.getLevelSixProfile(),
						"getLevelSixProfile() should throw NullPointerException because the object is not created"));
	}

	@DisplayName("<- Test Degree Constructor To Throw Exception if both Grade List is null ->")
	@Test
	public void DegreeConstructor_ShouldThrowException_IfBothGradeListIsNull() {
		List<Grade> yearTwoGradeList = null;
		List<Grade> yearThreeGradeList = null;
		assertAll("Degree Constructor To Throw",
				() -> assertThrows(IllegalArgumentException.class,
						() -> new Degree(yearTwoGradeList, yearThreeGradeList),
						"should throw IllegalArgumentException because both grade list is null"),
				() -> assertThrows(NullPointerException.class, () -> degreeUnderTest.getLevelFiveProfile(),
						"getLevelFiveProfile() should throw NullPointerException because the object is not created"),
				() -> assertThrows(NullPointerException.class, () -> degreeUnderTest.getLevelSixProfile(),
						"getLevelSixProfile() should throw NullPointerException because the object is not created"));
	}

	@DisplayName("<- Test Degree Constructor to Throw Exception if Year Two Grade List is null ->")
	@Test
	public void DegreeConstructor_ShouldThrowException_IfYearTwoGradeListIsNull() {
		List<Grade> yearTwoGradeList = null;
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(3));
		yearThreeGradeList.add(new Grade(4));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(6));
		assertAll("Degree Constructor To Throw",
				() -> assertThrows(IllegalArgumentException.class,
						() -> new Degree(yearTwoGradeList, yearThreeGradeList),
						"should throw IllegalArgumentException because year two grade list is null"),
				() -> assertThrows(NullPointerException.class, () -> degreeUnderTest.getLevelFiveProfile(),
						"getLevelFiveProfile() should throw NullPointerException because the object is not created"),
				() -> assertThrows(NullPointerException.class, () -> degreeUnderTest.getLevelSixProfile(),
						"getLevelSixProfile() should throw NullPointerException because the object is not created"));
	}

	@DisplayName("<- Test Degree Constructor to Throw Exception if Year Two Grade List does not Contain Four Grades ->")
	@Test
	public void DegreeConstructor_ShouldThrowException_IfYearTwoListDoesNotContainFourGrades() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(3));
		yearTwoGradeList.add(new Grade(4));
		yearTwoGradeList.add(new Grade(5));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(3));
		yearThreeGradeList.add(new Grade(4));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(17));
		assertAll("Degree Constructor To Throw", () -> assertThrows(IllegalArgumentException.class,
				() -> new Degree(yearTwoGradeList, yearThreeGradeList),
				"should throw IllegalArgumentException because year two grade list does not contain four grades"),
				() -> assertThrows(NullPointerException.class, () -> degreeUnderTest.getLevelFiveProfile(),
						"getLevelFiveProfile() should throw NullPointerException because the object is not created"),
				() -> assertThrows(NullPointerException.class, () -> degreeUnderTest.getLevelSixProfile(),
						"getLevelSixProfile() should throw NullPointerException because the object is not created"));
	}

	@DisplayName("<- Test Degree Constructor to Throw Exception if Year Two Grade List Has Fail Grades ->")
	@Test
	public void DegreeConstructor_ShouldThrowException_IfYearTwoHasFailGrades() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(3));
		yearTwoGradeList.add(new Grade(4));
		yearTwoGradeList.add(new Grade(17));
		yearTwoGradeList.add(new Grade(6));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(3));
		yearThreeGradeList.add(new Grade(4));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(8));
		assertAll("Degree Constructor To Throw",
				() -> assertThrows(IllegalArgumentException.class,
						() -> new Degree(yearTwoGradeList, yearThreeGradeList),
						"should throw IllegalArgumentException because year two grade list has fail grades"),
				() -> assertThrows(NullPointerException.class, () -> degreeUnderTest.getLevelFiveProfile(),
						"getLevelFiveProfile() should throw NullPointerException because the object is not created"),
				() -> assertThrows(NullPointerException.class, () -> degreeUnderTest.getLevelSixProfile(),
						"getLevelSixProfile() should throw NullPointerException because the object is not created"));
	}

	/*
	 * Extra Tests to Test combineYearTwoAndYearThreeGrades method to combine year 2
	 * and year 3 grades.
	 * =============================================================================
	 */
	
	@DisplayName("<- Test combineYearTwoAndYearThreeGrades Method to Return one List Contains Year 2 and Year 3 Grades ->")
	@Test
	public void CombineYearTwoAndYearThreeGrades_ShouldReturnListWithEightElements() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(3));
		yearTwoGradeList.add(new Grade(4));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(6));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(3));
		yearThreeGradeList.add(new Grade(4));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(8));
		List<Grade> bothYearsGrades = Degree.combineYearTwoAndYearThreeGrades(yearTwoGradeList, yearThreeGradeList);
		assertEquals(8, bothYearsGrades.size(), "should be 8 because the method combines two 4 items list togther");
		assertEquals(3, bothYearsGrades.get(0).getPoints(), "points should be 3 beacuse the grade passed has 3");
		assertEquals(4, bothYearsGrades.get(1).getPoints(), "points should be 4 beacuse the grade passed has 4");
		assertEquals(5, bothYearsGrades.get(2).getPoints(), "points should be 5 beacuse the grade passed has 5");
		assertEquals(6, bothYearsGrades.get(3).getPoints(), "points should be 6 beacuse the grade passed has 6");
		assertEquals(3, bothYearsGrades.get(4).getPoints(), "points should be 3 beacuse the grade passed has 3");
		assertEquals(4, bothYearsGrades.get(5).getPoints(), "points should be 4 beacuse the grade passed has 4");
		assertEquals(5, bothYearsGrades.get(6).getPoints(), "points should be 5 beacuse the grade passed has 5");
		assertEquals(8, bothYearsGrades.get(7).getPoints(), "points should be 8 beacuse the grade passed has 8");
	}
	
	/*
	 * Extra Tests to Test The values of level 5 profile and level 6 profile when
	 * Grade object is created
	 * =============================================================================
	 */

	@DisplayName("<= Test Values of Level Five Profile =>")
	@Test
	public void testValuesOfLevelFiveProfile() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(3));
		yearTwoGradeList.add(new Grade(4));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(6));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(3));
		yearThreeGradeList.add(new Grade(4));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(8));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(3, degree.getLevelFiveProfile().getGradesList().get(0).getPoints(),
				"Should Return 3 because Profile Five Garde at index 0 is 3");
		assertEquals(4, degree.getLevelFiveProfile().getGradesList().get(1).getPoints(),
				"Should Return 4 because Profile Five Garde at index 1 is 4");
		assertEquals(5, degree.getLevelFiveProfile().getGradesList().get(2).getPoints(),
				"Should Return 5 because Profile Five Garde at index 2 is 5");
		assertEquals(6, degree.getLevelFiveProfile().getGradesList().get(3).getPoints(),
				"Should Return 6 because Profile Five Garde at index 3 is 6");
	}

	@DisplayName("<= Test Values of Level Six Profile =>")
	@Test
	public void testValuesOfLevelSixProfile() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(3));
		yearTwoGradeList.add(new Grade(4));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(6));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(16));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(8));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(1, degree.getLevelSixProfile().getGradesList().get(0).getPoints(),
				"Should Return 3 because Profile Six Garde at index 0 is 1");
		assertEquals(16, degree.getLevelSixProfile().getGradesList().get(1).getPoints(),
				"Should Return 4 because Profile Six Garde at index 1 is 16");
		assertEquals(5, degree.getLevelSixProfile().getGradesList().get(2).getPoints(),
				"Should Return 5 because Profile Six Garde at index 2 is 5");
		assertEquals(8, degree.getLevelSixProfile().getGradesList().get(3).getPoints(),
				"Should Return 8 because Profile Six Garde at index 3 is 8");
	}
	/*
	 * Extra Tests to Test Grade Getters to return Profile
	 * =============================================================================
	 */

	@DisplayName("<= Test Degree Setter and Getter to Set and Get Level Five  and Level Six Profile =>")
	@Test
	public void testLevelFiveGetterAndSetter() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(11));
		yearTwoGradeList.add(new Grade(2));
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(6));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(16));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(8));
		List<Grade> yearTwoAndThreeGradeList = new ArrayList<Grade>();
		yearTwoAndThreeGradeList.addAll(yearTwoGradeList);
		yearTwoAndThreeGradeList.addAll(yearThreeGradeList);
		Profile levelFiveProfile = new Profile(yearTwoAndThreeGradeList);
		Profile levelSixProfile = new Profile(yearThreeGradeList);
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(levelFiveProfile.getGradesList(), degree.getLevelFiveProfile().getGradesList(),
				"Should be Equal beacuse the same list used to create the profile and the Degree");
		assertEquals(levelSixProfile.getGradesList(), degree.getLevelSixProfile().getGradesList(),
				"Should be Equal beacuse the same list used to create the profile and the Degree");
	}

	/*
	 * Extra Tests for classify() method to achieve maximum branch coverage
	 * =====================================================================
	 */

	@DisplayName("<- Test Degree Classify When Level Five Profile is Better and Clear and Only One Level Better Than Level Six->")
	@Test
	public void Classify_ShouldReturnFirst_WhenProfileFiveIsFirstAndBetterThanLevelSix() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(3));
		yearTwoGradeList.add(new Grade(3));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(9));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.UpperSecond, degree.classifyLevelSixProfile(),
				"Profile classify() should be UpperSecond class");
		assertEquals(Classification.First, degree.classifyLevelFiveProfile(),
				"Profile classify() should be First class");
		assertEquals(Classification.First, degree.classify(),
				"Degree classify() should be First class because level five is better and Clear and only one Level better than level six");
	}
	
	@DisplayName("<- Test Degree Classify When Level Five Profile is Better Than Level Six Profile by More Than One Class ->")
	@Test
	public void TestClassify_WhenLevelFiveIsBetter_AndMoreThanOneClass() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(5));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(12));
		yearThreeGradeList.add(new Grade(12));
		yearThreeGradeList.add(new Grade(1));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.LowerSecond, degree.classifyLevelSixProfile(), "Profile classify() should be LowerSecond class");
		assertEquals(Classification.First, degree.classifyLevelFiveProfile(), "Profile classify() should be First class");
		assertEquals(Classification.Discretion, degree.classify(),
				"Degree classify() should be Discretion class because Level five profile is better by More Than One Class");
	}
	
	@DisplayName("<- Test Degree Classify When Level Five Profile is Better But Not Clear ->")
	@Test
	public void TestClassify_WhenLevelFiveIsBetter_ButNotClear() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(1));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(8));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.Third, degree.classifyLevelSixProfile(), "Profile classify() should be Third class");
		assertEquals(Classification.UpperSecond, degree.classifyLevelFiveProfile(), "Profile classify() should be LowerSecond class");
		assertEquals(Classification.Discretion, degree.classify(),
				"Degree classify() should be Discretion class because Level five profile is better but Not Clear");
	}
	
	@DisplayName("<- Test Degree Classify When Level Six Profile is Better But Not Clear->")
	@Test
	public void TestClassify_WhenLevelSixIsBetter_ButNotClear() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(9));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(1));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.First, degree.classifyLevelSixProfile(), "Profile classify() should be UpperSecond class");
		assertEquals(Classification.Third, degree.classifyLevelFiveProfile(), "Profile classify() should be First class");
		assertEquals(Classification.Discretion, degree.classify(),
				"Degree classify() should be Discretion class because level six profile is not clear");
	}
	
	/*
	 * Extra Tests to Test isLevelSixProfileBetter() Method to achieve maximum
	 * branch coverage
	 * ========================================================================
	 */
	
	@DisplayName("<- Test Degree IsLevelSixProfileBetter() When Both Profiles Classified Same ->")
	@Test
	public void IsLevelSixProfileBetter_ShouldReturnTrue_WhenLevelSixFirstAndFiveIsNot() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(1));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(1));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.First, degree.classifyLevelSixProfile(), "should be First class");
		assertEquals(Classification.First, degree.classifyLevelFiveProfile(), "should be First class");
		assertEquals(Classification.First, degree.classify(), "should be First class because both profiles are first");
		assertFalse(degree.isLevelSixProfileBetter(), "should return false becuase both profiles are same");
	}
	

	@DisplayName("<- Test Degree IsLevelSixProfileBetter() When Both Profiles UpperSecond ->")
	@Test
	public void IsLevelSixProfileBetter_ShouldReturnFalse_WhenBothProfilesUpperSecond() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(9));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(9));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(5));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.UpperSecond, degree.classifyLevelSixProfile(), "should be UpperSecond class");
		assertEquals(Classification.UpperSecond, degree.classifyLevelFiveProfile(), "should be UpperSecond class");
		assertEquals(Classification.UpperSecond, degree.classify(),
				"should be UpperSecond class because both profiles are UpperSecond");
		assertFalse(degree.isLevelSixProfileBetter(), "should return false becuase both profiles are UpperSecond");
	}
	@DisplayName("<- Test Degree IsLevelSixProfileBetter() When Both Profiles LowerSecond ->")
	@Test
	public void IsLevelSixProfileBetter_ShouldReturnFalse_WhenBothProfilesLowerSecond() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(9));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(9));
		yearThreeGradeList.add(new Grade(9));
		yearThreeGradeList.add(new Grade(9));
		yearThreeGradeList.add(new Grade(9));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.LowerSecond, degree.classifyLevelSixProfile(), "should be LowerSecond class");
		assertEquals(Classification.LowerSecond, degree.classifyLevelFiveProfile(), "should be LowerSecond class");
		assertEquals(Classification.LowerSecond, degree.classify(),
				"should be LowerSecond class because both profiles are LowerSecond");
		assertFalse(degree.isLevelSixProfileBetter(), "should return false becuase both profiles are LowerSecond");
	}

	@DisplayName("<- Test Degree IsLevelSixProfileBetter() When Level Six Is LowerSecond and Five is UpperSecond ->")
	@Test
	public void ShouldReturnFalse_WhenLevelSixIsLowerandFiveIsUpperSecond() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(9));
		yearThreeGradeList.add(new Grade(9));
		yearThreeGradeList.add(new Grade(9));
		yearThreeGradeList.add(new Grade(9));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.LowerSecond, degree.classifyLevelSixProfile(), "should be LowerSecond class");
		assertEquals(Classification.UpperSecond, degree.classifyLevelFiveProfile(), "should be UpperSecond class");
		assertEquals(Classification.UpperSecond, degree.classify(),
				"should be UpperSecond class because both profiles are first");
		assertFalse(degree.isLevelSixProfileBetter(), "should return false becuase level five is better");
	}
	/*
	 * Extra Tests to Test isLevelFiveProfileBetter() Method to achieve maximum
	 * branch coverage
	 * ========================================================================
	 */

	@DisplayName("<- Test Degree isLevelFiveProfileBetter() When Both Profiles are First ->")
	@Test
	public void ShouldReturnFalse_WhenBothProfilesAreFirst() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(1));
		yearTwoGradeList.add(new Grade(1));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(1));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.First, degree.classifyLevelSixProfile(), "should be First class");
		assertEquals(Classification.First, degree.classifyLevelFiveProfile(), "should be First class");
		assertEquals(Classification.First, degree.classify(), "should be First class because both profiles are first");
		assertFalse(degree.isLevelFiveProfileBetter(), "should return false becuase both profiles are First");
	}

	@DisplayName("<- Test Degree isLevelFiveProfileBetter() When Both Profiles UpperSecond ->")
	@Test
	public void ShouldReturnFalse_WhenBothProfilesUpperSecond() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(14));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(9));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(6));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(6));
		yearThreeGradeList.add(new Grade(7));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.UpperSecond, degree.classifyLevelSixProfile(), "should be UpperSecond class");
		assertEquals(Classification.UpperSecond, degree.classifyLevelFiveProfile(), "should be UpperSecond class");
		assertEquals(Classification.UpperSecond, degree.classify(),
				"should be UpperSecond class because both profiles are UpperSecond");
		assertFalse(degree.isLevelFiveProfileBetter(), "should return false becuase both profiles are UpperSecond");
	}

	@DisplayName("<- Test Degree isLevelFiveProfileBetter() When Level Five UpperSecond and Level Six Is First ->")
	@Test
	public void ShouldReturnFalse_WhenLevelFiveIsUpperSecondAndSixIsFirst() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(14));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(9));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(1));
		yearThreeGradeList.add(new Grade(9));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.First, degree.classifyLevelSixProfile(), "should be First class");
		assertEquals(Classification.UpperSecond, degree.classifyLevelFiveProfile(), "should be UpperSecond class");
		assertEquals(Classification.First, degree.classify(), "should be First");
		assertFalse(degree.isLevelFiveProfileBetter(), "should return false becuase level six is better");
	}

	@DisplayName("<- Test Degree isLevelFiveProfileBetter() When Both Profiles LowerSecond ->")
	@Test
	public void ShouldReturnFalse_WhenBothProfilesLowerSecond() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(14));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(12));
		yearTwoGradeList.add(new Grade(11));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(11));
		yearThreeGradeList.add(new Grade(12));
		yearThreeGradeList.add(new Grade(9));
		yearThreeGradeList.add(new Grade(9));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.LowerSecond, degree.classifyLevelSixProfile(), "should be LowerSecond class");
		assertEquals(Classification.LowerSecond, degree.classifyLevelFiveProfile(), "should be LowerSecond class");
		assertEquals(Classification.LowerSecond, degree.classify(),
				"should be LowerSecond class because both profiles are LowerSecond");
		assertFalse(degree.isLevelFiveProfileBetter(), "should return false becuase both profiles are LowerSecond");
	}

	@DisplayName("<- Test Degree isLevelFiveProfileBetter() When Level Five LowerSecond and Six UpperSecond ->")
	@Test
	public void ShouldReturnFalse_WhenLevelFiveIsLowerSecondAndSixIsUpperSecond() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(14));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(12));
		yearTwoGradeList.add(new Grade(11));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(12));
		yearThreeGradeList.add(new Grade(8));
		yearThreeGradeList.add(new Grade(8));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.UpperSecond, degree.classifyLevelSixProfile(), "should be UpperSecond class");
		assertEquals(Classification.LowerSecond, degree.classifyLevelFiveProfile(), "should be LowerSecond class");
		assertEquals(Classification.UpperSecond, degree.classify(), "should be UpperSecond");
		assertFalse(degree.isLevelFiveProfileBetter(),
				"should return false becuase level five is better than level six");
	}

	/*
	 * Extra Tests to Test isLevelSixBetterWithNoMoreThanOneClass() Method to
	 * achieve maximum branch coverage
	 * ========================================================================
	 */

	@DisplayName("<- Test Degree isLevelSixBetterWithNoMoreThanOneClass() When Both Profiles are UpperSecond ->")
	@Test
	public void ShouldReturnFalse_WhenBothProfilesAreUpperSecond() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		yearTwoGradeList.add(new Grade(5));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(5));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.UpperSecond, degree.classifyLevelSixProfile(), "should be UpperSecond class");
		assertEquals(Classification.UpperSecond, degree.classifyLevelFiveProfile(), "should be UpperSecond class");
		assertEquals(Classification.UpperSecond, degree.classify(),
				"should be UpperSecond class because both profiles are UpperSecond");
		assertFalse(degree.isLevelSixBetterWithNoMoreThanOneClass(),
				"should return false becuase both profiles are UpperSecond");
	}

	@DisplayName("<- Test Degree isLevelSixBetterWithNoMoreThanOneClass() When Both Profiles Third ->")
	@Test
	public void ShouldReturnFalse_WhenBothProfilesThird() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(13));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(12));
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(13));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.Third, degree.classifyLevelSixProfile(), "should be Third class");
		assertEquals(Classification.Third, degree.classifyLevelFiveProfile(), "should be Third class");
		assertEquals(Classification.Third, degree.classify(), "should be Third class because both profiles are Third");
		assertFalse(degree.isLevelSixBetterWithNoMoreThanOneClass(),
				"should return false becuase both profiles are Third");
	}

	/*
	 * Extra Tests to Test isLevelFiveBetterWithNoMoreThanOneClass() Method to
	 * achieve maximum branch coverage
	 * ========================================================================
	 */

	@DisplayName("<- Test isLevelFiveBetterWithNoMoreThanOneClass() When Level Five LowerSecond and Six UpperSecond ->")
	@Test
	public void ShouldReturnFalse_WhenLevelFiveLowerSecondAndSixUpperSecond() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(9));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(5));
		yearThreeGradeList.add(new Grade(9));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.UpperSecond, degree.classifyLevelSixProfile(), "should be UpperSecond class");
		assertEquals(Classification.LowerSecond, degree.classifyLevelFiveProfile(), "should be LowerSecond class");
		assertEquals(Classification.UpperSecond, degree.classify(), "should be UpperSecond");
		assertFalse(degree.isLevelFiveBetterWithNoMoreThanOneClass(),
				"should return false becuase level five is  LowerSecond and six is UpperSecond");
	}

	@DisplayName("<- Test isLevelFiveBetterWithNoMoreThanOneClass() When Level Five LowerSecond and Six Third ->")
	@Test
	public void ShouldReturnTrue_WhenLevelFiveLowerSecondAndSixIsThird() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(9));
		yearTwoGradeList.add(new Grade(9));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(13));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.Third, degree.classifyLevelSixProfile(), "should be Third class");
		assertEquals(Classification.LowerSecond, degree.classifyLevelFiveProfile(), "should be LowerSecond class");
		assertEquals(Classification.LowerSecond, degree.classify(),
				"should be LowerSecond class because both profiles are first");
		assertTrue(degree.isLevelFiveBetterWithNoMoreThanOneClass(),
				"should return true becuase level five is LowerSecond and six is Third");
	}

	@DisplayName("<- Test isLevelFiveBetterWithNoMoreThanOneClass() When Both Profiles Classified Third ->")
	@Test
	public void ShouldReturnFalse_WhenBothProfilesClassifiedThird() {
		List<Grade> yearTwoGradeList = new ArrayList<Grade>();
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(13));
		yearTwoGradeList.add(new Grade(13));
		List<Grade> yearThreeGradeList = new ArrayList<Grade>();
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(13));
		yearThreeGradeList.add(new Grade(13));
		Degree degree = new Degree(yearTwoGradeList, yearThreeGradeList);
		assertEquals(Classification.Third, degree.classifyLevelSixProfile(), "should be Third class");
		assertEquals(Classification.Third, degree.classifyLevelFiveProfile(), "should be Third class");
		assertEquals(Classification.Third, degree.classify(), "should be Third class because both profiles are Third");
		assertFalse(degree.isLevelFiveBetterWithNoMoreThanOneClass(),
				"should return false becuase both profiles are Third");
	}

}
