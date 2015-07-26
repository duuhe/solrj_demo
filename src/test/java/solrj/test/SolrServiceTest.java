package solrj.test;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import solrj.service.SolrService;

public class SolrServiceTest {
	private SolrService solr = new SolrService();
	
	@Before
	public void init(){
		solr.init();
	}
	
	@After
	public void shutdown() {
		solr.shutdown();
	}
	
	@Test
	public void search(){
		
		String field = "*";
		String key = "*";
		String sortFiled = "id";
		boolean sortFlag = false;
		
		try {
			QueryResponse response = solr.search(field, key, 0, 10, sortFiled, sortFlag);
			if(response == null) {
				System.out.println("QueryResponse is null.");
				return ;
			}
			
			SolrDocumentList docs = response.getResults();
			for (SolrDocument doc : docs) {
				System.out.println("The Doc Id: " + doc.getFieldValue("id")
						.toString());
				System.out.println("The Doc Title: " + doc.getFieldValue("title")
						.toString());
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
