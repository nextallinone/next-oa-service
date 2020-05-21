package cn.com.starest.nextoa.openapi.support.impl;

import cn.com.starest.nextoa.model.ContactGroup;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.UserType;
import cn.com.starest.nextoa.openapi.dto.*;
import cn.com.starest.nextoa.openapi.support.ContactRestSupport;
import cn.com.starest.nextoa.service.AccountService;
import cn.com.starest.nextoa.service.ContactService;
import cn.com.starest.nextoa.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class ContactRestSupportImpl implements ContactRestSupport {

	@Autowired
	private ContactService contactService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private OrganizationService organizationService;

	@Override
	public List<UserSummary> listRecentUsers(User user) {
		return contactService.listRecentUsers(user).stream().map(UserSummary::from).collect(Collectors.toList());
	}

	@Override
	public List<OrganizationSummary> getOrganizationOfUser(User user) {
		//reload the user
		User target = accountService.findById(user.getId());
		if (target.getOrganizations() != null) {
			return target.getOrganizations().stream().map(OrganizationSummary::from).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@Override
	public Page<UserSummary> listAppUsers(UserQueryParameter queryRequest) {
		queryRequest.setUserType(UserType.APPUSER);
		Page<User> appUserPage = organizationService.listAppUsers(queryRequest);
		return new PageImpl<>(appUserPage.getContent().stream().map(UserSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  appUserPage.getTotalElements());
	}

	@Override
	public UserDetail getAppUserDetail(String id) {
		User user = organizationService.findAppUserById(id);
		return UserDetail.from(user);
	}

	@Override
	public Page<ContactGroupSummary> listContactGroups(ContactGroupQueryParameter queryRequest, User user) {
		Page<ContactGroup> contactGroupPage = contactService.listContactGroups(queryRequest, user);
		return new PageImpl<>(contactGroupPage.getContent()
											  .stream()
											  .map(ContactGroupSummary::from)
											  .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  contactGroupPage.getTotalElements());
	}

	@Override
	public ContactGroupSummary createContactGroup(SaveContactGroupParameter createRequest, User user) {
		ContactGroup contactGroup = contactService.createContactGroup(createRequest.getName(), user);
		return ContactGroupSummary.from(contactGroup);
	}

	@Override
	public ContactGroupDetail getContactGroupDetail(String id, User user) {
		ContactGroup contactGroup = contactService.findContactGroupById(id, user);
		List<User> users = contactService.listUsersOfContactGroups(id, new PageQueryParameter(0, 20)).getContent();
		return ContactGroupDetail.from(contactGroup,
									   users.stream().map(UserSummary::from).collect(Collectors.toList()));
	}

	@Override
	public void updateContactGroup(String id, SaveContactGroupParameter updateRequest, User user) {
		contactService.updateContactGroup(id, updateRequest.getName(), user);
	}

	@Override
	public void deleteContactGroup(String id, User user) {
		contactService.deleteContactGroup(id, user);
	}

	@Override
	public void addUsersToContactGroup(String id, String[] userIds, User user) {
		contactService.addUsersToGroup(id, userIds, user);
	}

	@Override
	public void removeUsersFromContactGroup(String id, String[] userIds, User user) {
		contactService.removeUsersFromGroup(id, userIds, user);
	}

}
