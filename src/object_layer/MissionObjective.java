package object_layer;

public class MissionObjective {

	String id;
	String description;
	String year;
	String isCurrentlyUsed;
	String tag;
	
	public MissionObjective(String id, String description, String year, String isCurrentlyUsed, String tag) {
		super();
		this.id = id;
		this.description = description;
		this.year = year;
		this.isCurrentlyUsed = isCurrentlyUsed;
		this.tag = tag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getIsCurrentlyUsed() {
		return isCurrentlyUsed;
	}

	public void setIsCurrentlyUsed(String isCurrentlyUsed) {
		this.isCurrentlyUsed = isCurrentlyUsed;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	

}
