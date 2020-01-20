package object_layer;

public class Instrument {

	String id;
	String gradeAverage;
	String type;
	String description;
	String weight;
	
	public Instrument(String id, String gradeAverage, String type, String description, String weight) {
		super();
		this.id = id;
		this.gradeAverage = gradeAverage;
		this.type = type;
		this.description = description;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGradeAverage() {
		return gradeAverage;
	}

	public void setGradeAverage(String gradeAverage) {
		this.gradeAverage = gradeAverage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	
}
