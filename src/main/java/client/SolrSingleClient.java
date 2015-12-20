package client;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrSingleClient implements ZSolrClient{

	private SolrClient client;
 
	public SolrSingleClient(){
		
	}
	
	public SolrSingleClient(String url, String core) {
		client = new HttpSolrClient(url + core);
	}

	@Override
	public SolrClient getClient() {
		return client;
	}
	
	@Override
	public void shutdown(){
		this.client.shutdown();
	}
}
