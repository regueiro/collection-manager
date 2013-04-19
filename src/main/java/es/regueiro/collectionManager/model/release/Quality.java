package es.regueiro.collectionManager.model.release;

// TODO: Auto-generated Javadoc
/**
 * The Enum Quality.
 */
public enum Quality {

	FLAC("Flac"),
	MP3_HIGH("MP3 High Quality"),
	MP3("MP3"),
	OGG_HIGH("OGG High Quality"),
	OGG("OGG"),
	M4A_HIGH("MP4 High Quality"),
	M4A("MP4"),
	WMA_HIGH("WMA High Quality"),
	WMA("WMA"),
	OTHER_HIGH("Other High Quality"),
	OTHER("Other");

	private String name;

	/**
	 * Instantiates a new quality.
	 * 
	 * @param name
	 *            the name
	 */
	Quality(String name) {
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Equals.
	 * 
	 * @param name
	 *            the name
	 * @return true, if successful
	 */
	public boolean equals(String name) {
		return this.name.equals(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Gets the.
	 * 
	 * @param name
	 *            the name
	 * @return the quality
	 */
	public static Quality get(String name) {
		for (Quality quality : Quality.values()) {
			if (quality.name.equals(name)) {
				return quality;
			}
		}
		return null;
	}
}