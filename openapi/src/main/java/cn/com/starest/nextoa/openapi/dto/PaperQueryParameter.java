package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.PaperStatus;
import cn.com.starest.nextoa.model.dtr.PaperQueryRequest;
import io.swagger.annotations.ApiModel;

/**
 */
@ApiModel
public class PaperQueryParameter extends DateRangedQueryParameter implements PaperQueryRequest {

	private String category;

	private String title;

	private PaperStatus paperStatus;

	private Boolean urgent;

	private String creatorId;

	private String creatorUsername;

	@Override
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public PaperStatus getPaperStatus() {
		return paperStatus;
	}

	public void setPaperStatus(PaperStatus paperStatus) {
		this.paperStatus = paperStatus;
	}

	@Override
	public Boolean getUrgent() {
		return urgent;
	}

	public void setUrgent(Boolean urgent) {
		this.urgent = urgent;
	}

	@Override
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	@Override
	public String getCreatorUsername() {
		return creatorUsername;
	}

	public void setCreatorUsername(String creatorUsername) {
		this.creatorUsername = creatorUsername;
	}
}
