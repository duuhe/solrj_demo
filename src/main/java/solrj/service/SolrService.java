package solrj.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

public class SolrService extends BaseSolrService {
	@Override
	public String getCore() {
		return "demo";
	}

	public QueryResponse search(String[] fields, String[] keys, int start,
			int count, String sortfields[], Boolean sortflags[]) throws SolrServerException {
		// 检测输入是否合法
		if (null == fields || null == keys || fields.length != keys.length) {
			logger.warn("Filed or key or is null. Or field.length != key.length.");
			return null;
		}

		this.init();
		
		SolrQuery query = null;
		// 初始化查询对象
		query = new SolrQuery(fields[0] + ":" + keys[0]);
		for (int i = 0; i < fields.length; i++) {
			query.addFilterQuery(fields[i] + ":" + keys[i]);
		}

		// 设置起始位置与返回结果数
		query.setStart(start);
		query.setRows(count);

		if (null != sortfields && null != sortflags
				&& sortfields.length == sortflags.length) {
			// 设置排序
			for (int i = 0; i < sortfields.length; i++) {
				if (sortflags[i]) {
					query.addSort(sortfields[i], SolrQuery.ORDER.asc);
				} else {
					query.addSort(sortfields[i], SolrQuery.ORDER.desc);
				}
			}
		}
		
		logger.info("Querying ......");
		QueryResponse response = client.query(query);
		
		this.shutdown();
		
		return response;
	}

	public QueryResponse search(String field, String key, int start,
			int count, String sortfield, Boolean sortflag) throws SolrServerException {
		
		String[] fields = new String[]{field};
		String[] keys = new String[]{key};
		String[] sortfields = new String[]{sortfield};
		Boolean[] sortflags = new Boolean[]{sortflag};
		
		return this.search(fields, keys, start, count, sortfields, sortflags);
		
	}
	
	public QueryResponse search(String field, String key, int start,
			int count) throws SolrServerException {
		
		String[] fields = new String[]{field};
		String[] keys = new String[]{key};
		
		return this.search(fields, keys, start, count, null, null);
		
	}
}
