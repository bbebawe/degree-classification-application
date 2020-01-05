
import java.util.List;

/**
 * This class holds a profile of student grade list
 * 
 * @author beshoy bebawe
 *
 */
public class Profile {

	private List<Grade> gradesList;

	/**
	 * The constructor takes a list of grades to be inserted in the profile It must
	 * throw an IllegalArgumentException if there are any fail grades, or if the
	 * list is empty or null.
	 * 
	 * @param gradeList
	 */
	public Profile(List<Grade> gradeList) {
		if (gradeList == null || gradeList.isEmpty() || hasFail(gradeList)) {
			throw new IllegalArgumentException();
		}
		this.gradesList = gradeList;
	}

	/**
	 * The getGradesList is a getter used to get current object gradesList.
	 * 
	 * @return return current object gradesList.
	 */
	public List<Grade> getGradesList() {
		return gradesList;
	}

	/**
	 * The classify method return the classification of the current profile.
	 * 
	 * @return return current profile classification.
	 */
	public Classification classify() {
		if (this.percentageOfFirstClassGrades() >= 50) {
			return Classification.First;
		}
		if (this.percentageOfUpperSecondClassOrAbove() >= 50) {
			return Classification.UpperSecond;
		}
		if (this.percentageOfLowerSecondOrAbove() >= 50) {
			return Classification.LowerSecond;
		}
		// return when 50% of grades are not LowerSecond or above
		return Classification.Third;
	}

	/**
	 * The isClear method must return true if the current profile is clear, and
	 * false if the profile is borderline. Namely, if the profile is classified as
	 * first or upper second class then it is a clear profile if the third class
	 * grades in the profile are no more than 25% of the total, otherwise the
	 * profile is borderline. Lower second and third class profiles are always
	 * clear.
	 * 
	 * @return return true if profile is clear and false if borderline.
	 */
	public boolean isClear() {
		if (this.classify().equals(Classification.LowerSecond) || this.classify().equals(Classification.Third)) {
			return true;
		}
		if (this.classify().equals(Classification.First) && perecntageOfThirdClassGrades() <= 25) {
			return true;
		}
		if( this.classify().equals(Classification.UpperSecond) && perecntageOfThirdClassGrades() <= 25) {
			return true;
		}
		// return when profile is First or UpperSecond and more than 25% Third
		return false;

	}

	/**
	 * The hasFail method return true if grades list has fail and false if not.
	 * 
	 * @param gradeList
	 * @return true if has fail and false if not.
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
	 * The perecntageOfThirdClassGrades calculates the percentage of Third class
	 * grades in a list.
	 * 
	 * @return percentage of third class grades in a list.
	 */
	private float perecntageOfThirdClassGrades() {
		float numberOfThirdClassGrades = 0;
		float perecntageOfThirdClassGrades;
		for (Grade grade : this.gradesList) {
			if (grade.classify().equals(Classification.Third)) {
				numberOfThirdClassGrades++;
			}
		}
		perecntageOfThirdClassGrades = (float) (numberOfThirdClassGrades / gradesList.size()) * 100;
		return perecntageOfThirdClassGrades;

	}

	/**
	 * The percentageOfFirstClassGrades calculates the percentage of First class
	 * grades in a list.
	 * 
	 * @return percentage of first class grades in a list.
	 */
	private float percentageOfFirstClassGrades() {
		float numberOfFirstClassGrades = 0;
		float percentageOfFirstClassGrades;
		for (Grade grade : this.gradesList) {
			if (grade.classify().equals(Classification.First)) {
				numberOfFirstClassGrades++;
			}
		}
		percentageOfFirstClassGrades = (float) (numberOfFirstClassGrades / gradesList.size()) * 100;
		return percentageOfFirstClassGrades;
	}

	/**
	 * The percentageOfUperScondClassOrAbove calculates the percentage of First and
	 * UpperSecond grades in a list.
	 * 
	 * @return percentage of First and UpperSecond grades in a list.
	 */
	private float percentageOfUpperSecondClassOrAbove() {
		float numberOfUpperSecondClassOrAbove = 0;
		float percentageOfUpperSecondClassOrAbove;
		for (Grade grade : this.gradesList) {
			if (grade.classify().equals(Classification.First) || grade.classify().equals(Classification.UpperSecond)) {
				numberOfUpperSecondClassOrAbove++;
			}
		}
		percentageOfUpperSecondClassOrAbove = (float) (numberOfUpperSecondClassOrAbove / gradesList.size()) * 100;
		return percentageOfUpperSecondClassOrAbove;
	}

	/**
	 * The percentageOfLowerSecondOrAbove calculates the percentage of First and
	 * UpperSecond and LowerSecond grades in a list.
	 * 
	 * @return percentage of First and UpperSecond and grades LowerSecond in a list.
	 */
	private float percentageOfLowerSecondOrAbove() {
		float numberOfLowerSecondOrAbove = 0;
		float percentageOfLowerSecondOrAbove;
		for (Grade grade : this.gradesList) {
			if (grade.classify().equals(Classification.First)
					|| grade.classify().equals(Classification.UpperSecond)
					|| grade.classify().equals(Classification.LowerSecond)) {
				numberOfLowerSecondOrAbove++;
			}
		}
		percentageOfLowerSecondOrAbove = (float) (numberOfLowerSecondOrAbove / gradesList.size()) * 100;
		return percentageOfLowerSecondOrAbove;
	}
}
