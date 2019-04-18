package ManagementEE;

import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ManagementEJB {

    @PersistenceContext(unitName = "managementPU")
    private EntityManager entityManager;

    /**
     * Creates new entity 'EmpEntity'
     *
     * All parameters must be filled
     *
     * The maximum length of each parameter except the 'age' and 'salary' is 21 characters
     *
     * @param key - must be unique
     * */
     public boolean createEmployee(String company, String name, String key, String position, int age, int salary){
        if(StringUtils.isEmpty(company) || company.length()>=22  ||
           StringUtils.isEmpty(name)    || name.length()>=22     ||
           StringUtils.isEmpty(key)     || key.length()>=22      ||
           StringUtils.isEmpty(position)|| position.length()>=22 ||
           StringUtils.isEmpty(Integer.toString(age)) ||
           StringUtils.isEmpty(Integer.toString(salary))){
            return false;
        }

        EmpEntity empEntity = entityManager.find(EmpEntity.class, key);
        if(empEntity != null){
            return false;
        }

        empEntity = new EmpEntity();
        empEntity.setCompany(company);
        empEntity.setName(name);
        empEntity.setKey(key);
        empEntity.setPosition(position);
        empEntity.setAge(age);
        empEntity.setSalary(salary);
        entityManager.persist(empEntity);

        return true;
    }

    /**
     * @return full list of entities 'EmpEntity'
     * */
     public List<EmpEntity> getAll(){
        Query query = entityManager.createQuery("select entity from EmpEntity entity");
         //noinspection unchecked
         return query.getResultList();
    }

    /**
     * @return list of entities where 'company' parameter equals to 'company' parameter from this method
     * */
     public List<EmpEntity> getFilterByCompany(String company){
        String q = "select entity from EmpEntity entity where entity.company = '" + company + "'";
        Query query = entityManager.createQuery(q);
         //noinspection unchecked
         return query.getResultList();
    }

    /**
     * @return entity 'EmpEntity' where 'key' parameter equals to 'key' parameter from this method
     * */
     public List<EmpEntity> getFindByKey(String key){
        String q = "select entity from EmpEntity entity where entity.key = '" + key + "'";
        Query query = entityManager.createQuery(q);
         //noinspection unchecked
         return query.getResultList();
    }

    /**
     * Removes entity 'EmpEntity' where 'key' parameter equals to 'key' parameter from this method
     * */
    public void getRemoved(String key){
         String q = "delete from EmpEntity entity where entity.key = '" + key + "'";
         Query query = entityManager.createQuery(q);
         query.executeUpdate();
         entityManager.clear();
    }

}
