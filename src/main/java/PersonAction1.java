import java.util.List;
import java.util.Map;

/**
 * 控制层，连接模型层和视图层
 */
public class PersonAction1 {

    public void add(Person person) throws Exception {
        PersonDao dao = new PersonDao();
        dao.addPerson(person);
    }

    public void edit(Person person) throws Exception {
        PersonDao dao = new PersonDao();
        dao.updatePerson(person);
    }

    public void delete(Integer id) throws Exception {
        PersonDao dao = new PersonDao();
        dao.deletePerson(id);
    }

    /**
     * 查看所有
     * @return 查询结果list
     * @throws Exception
     */
    public List<Person> query() throws Exception {
        PersonDao dao = new PersonDao();
        return dao.query();
    }

    /**
     * 查看单人（根据ID）
     * @param id
     * @return
     * @throws Exception
     */
    public Person get(Integer id) throws Exception {
        PersonDao dao = new PersonDao();
        return dao.get(id);
    }

    /**
     * 根据姓名等信息查看单人
     * @param params
     * @return
     * @throws Exception
     */
    public List<Person> get(List<Map<String, Object>> params) throws Exception {
        PersonDao dao = new PersonDao();
        return dao.get(params);
    }

}
