package domain.core;

/**
 * objects represent the possible values to music ratings
 */
public enum Rate {
	LOW,
	MID,
	HIGH;
	

	/**
	 * 
	 * @param rating
	 * @return
	 */
	public Rate incRating() {
		switch(this) {
	    case LOW:
	      return MID;
	    case MID:
	       return HIGH;
		default:
			return this;
		}
	}
	
	/**
	 * 
	 * @param rating
	 * @return
	 */
	public Rate decRating() {
		switch(this) {
	    case MID:
	      return LOW;
	    case HIGH:
	       return MID;
		default:
			return this;
		}
	}
	
	/**
	 * 
	 * @param rating
	 * @return
	 */
	public boolean isHigherOrSame(Rate rating) {
		switch(rating) {
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
	 * 
	 * 
	 * @param rating
	 * @return
	 */
	public boolean isHigher(Rate rating) {
		switch(rating) {
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
	 * 
	 * 
	 * @param rating
	 * @return
	 */
	public boolean isLower(Rate rating) {
		switch(rating) {
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
