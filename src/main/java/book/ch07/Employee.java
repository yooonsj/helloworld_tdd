package book.ch07;

/**
 * Created by Sangjun on 2015. 8. 14..
 */
public class Employee {
    private String name;
    private String empid;
    private String id;
    private String position;

    public Employee(String name, String empid, String id, String position) {
        this.name = name;
        this.empid = empid;
        this.id = id;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
