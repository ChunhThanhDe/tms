package com.vnptt.tms.api;

import com.vnptt.tms.api.output.UserOutput;
import com.vnptt.tms.dto.UserDTO;
import com.vnptt.tms.exception.ResourceNotFoundException;
import com.vnptt.tms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("TMS/api")
public class UserApi {

    @Autowired
    private IUserService userService;

    /**
     * Show User for web
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/user")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public UserOutput showUser(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "limit", required = false) Integer limit,
                               @RequestParam(value = "active", required = false) Integer active) {
        UserOutput result = new UserOutput();

        if (page != null && limit != null) {
            if (active != null){
                result.setPage(page);
                Pageable pageable = PageRequest.of(page - 1, limit);
                result.setListResult((userService.findAllWithActive(pageable, active)));
                result.setTotalPage((int) Math.ceil((double) userService.totalItem() / limit));
            } else {
                result.setPage(page);
                Pageable pageable = PageRequest.of(page - 1, limit);
                result.setListResult((userService.findAll(pageable)));
                result.setTotalPage((int) Math.ceil((double) userService.totalItem() / limit));
            }
        } else {
            if (active != null){
                result.setListResult(userService.findAllWithActive(active));
            } else {
                result.setListResult(userService.findAll());
            }
        }

        if (result.getListResult().size() >= 1) {
            result.setMessage("Request Success");
            result.setTotalElement(result.getListResult().size());
        } else {
            result.setMessage("no matching element found");
        }
        return result;
    }

    /**
     * find user by id
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/user/{id}")
    public UserDTO showUser(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }

    /**
     * Show list User with rule for web
     *
     * @param ruleId
     * @return
     */
    @GetMapping(value = "/rule/{ruleId}/user")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public UserOutput showUserWithRule(@PathVariable(value = "ruleId") Long ruleId) {
        UserOutput result = new UserOutput();
        result.setListResult(userService.findAllWithRule(ruleId));

        if (result.getListResult().size() >= 1) {
            result.setMessage("Request Success");
            result.setTotalElement(result.getListResult().size());
        } else {
            throw new ResourceNotFoundException("no matching element found");
        }
        return result;
    }

    /**
     * create new user (only use to test before token
     *
     * @param model
     * @return
     */
//    @PostMapping(value = "/user")
//    public UserDTO createUser(@RequestBody UserDTO model) {
//        return userService.save(model);
//    }

    /**
     * update password
     *
     * @param id
     * @return
     */
    @PutMapping(value = "/user/updatePassword/{id}")
    public UserDTO updatePassword (@PathVariable("id") Long id,
                                   @RequestParam(name = "passwordold") String passwordold,
                                   @RequestParam(name = "passwordnew") String passwordnew) {
        return userService.updatePassword(id, passwordold, passwordnew);
    }

    /**
     * update password
     *
     * @param id
     * @return
     */
    @PutMapping(value = "/user/forcedUpdatePassword/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public UserDTO forcedUpdatePassword(@PathVariable("id") Long id,
                                         @RequestParam(name = "passwordnew") String passwordnew) {
        return userService.forcedUpdatePassword(id, passwordnew);
    }

    /**
     * update info user for user
     *
     * @param model
     * @param id
     * @return
     */
    @PutMapping(value = "/user/{id}")
    public UserDTO updateUser(@RequestBody UserDTO model, @PathVariable("id") Long id) {
        model.setId(id);
        return userService.update(model);
    }

    /**
     * change active to off
     *
     * @param id
     */
    @DeleteMapping(value = "/user/remove/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void removeUser(@PathVariable("id") Long id) {
        userService.remove(id);
    }

    /**
     * only use to test
     *
     * @param ids
     */
    @DeleteMapping(value = "/user")
    @PreAuthorize("hasRole('MODERATOR')")
    public void removeUser(@RequestBody Long[] ids) {
        userService.delete(ids);
    }
}
