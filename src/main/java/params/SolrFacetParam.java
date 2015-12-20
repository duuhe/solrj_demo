package params;

import java.util.Collection;

import common.SolrField;

public class SolrFacetParam extends SolrSearchParam {

	private String enable = "true";
	private Collection<SolrField> facetQuerys;
	private String facetFiled;
	private String facetPrefix;
	private String facetContain;
	private String facetContainIngnoreCase;
	private String facetSort;
	private String facetLimit;
	private String facetOffset;
	private int facetMincount;
	private String facetMissing;
	private String facetMethod;

	public SolrFacetParam(Collection<SolrField> fields) {
		super(fields);
		facetMincount = 1;
	}

	public SolrFacetParam(SolrField field) {
		super(field);
		facetMincount = 1;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public Collection<SolrField> getFacetQuerys() {
		return facetQuerys;
	}

	public void setFacetQuerys(Collection<SolrField> facetQuerys) {
		this.facetQuerys = facetQuerys;
	}

	public String getFacetFiled() {
		return facetFiled;
	}

	public void setFacetFiled(String facetFiled) {
		this.facetFiled = facetFiled;
	}

	public String getFacetPrefix() {
		return facetPrefix;
	}

	public void setFacetPrefix(String facetPrefix) {
		this.facetPrefix = facetPrefix;
	}

	public String getFacetContain() {
		return facetContain;
	}

	public void setFacetContain(String facetContain) {
		this.facetContain = facetContain;
	}

	public String getFacetContainIngnoreCase() {
		return facetContainIngnoreCase;
	}

	public void setFacetContainIngnoreCase(String facetContainIngnoreCase) {
		this.facetContainIngnoreCase = facetContainIngnoreCase;
	}

	public String getFacetSort() {
		return facetSort;
	}

	public void setFacetSort(String facetSort) {
		this.facetSort = facetSort;
	}

	public String getFacetLimit() {
		return facetLimit;
	}

	public void setFacetLimit(String facetLimit) {
		this.facetLimit = facetLimit;
	}

	public String getFacetOffset() {
		return facetOffset;
	}

	public void setFacetOffset(String facetOffset) {
		this.facetOffset = facetOffset;
	}

	public int getFacetMincount() {
		return facetMincount;
	}

	public void setFacetMincount(int facetMincount) {
		this.facetMincount = facetMincount;
	}

	public String getFacetMissing() {
		return facetMissing;
	}

	public void setFacetMissing(String facetMissing) {
		this.facetMissing = facetMissing;
	}

	public String getFacetMethod() {
		return facetMethod;
	}

	public void setFacetMethod(String facetMethod) {
		this.facetMethod = facetMethod;
	}

}
