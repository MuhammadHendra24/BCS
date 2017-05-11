package DAL;


import List.ListIncomingOutgoing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IncOutGoing {
    
    public String id;
    public String sortdokumen;
    public String noDoc;
    public String dateNoDoc;
    public String vendor;
    public String noInvoice;
    public String noDo;
    public String dateNoDo;
    public String itemCode;
    public String description;
    public String kurenci;
    public String U_m;
    public String qtygood;
    public String qtyreject;
    public String price;
    public String grosWeigh;
    public String addBy;
    public String addedDate;
    public String userId;
    public String userName;

    
    
    
    public ObservableList<ListIncomingOutgoing> listIncoming = FXCollections.observableArrayList();
   

}
