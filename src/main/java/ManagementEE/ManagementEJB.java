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
     * @param key - must be unique
     * */
     public boolean createEmployee(String company, String name, String key, String position, int age, int salary){
        if(StringUtils.isEmpty(company) ||
           StringUtils.isEmpty(name)    ||
           StringUtils.isEmpty(key)     ||
           StringUtils.isEmpty(position)||
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
     * returns full list of entities 'EmpEntity'
     * */
     public List<EmpEntity> getAll(){
        Query query = entityManager.createQuery("select entity from EmpEntity entity");
         //noinspection unchecked
         return query.getResultList();
    }

    /**
     * returns list of entities where 'company' parameter equals to 'company' parameter from this method
     * */
     public List<EmpEntity> getFilterByCompany(String company){
        String q = "select entity from EmpEntity entity where entity.company = '" + company + "'";
        Query query = entityManager.createQuery(q);
         //noinspection unchecked
         return query.getResultList();
    }

    /**
     * returns entity 'EmpEntity' where 'key' parameter equals to 'key' parameter from this method
     * */
     public List<EmpEntity> getFindByKey(String key){
        String q = "select entity from EmpEntity entity where entity.key = '" + key + "'";
        Query query = entityManager.createQuery(q);
         //noinspection unchecked
         return query.getResultList();
    }

}
