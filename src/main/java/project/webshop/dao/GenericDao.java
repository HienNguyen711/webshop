package project.webshop.dao;

import org.springframework.stereotype.Service;

@Service
public interface GenericDao<T> {
    T save(T entity);
    T find(Long id);
    T update(T entity);
    void delete(Long id);
}
