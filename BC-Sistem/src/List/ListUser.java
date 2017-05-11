
package List;


public class ListUser {
    
    public String Uid;
    public String Uname; 

    public ListUser(String Uid, String Uname) {
        this.Uid = Uid;
        this.Uname = Uname;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String Uname) {
        this.Uname= Uname;
    }
    
}
