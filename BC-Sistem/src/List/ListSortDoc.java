package List;

public class ListSortDoc {

    public String Id;
    public String sortDocName;
    public String sortDocDescription;
    public String addBy;
    public String addedDate;

    public ListSortDoc(String Id, String sortDocName, String sortDocDescription, String addBy, String addedDate) {
        this.Id = Id;
        this.sortDocName = sortDocName;
        this.sortDocDescription = sortDocDescription;
        this.addBy = addBy;
        this.addedDate = addedDate;
        
    }

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getSortDocName() {
		return sortDocName;
	}

	public void setSortDocName(String sortDocName) {
		this.sortDocName = sortDocName;
	}

	public String getSortDocDescription() {
		return sortDocDescription;
	}

	public void setSortDocDescription(String sortDocDescription) {
		this.sortDocDescription = sortDocDescription;
	}

	public String getAddBy() {
		return addBy;
	}

	public void setAddBy(String addBy) {
		this.addBy = addBy;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}
}
	