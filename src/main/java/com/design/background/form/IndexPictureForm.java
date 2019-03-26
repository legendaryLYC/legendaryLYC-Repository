package com.design.background.form;

public class IndexPictureForm {

	private String pictureUrl;
	
	private int pictureIndex;

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public int getPictureIndex() {
		return pictureIndex;
	}

	public void setPictureIndex(int pictureIndex) {
		this.pictureIndex = pictureIndex;
	}

	@Override
	public String toString() {
		return "IndexPictureForm [pictureUrl=" + pictureUrl + ", pictureIndex=" + pictureIndex + "]";
	}

	public IndexPictureForm(int pictureIndex,String pictureUrl) {
		super();
		this.pictureUrl = pictureUrl;
		this.pictureIndex = pictureIndex;
	}
	public IndexPictureForm() {
		super();
	}
	
}
