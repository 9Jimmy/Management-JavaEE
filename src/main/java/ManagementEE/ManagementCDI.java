package ManagementEE;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ManagementCDI implements Serializable {

    private String company;
    private String name;
    private String key;
    private String position;
    private int age;
    private int salary;

    @EJB
    private ManagementEJB managementEJB;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void createEmployee(){
        boolean createSuccess = managementEJB.createEmployee(company, name, key, position, age, salary);
    }

    public List<EmpEntity> getAll(){
        return managementEJB.getAll();
    }

    public List<EmpEntity> getFilterByCompany(){
        return managementEJB.getFilterByCompany(company);
    }

    public List<EmpEntity> getFindByKey(){
        return managementEJB.getFindByKey(key);
    }
}
