package springTest.web.viewer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;

@Component(value="rssfeed")
public class RSSViewer extends AbstractRssFeedView  {

	@SuppressWarnings("unchecked")
	@Override
	protected List<Item> buildFeedItems(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return (List<Item>) map.get("items");
	}
	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
			HttpServletRequest request) {
		feed.setTitle("ÍøÕ¾±êÌâ");
		feed.setDescription("ÍøÕ¾ÃèÊö");
		feed.setLink("ÍøÖ·");
		super.buildFeedMetadata(model, feed, request);
	}
}
