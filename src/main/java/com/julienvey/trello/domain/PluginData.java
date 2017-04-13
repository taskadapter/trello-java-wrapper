package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PluginData {

	private String id;
	private String idPlugin;
	private String scope;
	private String idModel;
	private String value;
	private String access;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdPlugin() {
		return idPlugin;
	}

	public void setIdPlugin(String idPlugin) {
		this.idPlugin = idPlugin;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getIdModel() {
		return idModel;
	}

	public void setIdModel(String idModel) {
		this.idModel = idModel;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

}