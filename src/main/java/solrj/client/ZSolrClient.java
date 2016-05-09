package solrj.client;

import org.apache.solr.client.solrj.SolrClient;

public interface ZSolrClient {

	/**
     * Get a reference to an Solr {@link SolrClient}.
     * 
     * @return a {@link SolrClient}.
     */
	SolrClient getClient();

    /**
     * Shutdown the Solr {@link SolrClient}. The client is not available
     * for querying and indexing.
     */
    void shutdown();	
}
