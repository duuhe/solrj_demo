package querier;

import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SimpleParams;
import org.junit.Test;
import solrj.client.SolrCloudClient;
import solrj.client.ZSolrClient;
import solrj.common.SolrField;
import solrj.params.DismaxParam;
import solrj.params.FacetParam;
import solrj.params.SearchParam;
import solrj.querier.BaseQuerier;
import solrj.querier.Querier;

import java.util.ArrayList;
import java.util.Collection;

public class BaseQuerierTest {
    private String zkHost = "10.204.76.79:2181,10.204.76.80:2181,10.204.76.99:2181";
    private String core = "soft";
    private ZSolrClient zClient = new SolrCloudClient(zkHost, core);

    @Test
    public void searchTest() {
        Querier querier = new BaseQuerier(zClient);

        try {

            SolrField field1 = new SolrField("soft_name", "微信");
            SolrField field2 = new SolrField("soft_name", "微博");
            Collection<SolrField> fields = new ArrayList<SolrField>();
            fields.add(field1);
            fields.add(field2);
            SearchParam ssp = new SearchParam(fields);

            // filterField
            Collection<SolrField> filterFields = new ArrayList<SolrField>();
            SolrField fileterField = new SolrField("softId", 49411);
            filterFields.add(fileterField);
            //ssp.setFilterFields(filterFields);

            // sortFiled
            Collection<SolrField> sortFields = new ArrayList<SolrField>();
            SolrField sortField = new SolrField("softId", ORDER.desc);
            sortFields.add(sortField);
            //ssp.setSortFields(sortFields);

            // Operators
            String op = SimpleParams.AND_OPERATOR;
            ssp.setOp(op);

            SolrDocumentList docs = querier.search(ssp).getResults();
            for (SolrDocument doc : docs) {
                System.out.println(doc.get("softId"));
                System.out.println(doc.get("soft_name"));
                System.out.println("--------------------------");
            }
            System.out.println("end");
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void facetTest() {
        Querier querier = new BaseQuerier(zClient);

        try {

            SolrField field = new SolrField("*", "*");
            FacetParam sfp = new FacetParam(field);
            sfp.setCount(0);
            String facetField = "soft_name_auto";
            sfp.setFacetFiled(facetField);
            sfp.setFacetPrefix("微信");

            QueryResponse reponse = querier.facet(sfp);
            System.out.println(reponse);

            System.out.println("end");
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void searchByDismaxTest() {
        Querier querier = new BaseQuerier(zClient);

        try {
            DismaxParam sdp = new DismaxParam("微信");

            SolrField filed1 = new SolrField("summary", "5.0");
            SolrField filed2 = new SolrField("description", "0.5");
            SolrField filed3 = new SolrField("soft_name", "15.0");
            SolrField filed4 = new SolrField("tags", "1.0");
            Collection<SolrField> queryFields = new ArrayList<SolrField>();
            queryFields.add(filed1);
            queryFields.add(filed2);
            queryFields.add(filed3);
            queryFields.add(filed4);
            sdp.setQueryFields(queryFields);

            String boostFunctions = "sum(div(sum(download_number,ad_download_number),1000000.0))";
            sdp.setBoostFunctions(boostFunctions);

            Collection<String> filedsReturn = new ArrayList<String>();
            filedsReturn.add("soft_name");
            sdp.setFiledsReturn(filedsReturn);

            QueryResponse reponse = querier.searchByDismax(sdp);
            System.out.println(reponse);

            System.out.println("end");
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
