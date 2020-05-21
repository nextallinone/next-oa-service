package cn.com.starest.nextoa.event.listener;

import cn.com.starest.nextoa.model.dtr.ReadNewsEvent;
import cn.com.starest.nextoa.service.NewsService;
import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *@author dz
 */
@Component
public class ReadNewsEventListener implements EventListener<ReadNewsEvent>, InitializingBean {

	@Autowired
	private NewsService newsService;

	@Override
	public void onEvent(ReadNewsEvent event) {
		newsService.markNewsAsRead(event.getNews(), event.getUser());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm().register(ReadNewsEvent.EVENT_NAME, this);
	}
}
