import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

@DisplayName("Profile Specification Tests")
class ProfileTestRequirements {

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
     * THREE tests for the constructor, one for each distinct way input can be
     * invalid
     * ========================================================================
     */

    @DisplayName("<- Test Profile Constructor to Throw Exception if Grades List is null ->")
    @Test
    void ProfileConstructor_ShouldThrowException_IfGradesListIsNull() {
        List<Grade> gradeList = null;
        assertThrows(IllegalArgumentException.class, () -> profileUnderTest = new Profile(gradeList),
                "Constructor should throw IllegalArgumentException because Grades List is null");
    }

    @DisplayName("<- Test Profile Constructor to Throw Exception if  Grade List is Empty ->")
    @Test
    void ProfileConstructor_ShouldThrowException_IfGradesListIsEmpty() {
        List<Grade> gradeList = new ArrayList<Grade>();
        assertThrows(IllegalArgumentException.class, () -> profileUnderTest = new Profile(gradeList),
                "Constructor should throw IllegalArgumentException because Grades List is empty");
    }

    @DisplayName("<- Test Profile Constructor to Throw Exception if Grades List has Fail Grades ->")
    @Test
    void ProfileConstructor_ShouldThrowException_IfGradesListHasFailGrades() {
        List<Grade> gradeList = new ArrayList<Grade>();
        gradeList.add(new Grade(17));
        assertThrows(IllegalArgumentException.class, () -> profileUnderTest = new Profile(gradeList),
                "Constructor should throw IllegalArgumentException because Grades List  fail grades");
    }

    /*
     * SIX tests, one for each possible combination of Classification and truth
     * value (whether the profile is clear or not) as an equivalence class
     * =========================================================================
     */

    @DisplayName("<- Test isClear() to return True When Profile is Classified Third ->")
    @Test
    public void IsClear_ShouldReturnTrue_WhenProfileIsClassifiedThird() {
        List<Grade> gradeList = new ArrayList<Grade>();
        gradeList.add(new Grade(14));
        gradeList.add(new Grade(9));
        gradeList.add(new Grade(16));
        gradeList.add(new Grade(13));
        profileUnderTest = new Profile(gradeList);
        assertAll("isClear To Return True",
                () -> assertEquals(Classification.Third, profileUnderTest.classify(),
                        "classify() should return Third because more than 50% of grades are Third"),
                () -> assertTrue(profileUnderTest.isClear(), "isClear() should return true because profile is classified Third")
        );
    }

    @DisplayName("<- Test isClear() to return True When Profile is Classified LowerSecond ->")
    @Test
    public void IsClear_ShouldReturnTrue_WhenProfileIsClassifiedLowerSecond() {
        List<Grade> gradeList = new ArrayList<Grade>();
        gradeList.add(new Grade(10));
        gradeList.add(new Grade(9));
        gradeList.add(new Grade(16));
        gradeList.add(new Grade(13));
        profileUnderTest = new Profile(gradeList);
        assertAll("isClear To Return True",
                () -> assertEquals(Classification.LowerSecond, profileUnderTest.classify(),
                        "classify() should return LowerSecond because 50% of grades is LowerSecond or above"),
                () -> assertTrue(profileUnderTest.isClear(), "isClear() should return true because profile is classified LowerSecond")
        );
    }


    @DisplayName("<- Test isClear() to return True When Profile is Classified First and No More Than 25% of Grades are Third ->")
    @Test
    public void IsClear_ShouldReturnTrue_WhenProfileIsClassifiedFirst_AndNoMoreThanTwentyPercentAreThird() {
        List<Grade> gradeList = new ArrayList<Grade>();
        gradeList.add(new Grade(1));
        gradeList.add(new Grade(9));
        gradeList.add(new Grade(4));
        gradeList.add(new Grade(13));
        profileUnderTest = new Profile(gradeList);
        assertAll("isClear To Return True",
                () -> assertEquals(Classification.First, profileUnderTest.classify(),
                        "classify() should return First because 50% of grades are First "),
                () -> assertTrue(profileUnderTest.isClear(), "isClear() should return true because profile is classified First and no more than 25% of grades are Third")
        );
    }

    @DisplayName("<- Test isClear() to return True When Profile is Classified UpperSecond and No More Than 25% of Grades are Third ->")
    @Test
    public void IsClear_ShouldReturnTrue_WhenProfileIsClassifiedUpperSecond_AndNoMoreThanTwentyPercentAreThird() {
        List<Grade> gradeList = new ArrayList<Grade>();
        gradeList.add(new Grade(5));
        gradeList.add(new Grade(9));
        gradeList.add(new Grade(4));
        gradeList.add(new Grade(13));
        profileUnderTest = new Profile(gradeList);
        assertAll("isClear To Return True",
                () -> assertEquals(Classification.UpperSecond, profileUnderTest.classify(),
                        "classify() should return UpperSecond because 50% of grades is UpperSecond or above"),
                () -> assertTrue(profileUnderTest.isClear(), "isClear() should return true because profile is classified UpperSecond and no more than 25% of grades are Third")
        );
    }

    @DisplayName("<- Test isClear() to return False When Profile is Classified First and  More Than 25% of Grades are Third ->")
    @Test
    public void IsClear_ShouldReturnFalse_WhenProfileIsClassifiedFirst_AndMoreThanTwentyPercentAreThird() {
        List<Grade> gradeList = new ArrayList<Grade>();
        // year two grades
        gradeList.add(new Grade(1));
        gradeList.add(new Grade(13));
        gradeList.add(new Grade(4));
        gradeList.add(new Grade(5));
        // year three grades
        gradeList.add(new Grade(13));
        gradeList.add(new Grade(3));
        gradeList.add(new Grade(4));
        gradeList.add(new Grade(13));
        profileUnderTest = new Profile(gradeList);
        assertAll("isClear To Return True",
                () -> assertEquals(Classification.First, profileUnderTest.classify(),
                        "classify() should return UpperSecond because 50% of grades is First or above"),
                () -> assertFalse(profileUnderTest.isClear(), "isClear() should return false because profile is classified First and more than 25% of grades are Third")
        );
    }

    @DisplayName("<- Test isClear() to return False When Profile is Classified UpperSecond and  More Than 25% of Grades are Third ->")
    @Test
    public void IsClear_ShouldReturnFalse_WhenProfileIsClassifiedUpperSecond_AndMoreThanTwentyPercentAreThird() {
        List<Grade> gradeList = new ArrayList<Grade>();
        // year two grades
        gradeList.add(new Grade(13));
        gradeList.add(new Grade(9));
        gradeList.add(new Grade(4));
        gradeList.add(new Grade(5));
        // year three grades
        gradeList.add(new Grade(13));
        gradeList.add(new Grade(8));
        gradeList.add(new Grade(4));
        gradeList.add(new Grade(13));
        profileUnderTest = new Profile(gradeList);
        assertAll("isClear To Return True",
                () -> assertEquals(Classification.UpperSecond, profileUnderTest.classify(),
                        "classify() should return UpperSecond because 50% of grades is UpperSecond or above"),
                () -> assertFalse(profileUnderTest.isClear(), "isClear() should return false because profile is classified UpperSecond and more than 25% of grades are Third")
        );
    }

}
