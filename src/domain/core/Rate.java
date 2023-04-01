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
	public Rate incRating(Rate rating) {
		switch(rating) {
	    case LOW:
	      return MID;
	    case MID:
	       return HIGH;
		default:
			return rating;
		}
	}
	
	/**
	 * 
	 * @param rating
	 * @return
	 */
	public Rate decRating(Rate rating) {
		switch(rating) {
	    case MID:
	      return LOW;
	    case HIGH:
	       return MID;
		default:
			return rating;
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
}
