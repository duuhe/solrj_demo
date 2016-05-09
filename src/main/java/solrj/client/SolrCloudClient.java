package solrj.client;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;

public class SolrCloudClient extends SolrSingleClient{
	
	private CloudSolrClient client;
	
	/**
	 * Default constructor, initializing the client.
	 */
	public SolrCloudClient(String zkHost, String core) {
		client = new CloudSolrClient(zkHost);
		client.setDefaultCollection(core);
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
