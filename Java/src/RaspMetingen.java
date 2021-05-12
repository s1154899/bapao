import java.util.ArrayList;

public class RaspMetingen {

    private DatabaseCon[] conns;
    public Meting[] metingen;

    public Meting[] getMetingen(){
       return metingen;
    }

    public RaspMetingen(DatabaseCon[] databaseCon){
        this.conns = databaseCon;
    }



}
