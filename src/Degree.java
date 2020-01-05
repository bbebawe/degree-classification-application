
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a degree to be awarded to a student.
 * 
 * @author beshoy bebawe
 *
 */
public class Degree {

	private Profile levelFiveProfile;
	private Profile levelSixProfile;

	/**
	 * Two argument constructor takes two Lists of Grades for years two and three
	 * respectively. It must throw an IllegalArgumentException whenever either list
	 * given is null, does not contain four grades, or contains a fail grade.
	 * 
	 * @param year2
	 * @param year3
	 */
	public Degree(List<Grade> year2, List<Grade> year3) {
		if (year2 == null || year3 == null || year2.size() < 4 || year3.size() < 4 || hasFail(year2)
				|| hasFail(year3)) {
			throw new IllegalArgumentException();
		}
		// create level five profile using both grades list
		this.levelFiveProfile = new Profile(combineYearTwoAndYearThreeGrades(year2, year3));
		this.levelSixProfile = new Profile(year3);
	}

	/**
	 * The getLevelFiveProfile method is a getter used to return level five profile.
	 * 
	 * @return list of level five profile grades.
	 */

	public Profile getLevelFiveProfile() {
		return levelFiveProfile;
	}

	/**
	 * The getLevelSixProfile method is a getter used to return level six profile.
	 * 
	 * @return list of level six profile grades.
	 */
	public Profile getLevelSixProfile() {
		return levelSixProfile;
	}

	/**
	 * The createLevelFiveProfile method combines year 2 and year 3 grades in one
	 * list.
	 * 
	 * @param year2
	 * @param year3
	 * @return grade list contains year 2 and year 3 grades.
	 */
	public static List<Grade> combineYearTwoAndYearThreeGrades(List<Grade> year2, List<Grade> year3) {
		List<Grade> bothYearsGrades = new ArrayList<Grade>();
		bothYearsGrades.addAll(year2);
		bothYearsGrades.addAll(year3);
		return bothYearsGrades;
	}

	/**
	 * The method classify returns the Classification (first, upper second, etc).
	 * 
	 * @return current Degree classification.
	 */

	public Classification classify() {
		if (classifyLevelFiveProfile().equals(classifyLevelSixProfile())) {
			return classifyLevelFiveProfile();
		}
		if (isLevelSixProfileBetter() && this.levelSixProfile.isClear() && isLevelSixBetterWithNoMoreThanOneClass()) {
			return classifyLevelSixProfile();
		}
		if (isLevelFiveProfileBetter() && this.levelFiveProfile.isClear()
				&& isLevelFiveBetterWithNoMoreThanOneClass()) {
			return classifyLevelFiveProfile();
		}
		return Classification.Discretion;

	}

	/**
	 * The classifyLevelFiveProfile classify level five profile.
	 * 
	 * @return level five profile classification.
	 */
	public Classification classifyLevelFiveProfile() {
		return this.levelFiveProfile.classify();
	}

	/**
	 * The classifyLevelSixProfile classify level six profile.
	 * 
	 * @return level six profile classification.
	 */
	public Classification classifyLevelSixProfile() {
		return this.levelSixProfile.classify();
	}

	/**
	 * The hasFail checks if the gradeList has fail grades.
	 * 
	 * @param gradeList
	 * @return return true if list has fail grades and false if not.
	 */
	private boolean hasFail(List<Grade> gradeList) {
		boolean hasFail = false;
		for (Grade grade : gradeList) {
			if (grade.classify().equals(Classification.Fail)) {
				hasFail = true;
			}
		}
		return hasFail;
	}

	/**
	 * The isLevelSixProfileBetter method return true if level six profile is better
	 * than level five profile.
	 * 
	 * @return true if level six is better than level five and false if not.
	 */
	public boolean isLevelSixProfileBetter() {
		if (classifyLevelSixProfile().equals(Classification.First)
				&& !classifyLevelFiveProfile().equals(Classification.First)) {
			return true;
		}
		if (classifyLevelSixProfile().equals(Classification.UpperSecond)
				&& !classifyLevelFiveProfile().equals(Classification.First)
				&& !classifyLevelFiveProfile().equals(Classification.UpperSecond)) {
			return true;
		}
		if (classifyLevelSixProfile().equals(Classification.LowerSecond)
				&& !classifyLevelFiveProfile().equals(Classification.First)
				&& !classifyLevelFiveProfile().equals(Classification.UpperSecond)
				&& !classifyLevelFiveProfile().equals(Classification.LowerSecond)) {
			return true;
		}
		// return when level five is better
		return false;
	}

	/**
	 * The isLevelFiveProfileBetter method return true if level five profile is
	 * better than level six profile.
	 * 
	 * @return true if level five is better than level six and false if not.
	 */
	public boolean isLevelFiveProfileBetter() {
		if (classifyLevelFiveProfile().equals(Classification.First)
				&& !classifyLevelSixProfile().equals(Classification.First)) {
			return true;
		}
		if (classifyLevelFiveProfile().equals(Classification.UpperSecond)
				&& !classifyLevelSixProfile().equals(Classification.First)
				&& !classifyLevelSixProfile().equals(Classification.UpperSecond)) {
			return true;
		}
		if (classifyLevelFiveProfile().equals(Classification.LowerSecond)
				&& !classifyLevelSixProfile().equals(Classification.First)
				&& !classifyLevelSixProfile().equals(Classification.UpperSecond)
				&& !classifyLevelSixProfile().equals(Classification.LowerSecond)) {
			return true;
		}
		// return when level six is better
		return false;
	}

	/**
	 * The isLevelSixBetterWithNoMoreThanOneClass return true if level six profile
	 * is better with no more than one class.
	 * 
	 * @return true if profile is better with no more than one class.
	 */
	public boolean isLevelSixBetterWithNoMoreThanOneClass() {
		if (this.classifyLevelFiveProfile().equals(Classification.UpperSecond)
				&& this.classifyLevelSixProfile().equals(Classification.First)) {
			return true;
		}
		if (this.classifyLevelFiveProfile().equals(Classification.LowerSecond)
				&& this.classifyLevelSixProfile().equals(Classification.UpperSecond)) {
			return true;
		}
		if (this.classifyLevelFiveProfile().equals(Classification.Third)
				&& this.classifyLevelSixProfile().equals(Classification.LowerSecond)) {
			return true;
		}
		return false;
	}

	/**
	 * The isLevelFiveBetterWithNoMoreThanOneClass return true if level five profile
	 * is better with no more than one class.
	 * 
	 * @return true if profile is better with no more than one class.
	 */
	public boolean isLevelFiveBetterWithNoMoreThanOneClass() {
		if (this.classifyLevelSixProfile().equals(Classification.UpperSecond)
				&& this.classifyLevelFiveProfile().equals(Classification.First)) {
			return true;
		}
		if (this.classifyLevelSixProfile().equals(Classification.LowerSecond)
				&& this.classifyLevelFiveProfile().equals(Classification.UpperSecond)) {
			return true;
		}
		if (this.classifyLevelSixProfile().equals(Classification.Third)
				&& this.classifyLevelFiveProfile().equals(Classification.LowerSecond)) {
			return true;
		}
		return false;
	}

}
