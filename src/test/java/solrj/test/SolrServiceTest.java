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
		String sortFiled = "soft_id";
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
				System.out.println("soft_id: "
						+ doc.getFieldValue("soft_id").toString());
				System.out.println("soft_name: "
						+ doc.getFieldValue("soft_name").toString());
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void searchSuggest() {

		System.out.println("################## search suggest ");

		String keyword = "微信";
		try {
			SpellCheckResponse response = solr.searchSuggest(keyword);
			if (response != null) {
				/*
				 * List<Suggestion> suggestionList = response.getSuggestions();
				 * for (Suggestion suggestion : suggestionList) {
				 * System.out.println("Suggestions NumFound: " +
				 * suggestion.getNumFound()); System.out.println("Token: " +
				 * suggestion.getToken()); System.out.print("Suggested: ");
				 * List<String> suggestedWordList =
				 * suggestion.getAlternatives(); for (String word :
				 * suggestedWordList) { System.out.println(word + ", "); }
				 * System.out.println(); } System.out.println(); Map<String,
				 * Suggestion> suggestedMap = response.getSuggestionMap(); for
				 * (Map.Entry<String, Suggestion> entry :
				 * suggestedMap.entrySet()) {
				 * System.out.println("suggestionName: " + entry.getKey());
				 * Suggestion suggestion = entry.getValue();
				 * System.out.println("NumFound: " + suggestion.getNumFound());
				 * System.out.println("Token: " + suggestion.getToken());
				 * System.out.print("suggested: ");
				 * 
				 * List<String> suggestedList = suggestion.getAlternatives();
				 * for (String suggestedWord : suggestedList) {
				 * System.out.print(suggestedWord + ", "); }
				 * System.out.println("\n\n"); }
				 */

				Suggestion suggestion = response.getSuggestion(keyword);
				System.out.println("NumFound: " + suggestion.getNumFound());
				System.out.println("Token: " + suggestion.getToken());
				System.out.print("suggested: ");
				List<String> suggestedList = suggestion.getAlternatives();
				for (String suggestedWord : suggestedList) {
					System.out.print(suggestedWord + ", ");
				}
				System.out.println("\n\n");
			}

		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
