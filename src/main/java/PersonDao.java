import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonDao {

    public void addPerson(Person p) throws Exception {
        Connection con = DBUtil.getConnection();
        Statement stmt = con.createStatement();
        String sql = "INSERT INTO `zsdb`.`person` (`id`, `name`, `age`, `birthday`, `remark`) VALUES (?, ?, ?, ?, ?)";
        //预编译sql语句
        PreparedStatement psmt = con.prepareStatement(sql);
        //对应sql语句，给sql传参数
        //临时写死为1
        psmt.setInt(1, 1);
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

    /**
     * 更新
     */
    public void updatePerson(Person p) throws SQLException {
        Connection con = DBUtil.getConnection();
        String sql = "UPDATE `person` SET `birthday`=? WHERE `id`=?";
        //预编译sql
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setDate(1, new Date(p.getBirthday().getTime()));
        psmt.setInt(2, p.getId());
        //执行sql语句
        psmt.execute();
    }
    public void deletePerson(Integer id) throws SQLException {
        Connection con = DBUtil.getConnection();
        String sql = "delete from zsdb.person where id = ?";
        //预编译sql
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1, id);
        //执行sql
        psmt.execute();
    }

    public List<Person> query() throws Exception {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from zsdb.person");
        List<Person> pl = new ArrayList<Person>();
        Person p = null;
        while (rs.next()) {
            p = new Person();
            p.setName(rs.getString("name"));
            p.setId(rs.getInt("id"));
            p.setAge(rs.getInt("age"));
            p.setBirthday(rs.getDate("birthday"));
//            p.setRemark(JSONObject.parseObject(String.valueOf(rs.getMetaData())));
            pl.add(p);
        }
        return pl;
    }

    /**
     * 查询单人（根据id）
     * @return
     */
    public Person get(Integer id) throws SQLException {
        Person p = null;
        Connection con = DBUtil.getConnection();
        String sql = " select * from person where id = ?";
        //预编译sql
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1, id);
        /* execute是用于插入、更新、删除； executeQuery是用于查询操作 */
        ResultSet rs = psmt.executeQuery();
        while (rs.next()){
            p = new Person();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setAge(rs.getInt("age"));
            p.setBirthday(rs.getDate("birthday"));
            p.setRemark(JSONObject.parseObject(String.valueOf(rs.getString("remark"))));
        }
        return p;
    }

    /**
     * 根据姓名和年龄查询单个人
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Person> get(List<Map<String, Object>> params) throws Exception {
        List<Person> result = new ArrayList<Person>();
        Connection con = DBUtil.getConnection();
        StringBuffer sb = new StringBuffer();
        sb.append("select * from person where 1 = 1 ");
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                Map<String, Object> map = params.get(i);
                //查询、关系、值通过参数传递
                sb.append(" and " + map.get("name") + " " + map.get("rela") + " " + map.get("value") + " ");
            }
        }
        PreparedStatement psmt = con.prepareStatement(sb.toString());
        System.out.println(sb.toString());
        ResultSet rs = psmt.executeQuery();
        Person p = new Person();
        while (rs.next()) {
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setAge(rs.getInt("age"));
            p.setBirthday(rs.getDate("birthday"));
            p.setRemark(JSONObject.parseObject(String.valueOf(rs.getString("remark"))));
            result.add(p);
        }
        return result;
    }
}
