package project.webshop.service.impl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.webshop.model.dto.RoleDto;
import project.webshop.model.entity.user.Role;
import project.webshop.model.entity.user.RoleName;
import project.webshop.repository.RoleRepository;
import project.webshop.service.RoleService;
import project.webshop.utils.Constants;
import project.webshop.utils.Converter;


@Component
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;

    @Override
    public RoleDto editRole(@NonNull RoleDto roleDto) throws Exception {
        Role role = roleRepository.findOne(roleDto.getId());
        if (role == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_ROLE);
        }
        if (RoleName.valueOf(roleDto.getName().toUpperCase()) == null) {
            throw new Exception(Constants.MESSAGE_NOT_VALID_ROLE);
        }
        role.setName(roleDto.getName().toUpperCase());
        roleRepository.save(role);
        return roleDto;
    }

    // add role to Role table
    @Override
    public RoleDto addRole(@NonNull RoleDto roleDto) throws Exception {
        Role role = Converter.toRole(roleDto);
        if (RoleName.valueOf(role.getName().toUpperCase()) == null) {
            throw new Exception(Constants.MESSAGE_NOT_VALID_ROLE);
        }
        roleRepository.save(role);
        roleDto.setId(role.getId());
        return roleDto;
    }

    // delete role from Role table
    @Override
    public void deleteRole(Long id) {
        roleRepository.delete(id);
    }
}
