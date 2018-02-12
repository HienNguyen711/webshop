package project.webshop.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import project.webshop.model.entity.user.Role;

@Service("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Long> {
}
