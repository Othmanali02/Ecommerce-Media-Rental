
public class Media extends MediaRental implements Comparable<Media> {
	protected String title;
	protected int no_copies;
	protected String code;
	public Media(String code, String title, int no_copies) {
		this.code = code;
		this.title = title;
		this.no_copies = no_copies;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Media() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNo_copies() {
		return no_copies;
	}

	public void setNo_copies(int no_copies) {
		this.no_copies = no_copies;
	}

	@Override
	public int compareTo(Media o) {
		if (this.getTitle().compareTo(o.getTitle()) > 0) {
			return 1;
		} else if (this.getTitle().compareTo(o.getTitle()) < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.compareTo((Media) o) == 0) {
			return true;
		}
		return false;
	}
	
	public void NumberOfCopies(boolean add) {
		if (add == true) {
			this.no_copies++;
		} else {
			this.no_copies--;
		}
	}
	
	public String toString() {
		return "- " + code + " - Title: " +  title + " - Copies: " + no_copies + " ";
	}
}
