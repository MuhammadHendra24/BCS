/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

public class ListCustomer {
    
    public String id;
    public String customerName;
    public String customerAddress;
    public String customerNpwp;
    public String customerSkep;
    public String addBy;
    public String addedDate;

    public ListCustomer(String id, String customerName, String customerAddress, String customerNpwp, String customerSkep, String addBy, String addedDate) {
        this.id = id;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerNpwp = customerNpwp;
        this.customerSkep = customerSkep;
        this.addBy = addBy;
        this.addedDate = addedDate;
        
        
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerNpwp() {
		return customerNpwp;
	}

	public void setCustomerNpwp(String customerNpwp) {
		this.customerNpwp = customerNpwp;
	}

	public String getCustomerSkep() {
		return customerSkep;
	}

	public void setCustomerSkep(String customerSkep) {
		this.customerSkep = customerSkep;
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
