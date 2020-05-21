package cn.com.starest.nextoa.event.listener;

import cn.com.starest.nextoa.model.PaperAction;
import cn.com.starest.nextoa.model.PaperTransitionRequest;
import cn.com.starest.nextoa.service.PaperInnerService;
import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ReplyPaperEventListener implements EventListener<PaperTransitionRequest>, InitializingBean {

	@Autowired
	private PaperInnerService paperInnerService;

	@Override
	public void onEvent(PaperTransitionRequest request) {
		paperInnerService.handleReplyPaperAction(request.getFromAction(), request.getToAction());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm("paper").register(PaperAction.REPLY_ACTION, this);
	}

}
