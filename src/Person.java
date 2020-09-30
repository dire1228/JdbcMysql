import com.alibaba.fastjson.JSON;

import java.util.Date;

public class Person {

    private String id;
    private String name;
    private Integer age;
    private Date birthday;
    private JSON remark;

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", remark=" + remark +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public JSON getRemark() {
        return remark;
    }

    public void setRemark(JSON remark) {
        this.remark = remark;
    }
}
