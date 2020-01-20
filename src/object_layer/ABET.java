package object_layer;

public class ABET {

	String id;
	String description;
	String tag;
	String isCurrentlyUsed;
	String categoryID;
	String categoryName;
	String subcategoryID;
	
	public ABET(String id, String description, String tag, String isCurrentlyUsed, String categoryID, String categoryName, String subcategoryID) {
		this.id = id;
		this.description = description;
		this.tag = tag;
		this.isCurrentlyUsed = isCurrentlyUsed;
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.subcategoryID = subcategoryID;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getIsCurrentlyUsed() {
		return isCurrentlyUsed;
	}

	public void setIsCurrentlyUsed(String isCurrentlyUsed) {
		this.isCurrentlyUsed = isCurrentlyUsed;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(String subcategoryID) {
		this.subcategoryID = subcategoryID;
	}

}
