package solrj.querier;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import solrj.params.DismaxParam;
import solrj.params.EDismaxParam;
import solrj.params.FacetParam;
import solrj.params.SearchParam;

public interface Querier {
	QueryResponse search(SearchParam ssp) throws SolrServerException;

	QueryResponse searchByDismax(DismaxParam sdp) throws SolrServerException;
	
	QueryResponse searchByEDismax(EDismaxParam edp) throws SolrServerException;

	SolrDocumentList suggest(String keyword);

	QueryResponse facet(FacetParam sfp) throws SolrServerException;

	
}
