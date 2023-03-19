package domain.core;

public enum Rate {
	LOW,
	MID,
	HIGH;
	

	public Rate incRating(Rate rating) {
		switch(rating) {
	    case LOW:
	      return MID;
	    case MID:
	       return HIGH;
		}
		return rating;
	}
	
	public Rate decRating(Rate rating) {
		switch(rating) {
	    case MID:
	      return LOW;
	    case HIGH:
	       return MID;
		}
		return rating;
	}
}
