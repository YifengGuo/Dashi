package db;

public class DBUtil {
    private static final String HOSTNAME = "localhost";
    private static final String PORT_NUM = "3306";// change it to your mysql port number
    public static final String DB_NAME = "laiproject";
    private static final String USERNAME = "root";
    //private static final String PASSWORD = "root"; //for EC2
    private static final String PASSWORD = "fflovexx123";//project password
    public static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT_NUM + "/" + DB_NAME
   			 + "?user=" + USERNAME + "&password=" + PASSWORD + "&autoreconnect=true" + "&useSSL=false";
    
}
