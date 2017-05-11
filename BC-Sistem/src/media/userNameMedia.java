
package media;


public class userNameMedia {
    
   String Id;
   String usrName;

    public userNameMedia() {
    }

    public userNameMedia(String Id) {
        this.Id = Id;
    }

    public userNameMedia(String id, String usrName) {
        this.Id = id;
        this.usrName = usrName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }
    
}
