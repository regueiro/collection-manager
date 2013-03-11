package es.regueiro.collectionManager.model;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:collection-beans.xml" })
public class ArtistTest {

	@Autowired
	private Validator validator;
	ResourceBundle messages = ResourceBundle.getBundle("ValidationMessages",
			Locale.getDefault());

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void validate() {

		Artist artist = new Artist(null);

		Set<ConstraintViolation<Artist>> constraintViolations = validator
				.validate(artist);

		// The artist name can't be null
		assertEquals(1, constraintViolations.size());
		assertEquals(messages.getString("artistName.notBlank"),
				constraintViolations.iterator().next().getMessage());

		// The artist name can't be empty
		artist.setName("");
		constraintViolations = validator.validate(artist);
		assertEquals(1, constraintViolations.size());
		assertEquals(messages.getString("artistName.notBlank"),
				constraintViolations.iterator().next().getMessage());

		// The artist name can't be blank
		artist.setName("   ");
		constraintViolations = validator.validate(artist);
		assertEquals(1, constraintViolations.size());
		assertEquals(messages.getString("artistName.notBlank"),
				constraintViolations.iterator().next().getMessage());

		// Can set a name correctly
		artist.setName("testName");
		constraintViolations = validator.validate(artist);
		assertEquals(0, constraintViolations.size());

		String mbID1 = "ea71d0d3-4a60-4bd3-9cfe-9c0eec380ea4";
		String mbID2 = "9d2b8399-bdda-4b65-9d97-c7a2f7e1f32c";

		// The musicbrainzid can't be invalid
		artist.setMusicBrainzID("1234541243-123412");
		constraintViolations = validator.validate(artist);
		assertEquals(1, constraintViolations.size());
		assertEquals(messages.getString("artistMBID.notValid"),
				constraintViolations.iterator().next().getMessage());

		// Can set a valid musicbrainzid
		artist.setMusicBrainzID(mbID1);
		constraintViolations = validator.validate(artist);
		assertEquals(0, constraintViolations.size());
		
		artist.setMusicBrainzID(mbID2);
		constraintViolations = validator.validate(artist);
		assertEquals(0, constraintViolations.size());
	}
}
