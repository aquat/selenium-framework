package org.aquat.seleniumframework.yamlmodel;

public class Selenium {
	private boolean grid;
	private String gridServer;
	private String implicitwaits;

	public boolean isGrid() {
		return grid;
	}

	public void setGrid(boolean grid) {
		this.grid = grid;
	}

	public String getGridServer() {
		return gridServer;
	}

	public void setGridServer(String gridServer) {
		this.gridServer = gridServer;
	}

	public String getImplicitwaits() {
		return implicitwaits;
	}

	public void setImplicitwaits(String implicitwaits) {
		this.implicitwaits = implicitwaits;
	}

}
