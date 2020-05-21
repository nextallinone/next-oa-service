package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.*;
import in.clouthink.daas.fss.core.FileObject;
import in.clouthink.daas.fss.rest.UploadFileRequest;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public interface AttachmentRestSupport {

	Page<AttachmentSummary> listAttachment(AttachmentQueryParameter queryRequest);

	AttachmentDetail getAttachmentDetail(String id);

	String createAttachment(SaveAttachmentParameter request, User user);

	void updateAttachment(String id, SaveAttachmentParameter request, User user);

	void deleteAttachment(String id, User user);

	void publishAttachment(String id, User user);

	void unpublishAttachment(String id, User user);

	Page<DownloadSummary> listDownloadHistory(String id, PageQueryParameter queryParameter);

	void downloadAttachment(String id, User user, HttpServletResponse response) throws IOException;

	FileObject upload4CkEditor(UploadFileRequest uploadFileRequest,
							   HttpServletRequest request,
							   HttpServletResponse response) throws IOException;

	FileObject uploadAvatar(UploadFileRequest uploadFileRequest,
							HttpServletRequest request,
							HttpServletResponse response) throws IOException;
}
