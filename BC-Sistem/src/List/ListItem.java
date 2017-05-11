/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

public class ListItem {
    
    public String id;
    public String itemCode;
    public String itemDescription;
    public String um;
    public String addBy;
    public String addedDate;

    public ListItem(String id, String itemCode, String itemDescription, String um, String addBy, String addedDate) {
        this.id = id;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.um = um;
        this.addBy = addBy;
        this.addedDate = addedDate;
  
}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
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