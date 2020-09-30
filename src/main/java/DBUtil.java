import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {

    private static final String URL = "jdbc:mysql://192.168.72.39:3306/zsdb";
    private static final String NAME = "root";
    private static final String PASSWORD = "123456";

    private static Connection conn = null;

    //静态代码块（将加载驱动和连接数据库放在静态块中）
    static{
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2.获得数据库连接
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

//    public static void main(String[] args) throws Exception {
//
//        //3.通过数据库连接操作数据库，实现增删改查
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM zsdb.person;");
//        while (rs.next()) {
//            System.out.println(rs.getString("id") + " " + rs.getString("name") + " " + rs.getInt("age"));
//        }
//    }
}
