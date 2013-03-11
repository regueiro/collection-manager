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
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:collection-beans.xml" })
public class ReleaseTest {

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

		Artist artist = mock(Artist.class);
		Release release = new Release(artist, null);

		Set<ConstraintViolation<Release>> constraintViolations = validator
				.validate(release);

		// The release name can't be null
		assertEquals(1, constraintViolations.size());
		assertEquals(messages.getString("releaseTitle.notBlank"),
				constraintViolations.iterator().next().getMessage());

		// The release name can't be empty
		release.setTitle("");
		constraintViolations = validator.validate(release);
		assertEquals(1, constraintViolations.size());
		assertEquals(messages.getString("releaseTitle.notBlank"),
				constraintViolations.iterator().next().getMessage());

		// The release name can't be blank
		release.setTitle("   ");
		constraintViolations = validator.validate(release);
		assertEquals(1, constraintViolations.size());
		assertEquals(messages.getString("releaseTitle.notBlank"),
				constraintViolations.iterator().next().getMessage());

		// Can set a name correctly
		release.setTitle("testTitle");
		constraintViolations = validator.validate(release);
		assertEquals(0, constraintViolations.size());

		String mbID1 = "ea71d0d3-4a60-4bd3-9cfe-9c0eec380ea4";
		String mbID2 = "9d2b8399-bdda-4b65-9d97-c7a2f7e1f32c";

		// The musicbrainzid can't be invalid
		release.setMusicBrainzID("1234541243-123412");
		constraintViolations = validator.validate(release);
		assertEquals(1, constraintViolations.size());
		assertEquals(messages.getString("releaseMBID.notValid"),
				constraintViolations.iterator().next().getMessage());

		// Can set a valid musicbrainzid
		release.setMusicBrainzID(mbID1);
		constraintViolations = validator.validate(release);
		assertEquals(0, constraintViolations.size());

		release.setMusicBrainzID(mbID2);
		constraintViolations = validator.validate(release);
		assertEquals(0, constraintViolations.size());

		// The year can't be less than 4 digits long
		release.setYear("99");
		constraintViolations = validator.validate(release);
		assertEquals(1, constraintViolations.size());
		assertEquals(messages.getString("releaseYear.4digits"),
				constraintViolations.iterator().next().getMessage());

		// The year can't be more than 4 digits long
		release.setYear("01990");
		constraintViolations = validator.validate(release);
		assertEquals(1, constraintViolations.size());
		assertEquals(messages.getString("releaseYear.4digits"),
				constraintViolations.iterator().next().getMessage());

		// Can set a valid year
		release.setYear("1999");
		constraintViolations = validator.validate(release);
		assertEquals(0, constraintViolations.size());

		release.setYear("2014");
		constraintViolations = validator.validate(release);
		assertEquals(0, constraintViolations.size());

		// The artist and title can't be null
		release = new Release(null, null);
		constraintViolations = validator.validate(release);
		assertEquals(2, constraintViolations.size());
		for (ConstraintViolation<Release> mes : constraintViolations) {
			assertTrue(mes.getMessage().equals(
					messages.getString("releaseArtist.notNull"))
					|| mes.getMessage().equals(
							messages.getString("releaseTitle.notBlank")));
		}

		// Can set a valid artist but not with an invalid title
		release = new Release(artist, null);
		constraintViolations = validator.validate(release);
		assertEquals(1, constraintViolations.size());
		assertEquals(messages.getString("releaseTitle.notBlank"),
				constraintViolations.iterator().next().getMessage());

		// Can set a valid artist now
		release = new Release(artist, "title");
		constraintViolations = validator.validate(release);
		assertEquals(0, constraintViolations.size());
	}
}
