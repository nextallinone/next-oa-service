package cn.com.starest.nextoa.rbac.impl.service;

import cn.com.starest.nextoa.model.AppRole;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.RoleQueryRequest;
import cn.com.starest.nextoa.model.dtr.SaveRoleRequest;
import cn.com.starest.nextoa.model.dtr.UserQueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {
    
    Page<AppRole> listAppRoles(RoleQueryRequest request);

    List<AppRole> listAppRoles();

    Page<User> listBindUsers(String id, UserQueryRequest request);
    
    AppRole findById(String id);

    AppRole findByCode(String code);

    AppRole createAppRole(SaveRoleRequest request);
    
    void updateAppRole(String id, SaveRoleRequest request);
    
    void deleteAppRole(String id);
    
    void bindUsers4AppRole(String id, List<String> userIds);
    
    void unBindUsers4AppRole(String id, List<String> userIds);
    
    void bindUsers4SysRole(String id, List<String> userIds);
    
    void unBindUsers4SysRole(String id, List<String> userIds);
    
}
