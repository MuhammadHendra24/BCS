/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

public class ListIncomingOutgoing {

    String id;
    String sortdokumen;
    String noDoc;
    String dateNoDoc;
    String vendor;
    String noInvoice;
    String noDo;
    String dateNoDo;
    String itemCode;
    String description;
    String kurenci;
    String U_m;
    String qtygood;
    String qtyreject;
    String price;
    String grosWeigh; 
    String addBy;
    String addedDate;

    public ListIncomingOutgoing(String id , String sortdokumen, String noDoc, String dateNoDoc, String vendor, String noInvoice, String noDo, String dateNoDo, String itemCode, String description, String kurenci, String U_m, String qtygood, String qtyreject,String price, String grosWeigh, String addBy, String addedDate) {
        this.id = id;
        this.sortdokumen= sortdokumen;
        this.noDoc = noDoc;
        this.dateNoDoc = dateNoDoc;
        this.vendor = vendor;
        this.noInvoice = noInvoice;
        this.noDo = noDo;
        this.dateNoDo = dateNoDo;
        this.itemCode = itemCode;
        this.description = description;
        this.kurenci = kurenci;
        this.U_m = U_m;
        this.qtygood= qtygood;
        this.qtyreject = qtyreject;
        this.price = price;
        this.grosWeigh = grosWeigh;
        this.addBy = addBy;
        this.addedDate = addedDate;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSortdokumen() {
		return sortdokumen;
	}

	public void setSortdokumen(String sortdokumen) {
		this.sortdokumen = sortdokumen;
	}

	public String getNoDoc() {
		return noDoc;
	}

	public void setNoDoc(String noDoc) {
		this.noDoc = noDoc;
	}

	public String getDateNoDoc() {
		return dateNoDoc;
	}

	public void setDateNoDoc(String dateNoDoc) {
		this.dateNoDoc = dateNoDoc;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getNoInvoice() {
		return noInvoice;
	}

	public void setNoInvoice(String noInvoice) {
		this.noInvoice = noInvoice;
	}

	public String getNoDo() {
		return noDo;
	}

	public void setNoDo(String noDo) {
		this.noDo = noDo;
	}

	public String getDateNoDo() {
		return dateNoDo;
	}

	public void setDateNoDo(String dateNoDo) {
		this.dateNoDo = dateNoDo;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKurenci() {
		return kurenci;
	}

	public void setKurenci(String kurenci) {
		this.kurenci = kurenci;
	}

	public String getU_m() {
		return U_m;
	}

	public void setU_m(String u_m) {
		U_m = u_m;
	}

	public String getQtygood() {
		return qtygood;
	}

	public void setQtygood(String qtygood) {
		this.qtygood = qtygood;
	}

	public String getQtyreject() {
		return qtyreject;
	}

	public void setQtyreject(String qtyreject) {
		this.qtyreject = qtyreject;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGrosWeigh() {
		return grosWeigh;
	}

	public void setGrosWeigh(String grosWeigh) {
		this.grosWeigh = grosWeigh;
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

	