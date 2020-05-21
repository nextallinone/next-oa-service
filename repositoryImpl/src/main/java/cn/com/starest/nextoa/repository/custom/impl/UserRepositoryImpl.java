package cn.com.starest.nextoa.repository.custom.impl;

import cn.com.starest.nextoa.model.Organization;
import cn.com.starest.nextoa.model.SysRole;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.UserQueryRequest;
import cn.com.starest.nextoa.model.dtr.UsernameQueryRequest;
import cn.com.starest.nextoa.repository.custom.UserRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractCustomRepositoryImpl implements UserRepositoryCustom {

	@Override
	public Page<User> queryPage(Organization organization, UsernameQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = new Query();
		query.addCriteria(Criteria.where("organizations").in(organization));
		if (!StringUtils.isEmpty(queryRequest.getUsername())) {
			query.addCriteria(Criteria.where("username").regex(queryRequest.getUsername()));
		}
		query.addCriteria(Criteria.where("deleted").ne(true));

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.ASC, "rank", "username"));
		query.with(pageable);
		long count = mongoTemplate.count(query, User.class);
		List<User> list = mongoTemplate.find(query, User.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<User> queryPage(SysRole role, UserQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = new Query();
		query.addCriteria(Criteria.where("roles").in(role));
		if (!StringUtils.isEmpty(queryRequest.getUsername())) {
			query.addCriteria(Criteria.where("username").regex(queryRequest.getUsername()));
		}
		query.addCriteria(Criteria.where("deleted").ne(true));

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "username"));
		query.with(pageable);
		long count = mongoTemplate.count(query, User.class);
		List<User> list = mongoTemplate.find(query, User.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<User> queryPage(UserQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);
		query.addCriteria(Criteria.where("deleted").ne(true));

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.ASC, "rank", "username"));
		query.with(pageable);
		long count = mongoTemplate.count(query, User.class);
		List<User> list = mongoTemplate.find(query, User.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<User> queryArchivedUsers(UserQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);
		query.addCriteria(Criteria.where("deleted").is(true));

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "deletedAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, User.class);
		List<User> list = mongoTemplate.find(query, User.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(UserQueryRequest request) {
		Query query = new Query();
		if (!StringUtils.isEmpty(request.getUsername())) {
			query.addCriteria(Criteria.where("username").regex(request.getUsername()));
		}
		if (!StringUtils.isEmpty(request.getContactPhone())) {
			query.addCriteria(Criteria.where("contactPhone").regex(request.getContactPhone()));
		}
		if (!StringUtils.isEmpty(request.getOfficePhone())) {
			query.addCriteria(Criteria.where("officePhone").regex(request.getOfficePhone()));
		}
		if (!StringUtils.isEmpty(request.getEmail())) {
			query.addCriteria(Criteria.where("email").regex(request.getEmail()));
		}
		if (!StringUtils.isEmpty(request.getPosition())) {
			query.addCriteria(Criteria.where("position").regex(request.getPosition()));
		}
		if (request.getUserType() != null) {
			query.addCriteria(Criteria.where("userType").is(request.getUserType()));
		}
		if (!StringUtils.isEmpty(request.getOrganizationId())) {
			query.addCriteria(Criteria.where("organization.id").is(request.getOrganizationId()));
		}
		if (request.getEnabled() != null) {
			query.addCriteria(Criteria.where("enabled").is(request.getEnabled().booleanValue()));
		}

		return query;
	}

}
