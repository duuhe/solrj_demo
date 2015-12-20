package indexer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import client.SolrCloudClient;
import client.ZSolrClient;
import domain.Demo;

public class BaseIndexerTest {
	private String zkHost = "10.204.76.79:2181,10.204.76.80:2181,10.204.76.99:2181";
	private String core = "demo";
	
	@Test
	public void addDocTest(){
		ZSolrClient zClient = new SolrCloudClient(zkHost, core);
		Indexer indexer = new BaseIndexer(zClient);
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", 1);
		doc.addField("title", "张三");
		doc.addField("author", "张三");
		doc.addField("text", "张三是谁");
		
		try {
			System.out.println("status:" + indexer.addDoc(doc));
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void addDocsTest(){
		ZSolrClient zClient = new SolrCloudClient(zkHost, core);
		Indexer indexer = new BaseIndexer(zClient);
		
		SolrInputDocument doc1 = new SolrInputDocument();
		doc1.addField("id", 2);
		doc1.addField("title", "李四");
		doc1.addField("author", "李四");
		doc1.addField("text", "李四是谁");
		SolrInputDocument doc2 = new SolrInputDocument();
		doc2.addField("id", 3);
		doc2.addField("title", "王五");
		doc2.addField("author", "王五");
		doc2.addField("text", "王五是谁");
		SolrInputDocument doc3 = new SolrInputDocument();
		doc3.addField("id", 4);
		doc3.addField("title", "赵六");
		doc3.addField("author", "赵六");
		doc3.addField("text", "赵六是谁");
		
		List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		docs.add(doc1);
		docs.add(doc2);
		docs.add(doc3);
		
		try {
			System.out.println("status:" + indexer.addDocs(docs));
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void delDocByIdTest(){
		ZSolrClient zClient = new SolrCloudClient(zkHost, core);
		Indexer indexer = new BaseIndexer(zClient);
		
		try {
			System.out.println("status:" + indexer.delDocById("1"));
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void delDocByQueryTest(){
		ZSolrClient zClient = new SolrCloudClient(zkHost, core);
		Indexer indexer = new BaseIndexer(zClient);
		
		try {
			System.out.println("status:" + indexer.delDocByQuery("id:3 OR id:4"));
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateDocTest(){
		ZSolrClient zClient = new SolrCloudClient(zkHost, core);
		Indexer indexer = new BaseIndexer(zClient);
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", 4);
		doc.addField("title", "钱七");
		doc.addField("author", "钱七");
		doc.addField("text", "钱七是谁");
		
		try {
			System.out.println("status:" + indexer.updateDoc(doc));
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void addBeanTest(){
		ZSolrClient zClient = new SolrCloudClient(zkHost, core);
		Indexer indexer = new BaseIndexer(zClient);
		
		Demo demo = new Demo(1, "test1", "test1", "This is a test 1.");
		
		try {
			System.out.println("status:" + indexer.addBean(demo));
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void addBeansTest(){
		ZSolrClient zClient = new SolrCloudClient(zkHost, core);
		Indexer indexer = new BaseIndexer(zClient);
		
		Demo demo1 = new Demo(1, "test1", "test1", "This is a test 1.");
		Demo demo2 = new Demo(2, "test2", "test2", "This is a test 2.");
		Demo demo3 = new Demo(3, "test3", "test3", "This is a test 3.");
		
		Collection<Object> demos = new ArrayList<Object>();
		demos.add(demo1);
		demos.add(demo2);
		demos.add(demo3);
		
		try {
			System.out.println("status:" + indexer.addBeans(demos));
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
