package project.webshop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.webshop.service.RoleService;

@RestController
public class RoleController {
    //
    @Autowired
    private RoleService roleService;


    // edit role


    // add role



    // delete role
    // DELETE admin/role/{roleId}

    @RequestMapping(value = "admin/role/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> addRole(@PathVariable Long id,
                                     @RequestHeader(name = "Authorization") String token) throws Exception {
        // try catch
        try {
            roleService.deleteRole(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return ResponseEntity.ok().build();
    }
}
