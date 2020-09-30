import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.List;

public class PersonAction {

    public static void main(String[] args) throws Exception {
//        insert();
        query();
    }

    static void  query() throws Exception {
        PersonDao pd = new PersonDao();
        List<Person> pl = pd.query();
        for (Person p : pl) {
            System.out.println(p.getId() + "\t" + p.getName() + "\t" + p.getAge() + "\t" + p.getBirthday() + "\t" + p.getRemark());
        }
    }

     static void insert() throws Exception {
        PersonDao pd = new PersonDao();
        Person p = new Person();
        p.setId("4");
        p.setName("孙峰");
        p.setAge(33);
        p.setBirthday(new Date());
        String json = "{\"status\":200,\"data\":{\"备注\":\"成功\"}}";
        p.setRemark(JSONObject.parseObject(json));
        pd.addPerson(p);
    }


}
