package Entities;

public class ImageTable {

	private int Id;
	private String Link;
	private byte[] Image;

	public ImageTable() {
		super();
	}

	public ImageTable(int id, String links, byte[] image) {
		super();
		Id = id;
		Link = links;
		Image = image;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getLinks() {
		return Link;
	}

	public void setLinks(String links) {
		Link = links;
	}

	public byte[] getImage() {
		return Image;
	}

	public void setImage(byte[] image) {
		Image = image;
	}

}
