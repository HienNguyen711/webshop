package project.webshop.dao.impl;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.webshop.dao.CategoryDao;
import project.webshop.model.entity.Category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@Transactional(noRollbackFor = Exception.class)
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {

    @Override
    public List<Category> getRootCategories() {
        CriteriaBuilder builder = getBuilder();
        CriteriaQuery<Category> query = builder.createQuery(getPersistenceClass());
        Root<Category> root = query.from(getPersistenceClass());
        query.where(
                builder.isNull(root.get("parent"))
        );
        return getEntityManager().createQuery(query).getResultList();
    }

    @Override
    public List<Category> getSubcategories(Long parentId) {
        CriteriaBuilder builder = getBuilder();
        CriteriaQuery<Category> query = builder.createQuery(getPersistenceClass());
        Root<Category> root = query.from(getPersistenceClass());
        query.where(
                builder.equal(root.get("parent"), parentId)
        );
        return getEntityManager().createQuery(query).getResultList();
    }

    @Override
    protected Class<Category> getPersistenceClass() {
        return Category.class;
    }
}
