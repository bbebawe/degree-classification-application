/**
 * The class Grade holds a grade in the MDX 20-point scale
 * 
 * @author beshoy bebawe
 *
 */
public class Grade {

	private final int points;

	/**
	 * One argument constructor to create Grade object. The constructor must throw
	 * IllegalArgumentException if its input is outside 1–20.
	 *
	 * @param points
	 * @throws IllegalArgumentException
	 */
	public Grade(int points) throws IllegalArgumentException {
		if (points < 1 || points > 20) {
			throw new IllegalArgumentException();
		}
		this.points = points;
	}

	/**
	 * The getPoints method is used to return points in Grade object.
	 *
	 * @return return points in current Grade object.
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * The classify method is used to classify the current grade object (as stored
	 * in the points field).
	 *
	 * @return Classification (first, upper second, etc) of the current grade.
	 */
	public Classification classify() {
		if (this.points <= 4) {
			return Classification.First;
		}
		if (this.points <= 8) {
			return Classification.UpperSecond;
		}
		if (this.points <= 12) {
			return Classification.LowerSecond;
		}
		if (this.points <= 16) {
			return Classification.Third;
		}
		// return when points less than 16
		return Classification.Fail;
	}

	/**
	 * The static method fromPercentage creates a Grade object. The method must
	 * throw an IllegalArgumentException if its argument is not within 0-100, nor
	 * -1.
	 *
	 * @param grade
	 * @return grade object
	 * @throws IllegalArgumentException
	 */
	public static Grade fromPercentage(int grade) throws IllegalArgumentException {
		if (grade < -1 || grade > 100) {
			throw new IllegalArgumentException();
		}
		if (grade >= 79) {
			return new Grade(1);
		}
		if (grade >= 76) {
			return new Grade(2);
		}
		if (grade >= 73) {
			return new Grade(3);
		}
		if (grade >= 70) {
			return new Grade(4);
		}
		if (grade >= 67) {
			return new Grade(5);
		}
		if (grade >= 65) {
			return new Grade(6);
		}
		if (grade >= 62) {
			return new Grade(7);
		}
		if (grade >= 60) {
			return new Grade(8);
		}
		if (grade >= 57) {
			return new Grade(9);
		}
		if (grade >= 55) {
			return new Grade(10);
		}
		if (grade >= 52) {
			return new Grade(11);
		}
		if (grade >= 50) {
			return new Grade(12);
		}
		if (grade >= 47) {
			return new Grade(13);
		}
		if (grade >= 45) {
			return new Grade(14);
		}
		if (grade >= 42) {
			return new Grade(15);
		}
		if (grade >= 40) {
			return new Grade(16);
		}
		if (grade >= 35) {
			return new Grade(17);
		}
		if (grade >= 30) {
			return new Grade(18);
		}
		if (grade >= 0) {
			return new Grade(19);
		}
		// return when grade less than 0
		return new Grade(20);
	}
}
