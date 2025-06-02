package it.uniroma3.siw.model;

public enum Genre {
	FICTION("Fiction"),
	NONFICTION("Non Fiction"),
	FANTASY("Fantasy"),
	MYSTERY("Mystery"),
	SCIENCEFICTION("Science Fiction"),
	ROMANCE("Romance"),
	THRILLER("Thriller"),
	HORROR("Horror"),
	MEMOIR("Memoir"),
	HISTORICAL("Historical");
	
	private final String label;
	
	Genre(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
