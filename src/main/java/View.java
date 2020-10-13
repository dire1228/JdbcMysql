import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class View {

    //提示语
    private static final String CONTEXT = "欢迎来到女神禁区：\n" +
            "下面是女神禁区的功能列表：\n" +
            "[MAIN/M]:主菜单\n" +
            "[QUERY/Q]:查看全部女神的信息\n" +
            "[GET/G]:查看某位女神的详细信息\n" +
            "[ADD/A]:添加女神信息\n" +
            "[UPDATE/U]:更新女神信息\n" +
            "[DELETE/D]:删除女神信息\n" +
            "[SEARCH/S]:查询女神信息(根据姓名、手机号来查询)\n" +
            "[EXIT/E]:退出女神禁区\n" +
            "[BREAK/B]:退出当前功能，返回主菜单";

    //操作标记
    private static final String OPERATION_MAIN = "MAIN";
    private static final String OPERATION_QUERY = "QUERY";
    private static final String OPERATION_GET = "GET";
    private static final String OPERATION_ADD = "ADD";
    private static final String OPERATION_UPDATE = "UPDATE";
    private static final String OPERATION_DELETE = "DELETE";
    private static final String OPERATION_SEARCH = "SERRCH";
    private static final String OPERATION_EXIT = "EXIT";
    private static final String OPERATION_BREAK = "BREAK";

    public static void main(String[] args) {
        //输出提示
        System.out.println(CONTEXT);
        //保持程序一直运行while(true) {}
        //接收控制台的输入
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        PersonAction1 action = new PersonAction1();

        //记住上一次的请求
        String prenious = null;
        //标记步骤
        Integer step = 1;
        //scanner有值才会循环
        while (scanner.hasNext()) {
            String in = scanner.next();
            if ((OPERATION_EXIT.equals(in.toUpperCase())) || (OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase()))) {
                System.out.println("----退出成功！");
                break;
            } else if ((OPERATION_QUERY.equals(in.toUpperCase())) || (OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase()))) {
                try {
                    List<Person> list = action.query();
                    for (Person p : list) {
                        System.out.println(p);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ((OPERATION_ADD.equals(in.toUpperCase())) || (OPERATION_ADD.substring(0, 1).equals(in.toUpperCase())) || (OPERATION_ADD.equals(prenious))) {
                prenious = OPERATION_ADD;
                if (1 == step) {
                    System.out.println("请输入姓名：");
                } else if (2 == step) {
                    person.setName(in);
                    System.out.println("请输入年龄：");
                }  else if (3 == step) {
                    person.setAge(Integer.valueOf(in));
                    System.out.println("请输入生日【yyyy-MM-dd】：");
                }   else if (4 == step) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date birthday;
                    try {
                        birthday = sdf.parse(in);
                        person.setBirthday(birthday);
                        try {
                            action.add(person);
                            System.out.println("新增成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("新增失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                step++;
            } else {
                System.out.println("您输入的值为："+in);
            }
        }
    }

}
