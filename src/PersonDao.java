import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    public void addPerson(Person p) throws Exception {
        Connection con = DBUtil.getConnection();
        Statement stmt = con.createStatement();
        String sql = "INSERT INTO `zsdb`.`person` (`id`, `name`, `age`, `birthday`, `remark`) VALUES (?, ?, ?, ?, ?)";
        //预编译sql语句
        PreparedStatement psmt = con.prepareStatement(sql);
        //对应sql语句，给sql传参数
        psmt.setString(1, p.getId());
        psmt.setString(2, p.getName());
        psmt.setInt(3, p.getAge());
        psmt.setDate(4, new Date(p.getBirthday().getTime()));
        String str = JSONObject.toJSONString(p.getRemark());
        psmt.setString(5, str);
        //执行sql
        psmt.execute();

        /**
         26          * prepareStatement这个方法会将SQL语句加载到驱动程序conn集成程序中，但是并不直接执行
         27          * 而是当它调用execute()方法的时候才真正执行；
         28          *
         29          * 上面SQL中的参数用?表示，相当于占位符，然后在对参数进行赋值。
         30          * 当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。
         31          * 这样就会减少对数据库的操作
         32          */

    }
    public void updatePerson() {}
    public void deletePerson() {}

    public List<Person> query() throws Exception {

        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from zsdb.person");
        List<Person> pl = new ArrayList<Person>();
        Person p = null;
        while (rs.next()) {
            p = new Person();
            p.setName(rs.getString("name"));
            p.setId(rs.getString("id"));
            p.setAge(rs.getInt("age"));
            pl.add(p);
        }
        return pl;
    }

    public Person get() {
        return null;
    }


}
