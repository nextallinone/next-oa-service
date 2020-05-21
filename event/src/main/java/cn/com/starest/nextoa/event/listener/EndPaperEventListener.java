package cn.com.starest.nextoa.event.listener;

import cn.com.starest.nextoa.model.PaperAction;
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
public class EndPaperEventListener implements EventListener<PaperAction>, InitializingBean {

	@Autowired
	private PaperInnerService paperInnerService;

	@Override
	public void onEvent(PaperAction endPaperAction) {
		paperInnerService.handleEndPaperAction(endPaperAction);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm("paper").register(PaperAction.END_ACTION, this);
	}

}
