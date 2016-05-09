package solrj.params;

import java.util.ArrayList;
import java.util.Collection;

import solrj.common.SolrField;

/**
 * The Standard(Lucene) Query Parser Param
 * 
 * @author LINGQ
 *
 */
public class SearchParam {
	// query field
	private Collection<SolrField> fields;
	// filter field
	private Collection<SolrField> filterFields;
	// sort field
	private Collection<SolrField> sortFields;
	// default 0
	private int start;
	// default 20
	private int count;
	// q.op
	private String op;
	
	public SearchParam(SolrField field) {
		this.fields = new ArrayList<SolrField>();
		this.fields.add(field);
		this.start = 0;
		this.count = 20;
	}

	public SearchParam(SolrField field, int start, int count) {
		this.fields = new ArrayList<SolrField>();
		this.fields.add(field);
		this.start = start;
		this.count = count;
	}

	public SearchParam(Collection<SolrField> fields) {
		this.fields = fields;
		this.start = 0;
		this.count = 20;
	}

	public SearchParam(Collection<SolrField> fields, int start, int count) {
		this.fields = fields;
		this.start = start;
		this.count = count;
	}

	public Collection<SolrField> getFields() {
		return fields;
	}

	public void setFields(Collection<SolrField> fields) {
		this.fields = fields;
	}

	public Collection<SolrField> getFilterFields() {
		return filterFields;
	}

	public void setFilterFields(Collection<SolrField> filterFields) {
		this.filterFields = filterFields;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Collection<SolrField> getSortFields() {
		return sortFields;
	}

	public void setSortFields(Collection<SolrField> sortFields) {
		this.sortFields = sortFields;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
}
