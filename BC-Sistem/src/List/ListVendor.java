
package List;


public class ListVendor {
    
    public String vendorId;
    public String vendorName;
    public String vendorPhoneNumber;
    public String vendorAddress;
    public String vendorDescription;
    public String creatorId;
    public String dataOfjoining;

    public ListVendor(String vendorId, String vendorName) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }



    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public String getVendorDescription() {
        return vendorDescription;
    }

    public String getDataOfjoining() {
        return dataOfjoining;
    }

    public ListVendor(String vendorId, String vendorName, String vendorPhoneNumber, String vendorAddress, String vendorDescription, String dataOfjoining) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorPhoneNumber = vendorPhoneNumber;
        this.vendorAddress = vendorAddress;
        this.vendorDescription = vendorDescription;
        this.dataOfjoining = dataOfjoining;
    }





    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

	

}
