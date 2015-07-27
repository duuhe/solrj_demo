package solrj.test;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import solrj.service.SolrService;

public class SolrServiceTest {
	private SolrService solr = new SolrService();

	@Before
	public void init() {
		solr.init();
	}

	@After
	public void shutdown() {
		solr.shutdown();
	}

	@Test
	public void search() {

		String field = "*";
		String key = "*";
		String sortFiled = "id";
		boolean sortFlag = true;

		try {
			QueryResponse response = solr.search(field, key, 0, 10, sortFiled,
					sortFlag);
			if (response == null) {
				System.out.println("QueryResponse is null.");
				return;
			}

			SolrDocumentList docs = response.getResults();
			for (SolrDocument doc : docs) {
				System.out.println("id: " + doc.getFieldValue("id").toString());
				System.out.println("title: "
						+ doc.getFieldValue("title").toString());
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void searchSuggest() {

		System.out.println("################## search suggest ");

		String keyword = "张";
		try {
			SpellCheckResponse response = solr.searchSuggest(keyword);
			if (response != null) {

				Suggestion suggestion = response.getSuggestion(keyword);
				System.out.println("NumFound: " + suggestion.getNumFound());
				System.out.println("Token: " + suggestion.getToken());
				System.out.print("suggested: ");
				List<String> suggestedList = suggestion.getAlternatives();
				for (String suggestedWord : suggestedList) {
					System.out.print(suggestedWord + ", ");
				}
			}

		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
