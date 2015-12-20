package params;

import java.util.ArrayList;
import java.util.Collection;

import common.SolrField;

public class SolrSearchParam {
	private Collection<SolrField> fields;
	private Collection<SolrField> filterFields;
	private Collection<SolrField> sortFields;
	private int start;
	private int count;
	private String op;
	
	public SolrSearchParam(SolrField field) {
		this.fields = new ArrayList<SolrField>();
		this.fields.add(field);
		this.start = 0;
		this.count = 20;
	}

	public SolrSearchParam(SolrField field, int start, int count) {
		this.fields = new ArrayList<SolrField>();
		this.fields.add(field);
		this.start = start;
		this.count = count;
	}

	public SolrSearchParam(Collection<SolrField> fields) {
		this.fields = fields;
		this.start = 0;
		this.count = 20;
	}

	public SolrSearchParam(Collection<SolrField> fields, int start, int count) {
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
