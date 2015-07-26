package solrj.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description Solrj 使用demo
 * @author Zhifeiyu
 * @version 1.0
 *
 */
public class BaseSolrService {
	private static final String url = "http://192.168.1.111:8983/solr";
	private static final String core = "demo";
	protected SolrClient client;
	protected Logger logger = null;
	
	public String getCore(){
    	return core;
    }
	
	public String getURL(){
    	return url;
    }
	
	public void init() {
		client = new HttpSolrClient(getURL() + "/" + getCore());
		logger = LoggerFactory.getLogger(this.getClass());
		
		logger.info("Running ......");
	}
	
	public void shutdown() {
		client.shutdown();
		logger.info("Shutdown ......");
	}
}
