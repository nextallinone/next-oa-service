package cn.com.starest.nextoa.dashboard.support.audit.repository;


import cn.com.starest.nextoa.dashboard.support.audit.model.AggregationType;
import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEventAggregation;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuditEventAggregationRepository extends AbstractRepository<AuditEventAggregation> {

	AuditEventAggregation findByAggregationTypeAndAggregationKey(AggregationType type, String key);

	List<AuditEventAggregation> findByAggregationTypeAndAggregationKeyLike(AggregationType type, String key);

	Page<AuditEventAggregation> findPageByAggregationTypeOrderByAggregationKeyDesc(AggregationType type,
																				   Pageable pageable);

}
