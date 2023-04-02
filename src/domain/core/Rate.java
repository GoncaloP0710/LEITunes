package domain.core;

/**
 * enum represent the possible values to music ratings
 */
public enum Rate {
	LOW,
	MID,
	HIGH;

	/**
	 * incrises the rate
	 * 
	 * @return the higher version of the value of the rank
	 */
	public Rate incRating() {
		switch (this) {
			case LOW:
				return MID;
			case MID:
				return HIGH;
			default:
				return this;
		}
	}

	/**
	 * devreases the rate
	 * 
	 * @return the lower version of value of the rank
	 */
	public Rate decRating() {
		switch (this) {
			case MID:
				return LOW;
			case HIGH:
				return MID;
			default:
				return this;
		}
	}

	/**
	 * verifies if the rate is higher or the same of the one of the param
	 * 
	 * @param rating to be compared
	 * @return true if the rate is higher or the same of the one of the param
	 */
	public boolean isHigherOrSame(Rate rating) {
		switch (rating) {
			case MID:
				if (rating == HIGH) {
					return false;
				}
				return true;
			case LOW:
				if (rating == MID) {
					return false;
				}
			default:
				return true;
		}
	}

	/**
	 * verifies if the rate is higher than the one of the param
	 * 
	 * @param rating to be compared
	 * @return true if the rate is higher than the one of the param
	 */
	public boolean isHigher(Rate rating) {
		switch (rating) {
			case MID:
				if (rating == LOW) {
					return true;
				}
				return true;
			case HIGH:
				if (rating == MID || rating == LOW) {
					return true;
				}
			default:
				return false;
		}
	}

	/**
	 * verifies if the rate is lower than the one of the param
	 * 
	 * @param rating to be compared
	 * @return true if the rate is lower than the one of the param
	 */
	public boolean isLower(Rate rating) {
		switch (rating) {
			case MID:
				if (rating == HIGH) {
					return true;
				}
				return true;
			case LOW:
				if (rating == MID || rating == HIGH) {
					return true;
				}
			default:
				return false;
		}
	}

}
