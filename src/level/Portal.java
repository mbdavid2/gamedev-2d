package level;

public class Portal {
	
	Integer start;
	Integer end;
	
	public Portal(Integer start, Integer end) {
		this.start = start;
		this.end = end;
	}
	
	public boolean isInPortalRange(float position) {
		return position > start && position < end;
	}
	
	public Integer getStart() {
		return start;
	}
	
	public Integer getEnd() {
		return end;
	}
}
