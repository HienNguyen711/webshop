package project.webshop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.webshop.model.dto.RoleDto;
import project.webshop.service.RoleService;

@RestController
public class RoleController {
    //
    @Autowired
    private RoleService roleService;


    // edit role
    // PUT /admin/role/update
    @RequestMapping(value = "admin/role/update", method = RequestMethod.PUT)
    public ResponseEntity<?> editRole(@RequestBody RoleDto roleDto,
                                      @RequestHeader(name = "Authorization") String token) throws Exception {
        RoleDto existingRoleDto;
        try {
            existingRoleDto = roleService.editRole(roleDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingRoleDto, HttpStatus.OK);
    }



    // add role
    // POST admin/role/add
    @RequestMapping(value = "admin/role/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto,
                                     @RequestHeader(name = "Authorization") String token) throws Exception {
        RoleDto existingRoleDto;
        try {
            existingRoleDto = roleService.addRole(roleDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingRoleDto, HttpStatus.OK);
    }




    // delete role
    // DELETE admin/role/{roleId}

    @RequestMapping(value = "admin/role/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> addRole(@PathVariable Long id,
                                     @RequestHeader(name = "Authorization") String token) throws Exception {
        // try catch
        try {
            roleService.deleteRole(id);
        } catch (Exception e) {
            // internal server error
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return ResponseEntity.ok().build();
    }
}
