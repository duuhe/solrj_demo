package solrj.querier;

import java.util.Collection;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.DisMaxParams;
import org.apache.solr.common.params.FacetParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import solrj.client.ZSolrClient;
import solrj.common.SolrContent;
import solrj.common.SolrField;
import solrj.params.DismaxParam;
import solrj.params.EDismaxParam;
import solrj.params.FacetParam;
import solrj.params.SearchParam;

public class BaseQuerier implements Querier {

	private static final Logger log = LoggerFactory.getLogger(BaseQuerier.class);
	private SolrClient client;

	public BaseQuerier(ZSolrClient zClient) {
		this.client = zClient.getClient();
	}

	private SolrQuery SP2SQ(SearchParam ssp) {

		Collection<SolrField> fields = ssp.getFields();
		// Fields is null, return
		if (fields == null) {
			log.warn("filed is null.");
			return null;
		}

		// Init SolrQuery
		StringBuffer strBuf = new StringBuffer();
		for (SolrField field : fields) {
			strBuf.append(field.getName() + ":" + field.getValue());
			strBuf.append(" ");
		}
		SolrQuery query = new SolrQuery(strBuf.toString());

		query.setStart(ssp.getStart());
		query.setRows(ssp.getCount());

		// FilterQuery
		Collection<SolrField> filterFields = ssp.getFilterFields();
		if (filterFields != null) {
			for (SolrField filterField : filterFields) {
				query.addFilterQuery(filterField.getName() + ":" + filterField.getValue());
			}
		}

		// Sort
		Collection<SolrField> sortFields = ssp.getSortFields();
		if (sortFields != null) {
			for (SolrField sortField : sortFields) {
				query.addSort(sortField.getName(), (ORDER) sortField.getValue());
			}
		}

		// Operators
		String op = ssp.getOp();
		if (op != null) {
			query.set(SolrContent.QO, op);
		}

		return query;
	}

	// FIXME:
	private SolrQuery FP2SQ(FacetParam sfp) {
		SolrQuery query = SP2SQ(sfp);

		query.set(CommonParams.ROWS, sfp.getCount());
		query.set(FacetParams.FACET, sfp.getEnable());
		query.set(FacetParams.FACET_FIELD, sfp.getFacetFiled());
		query.set(FacetParams.FACET_MINCOUNT, sfp.getFacetMincount());
		query.set(FacetParams.FACET_PREFIX, sfp.getFacetPrefix());

		return query;
	}

	private SolrQuery DP2SQ(DismaxParam sdp) {
		SolrQuery query = new SolrQuery();

		query.setParam(SolrContent.DT, sdp.getDfType());
		query.set(CommonParams.Q, sdp.getKeyword());

		// Query Fields
		Collection<SolrField> queryFields = sdp.getQueryFields();
		if (queryFields != null) {
			StringBuffer strBuf = new StringBuffer();
			for (SolrField queryField : queryFields) {
				// filed1^boost1 filed2^boost2 ...
				strBuf.append(queryField.getName() + SolrContent.UPARROW + queryField.getValue());
				strBuf.append(SolrContent.blank);
			}
			query.set(DisMaxParams.QF, strBuf.toString());
		}

		// Phrase Fields
		
		
		// Boost Functions
		String boostFunctions = sdp.getBoostFunctions();
		if(boostFunctions != null){
			query.set(DisMaxParams.BF, boostFunctions);
		}
		
		// Fields Return
		Collection<String> fieldsReturn = sdp.getFiledsReturn();
		if(fieldsReturn != null){
			StringBuffer strBuf = new StringBuffer();
			for (String field : fieldsReturn) {
				// filed1^boost1 filed2^boost2 ...
				strBuf.append(field);
				strBuf.append(SolrContent.blank);
			}
			query.set(CommonParams.FL, strBuf.toString());
		}
		
		return query;
	}

	// FIXME:
	private SolrQuery EDP2SQ(EDismaxParam edp){
		SolrQuery query = DP2SQ(edp);
		
		return query;
	}
	
	@Override
	public QueryResponse search(SearchParam ssp) throws SolrServerException {
		SolrQuery query = SP2SQ(ssp);
		QueryResponse response = client.query(query, SolrRequest.METHOD.POST);

		return response;
	}

	@Override
	public QueryResponse searchByDismax(DismaxParam sdp) throws SolrServerException {
		SolrQuery query = DP2SQ(sdp);
		QueryResponse response = client.query(query, SolrRequest.METHOD.POST);

		return response;
	}
	
	@Override
	public QueryResponse searchByEDismax(EDismaxParam edp) throws SolrServerException{
		SolrQuery query = EDP2SQ(edp);
		QueryResponse response = client.query(query, SolrRequest.METHOD.POST);
		
		return response;
	}

	@Override
	public SolrDocumentList suggest(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResponse facet(FacetParam sfp) throws SolrServerException {
		SolrQuery query = FP2SQ(sfp);
		QueryResponse response = client.query(query, SolrRequest.METHOD.POST);

		return response;
	}
}
