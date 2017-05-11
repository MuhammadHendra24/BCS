/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

public class ListCurrency {
    
    public String id;
    public String currency;
    public String currencyDescription;
    public String addBy;
    public String addedDate;

    public ListCurrency(String id, String currency, String currencyDescription, String addBy, String addedDate) {
        this.id = id;
        this.currency = currency;
        this.currencyDescription = currencyDescription;
        this.addBy = addBy;
        this.addedDate = addedDate;
        
        
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyDescription() {
		return currencyDescription;
	}

	public void setCurrencyDescription(String currencyDescription) {
		this.currencyDescription = currencyDescription;
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
