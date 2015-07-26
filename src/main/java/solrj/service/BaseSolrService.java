package solrj.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
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
	
	/**
	 * 
	 * @param fields
	 *            查询的字段名称数组
	 * @param keys
	 *            查询的字段名称对应的值
	 * @param start
	 *            查询的起始位置
	 * @param count
	 *            一次查询出来的数量
	 * @param sortfields
	 *            需要排序的字段数组
	 * @param sortflags
	 *            需要排序的字段的排序方式如果为true 升序 如果为false 降序
	 * @return 查询结果
	 * @throws SolrServerException
	 */
	public QueryResponse search(String[] fields, String[] keys, int start,
			int count, String sortfields[], Boolean sortflags[])
			throws SolrServerException {
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

		// 设置起始位置
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
}
