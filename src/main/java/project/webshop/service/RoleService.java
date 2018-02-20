package project.webshop.service;


import org.springframework.stereotype.Service;
import project.webshop.model.dto.RoleDto;

@Service
public interface RoleService {
    RoleDto editRole(RoleDto roleDTO) throws Exception;
    RoleDto addRole(RoleDto roleDTO) throws Exception;
    void deleteRole(Long id);
    // find role
    RoleDto findRoleById(Long id);
}
