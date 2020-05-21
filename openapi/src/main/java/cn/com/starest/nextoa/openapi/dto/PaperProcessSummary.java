package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.PaperAction;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 *
 */
@ApiModel
public class PaperProcessSummary extends PaperActionSummary {

	public static PaperProcessSummary from(PaperAction action) {
		if (action == null) {
			return null;
		}
		PaperProcessSummary result = new PaperProcessSummary();
		convert(action, result);
		result.setContent(action.getContent());
		return result;
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
