package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.*;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface SysUserRestSupport {

	Page<UserSummary> listSysUsers(UserQueryParameter queryRequest);

	UserDetail getSysUserDetail(String id);

	User createSysUser(SaveSysUserParameter request);

	void updateSysUser(String id, SaveSysUserParameter request);

	void deleteSysUser(String id, User byWho);

	void changePassword(String id, ChangePasswordRequest request);

	void enableSysUser(String id);

	void disableSysUser(String id);

	void lockSysUser(String id);

	void unlockSysUser(String id);

}
