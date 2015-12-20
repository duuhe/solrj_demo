package querier;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import params.SolrDismaxParam;
import params.SolrFacetParam;
import params.SolrSearchParam;

public interface Querier {
	QueryResponse search(SolrSearchParam ssp) throws SolrServerException;

	QueryResponse searchByDismax(SolrDismaxParam sdp) throws SolrServerException;

	SolrDocumentList searchByEdismax(String keyword);

	SolrDocumentList suggest(String keyword);

	QueryResponse facet(SolrFacetParam sfp) throws SolrServerException;
}
