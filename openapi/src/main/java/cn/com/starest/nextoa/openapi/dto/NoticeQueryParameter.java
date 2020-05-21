package cn.com.starest.nextoa.openapi.dto;

import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 *
 */
@ApiModel
public class NoticeQueryParameter extends DateRangedQueryParameter implements
                                                                  cn.com.starest.nextoa.model.dtr.NoticeQueryRequest {
    
    private String title;
    
    private String category;
    
    private Boolean published;

    private Date createdAtBegin;

    private Date createdAtEnd;

    @Override
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Boolean getPublished() {
        return published;
    }
    
    public void setPublished(Boolean published) {
        this.published = published;
    }

    @Override
    public Date getCreatedAtBegin() {
        return createdAtBegin;
    }

    public void setCreatedAtBegin(Date createdAtBegin) {
        this.createdAtBegin = createdAtBegin;
    }

    @Override
    public Date getCreatedAtEnd() {
        return createdAtEnd;
    }

    public void setCreatedAtEnd(Date createdAtEnd) {
        this.createdAtEnd = createdAtEnd;
    }
}
