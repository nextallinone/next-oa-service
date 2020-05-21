package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.dtr.SaveOrganizationRequest;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class SaveOrganizationParameter implements SaveOrganizationRequest {

	private String code;

	private String name;

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
