import com.alibaba.fastjson.JSONObject;

import java.sql.SQLException;
import java.util.*;

public class PersonAction {

//    public static void main(String[] args) throws Exception {
////        insert();
////        query();
//        queryOnePerson();
//    }

    private static void queryOnePerson() throws Exception {
         PersonDao pd = new PersonDao();
         List<Map<String, Object>> params = new ArrayList<>();
         Map<String, Object> param = new HashMap<>();
         param.put("name", "name");
         param.put("rela", "like");
         //因为变量是字符串形式，所以多加一个单引号
         param.put("value", "'张三'");
         params.add(param);
        List<Person> list = pd.get(params);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    static void  query() throws Exception {
        PersonDao pd = new PersonDao();
        List<Person> pl = pd.query();
        for (Person p : pl) {
            String str = JSONObject.toJSONString(p.getRemark());
            System.out.println(p.getId() + "\t" + p.getName() + "\t" + p.getAge() + "\t" + p.getBirthday() + "\t" + str);
        }
    }

     static void insert() throws Exception {
        PersonDao pd = new PersonDao();
        Person p = new Person();
        p.setId(4);
        p.setName("孙峰");
        p.setAge(33);
        p.setBirthday(new Date());
        String json = "{\"status\":200,\"data\":{\"备注\":\"成功\"}}";
        p.setRemark(JSONObject.parseObject(json));
        pd.addPerson(p);
    }


}
