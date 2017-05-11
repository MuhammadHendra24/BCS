package DAL;


import List.ListIncomingOutgoing;
import List.ListOutgoing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Outgoing {
    
    public String id;
    public String sortdokumen;
    public String noDoc;
    public String dateNoDoc;
    public String customer;
    public String noInvoice;
    public String noDo;
    public String dateNoDo;
    public String itemCode;
    public String description;
    public String kurenci;
    public String U_m;
    public String qtygood;
    public String price;
    public String grosWeigh;
    public String addBy;
    public String addedDate;
    public String userId;
    public String userName;

    
    
    
    public ObservableList<ListOutgoing> listoutgoing = FXCollections.observableArrayList();
   

}
