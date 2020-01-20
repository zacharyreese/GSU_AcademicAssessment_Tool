package object_layer;

public class Assignment {
	
	String id;
	String lowestPDF;
	String medianPDF;
	String highestPDF;
	String lowestGrade;
	String medianGrade;
	String highestGrade;
	String averageGrade;
	
	public Assignment(String id, String lowestPDF, String medianPDF, String highestPDF, String lowestGrade,
			String medianGrade, String highestGrade, String averageGrade) {
		super();
		this.id = id;
		this.lowestPDF = lowestPDF;
		this.medianPDF = medianPDF;
		this.highestPDF = highestPDF;
		this.lowestGrade = lowestGrade;
		this.medianGrade = medianGrade;
		this.highestGrade = highestGrade;
		this.averageGrade = averageGrade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLowestPDF() {
		return lowestPDF;
	}

	public void setLowestPDF(String lowestPDF) {
		this.lowestPDF = lowestPDF;
	}

	public String getMedianPDF() {
		return medianPDF;
	}

	public void setMedianPDF(String medianPDF) {
		this.medianPDF = medianPDF;
	}

	public String getHighestPDF() {
		return highestPDF;
	}

	public void setHighestPDF(String highestPDF) {
		this.highestPDF = highestPDF;
	}

	public String getLowestGrade() {
		return lowestGrade;
	}

	public void setLowestGrade(String lowestGrade) {
		this.lowestGrade = lowestGrade;
	}

	public String getMedianGrade() {
		return medianGrade;
	}

	public void setMedianGrade(String medianGrade) {
		this.medianGrade = medianGrade;
	}

	public String getHighestGrade() {
		return highestGrade;
	}

	public void setHighestGrade(String highestGrade) {
		this.highestGrade = highestGrade;
	}

	public String getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(String averageGrade) {
		this.averageGrade = averageGrade;
	}
	
	
	
	
	
}
