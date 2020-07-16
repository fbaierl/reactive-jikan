package net.sandrohc.jikan.test;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.Picture;
import net.sandrohc.jikan.model.Pictures;
import net.sandrohc.jikan.model.base.MalSubEntity;
import net.sandrohc.jikan.model.character.CharacterSub;
import net.sandrohc.jikan.model.enums.Type;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.model.search.CharacterSearch;
import net.sandrohc.jikan.model.search.PersonSearch;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestPersonTest extends RequestTest {

	@Test
	void fetchPerson() {
		// https://api.jikan.moe/v3/character/36765
		String response = "{\n" +
				"    \"request_hash\": \"request:person:cbd59b95af90c14277375ca57503ca9d3cd54278\",\n" +
				"    \"request_cached\": true,\n" +
				"    \"request_cache_expiry\": 3935,\n" +
				"    \"mal_id\": 11817,\n" +
				"    \"url\": \"https://myanimelist.net/people/11817/Yoshitsugu_Matsuoka\",\n" +
				"    \"image_url\": \"https://cdn.myanimelist.net/images/voiceactors/2/40132.jpg\",\n" +
				"    \"website_url\": null,\n" +
				"    \"name\": \"Yoshitsugu Matsuoka\",\n" +
				"    \"given_name\": \"禎丞\",\n" +
				"    \"family_name\": \"松岡\",\n" +
				"    \"alternate_names\": [],\n" +
				"    \"birthday\": \"1986-09-17T00:00:00+00:00\",\n" +
				"    \"member_favorites\": 25992,\n" +
				"    \"about\": \"Hometown: Hokkaido, Japan\\\\n\\r\\nHeight: 165 cm ...\",\n" +
				"    \"voice_acting_roles\": [\n" +
				"        {\n" +
				"            \"role\": \"Main\",\n" +
				"            \"anime\": {\n" +
				"                \"mal_id\": 11757,\n" +
				"                \"url\": \"https://myanimelist.net/anime/11757/Sword_Art_Online\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/anime/11/39717.jpg?s=0d39a46a21e7dac6a04cb02a3263c72e\",\n" +
				"                \"name\": \"Sword Art Online\"\n" +
				"            },\n" +
				"            \"character\": {\n" +
				"                \"mal_id\": 36765,\n" +
				"                \"url\": \"https://myanimelist.net/character/36765/Kazuto_Kirigaya\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/characters/7/204821.jpg?s=256042ba88a8ba04279940785bef9dc6\",\n" +
				"                \"name\": \"Kirigaya, Kazuto\"\n" +
				"            }\n" +
				"        },\n" +
				"        {\n" +
				"            \"role\": \"Main\",\n" +
				"            \"anime\": {\n" +
				"                \"mal_id\": 31765,\n" +
				"                \"url\": \"https://myanimelist.net/anime/31765/Sword_Art_Online_Movie__Ordinal_Scale\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/anime/4/83811.jpg?s=0f5f07a5eaca4bcfaa4c792d874dcec0\",\n" +
				"                \"name\": \"Sword Art Online Movie: Ordinal Scale\"\n" +
				"            },\n" +
				"            \"character\": {\n" +
				"                \"mal_id\": 36765,\n" +
				"                \"url\": \"https://myanimelist.net/character/36765/Kazuto_Kirigaya\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/characters/7/204821.jpg?s=256042ba88a8ba04279940785bef9dc6\",\n" +
				"                \"name\": \"Kirigaya, Kazuto\"\n" +
				"            }\n" +
				"        }\n" +
				"    ],\n" +
				"    \"anime_staff_positions\": [\n" +
				"        {\n" +
				"            \"position\": \"Theme Song Performance\",\n" +
				"            \"anime\": {\n" +
				"                \"mal_id\": 30205,\n" +
				"                \"url\": \"https://myanimelist.net/anime/30205/Aoharu_x_Kikanjuu\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/anime/4/76271.jpg?s=f5bbc906562d79cadb58efa015a86f20\",\n" +
				"                \"name\": \"Aoharu x Kikanjuu\"\n" +
				"            }\n" +
				"        }\n" +
				"    ],\n" +
				"    \"published_manga\": [\n" +
				"        {\n" +
				"            \"position\": \"Story\",\n" +
				"            \"manga\": {\n" +
				"                \"mal_id\": 98568,\n" +
				"                \"url\": \"https://myanimelist.net/manga/98568/CV_Ore\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/manga/3/179349.jpg?s=6d366ebaf5a1357cb8b67e070f119db1\",\n" +
				"                \"name\": \"CV. Ore!\"\n" +
				"            }\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/person/11817", response);

		Person person = jikan.query().person().get(11817).execute().block();

		assertNotNull(person);
		assertNotNull(person.toString());
		assertEquals(11817, person.malId);
		assertEquals("Yoshitsugu Matsuoka", person.name);
	}

	@Test
	void fetchPictures() {
		// https://api.jikan.moe/v3/person/11817/pictures
		String response = "{\n" +
						  "    \"pictures\": [\n" +
						  "        {\n" +
						  "            \"large\": \"https://cdn.myanimelist.net/images/voiceactors/3/21725.jpg\",\n" +
						  "            \"small\": \"https://cdn.myanimelist.net/images/voiceactors/3/21725.jpg\"\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"large\": \"https://cdn.myanimelist.net/images/voiceactors/2/40132.jpg\",\n" +
						  "            \"small\": \"https://cdn.myanimelist.net/images/voiceactors/2/40132.jpg\"\n" +
						  "        }\n" +
						  "    ]\n" +
						  "}";

		mock(mockServer, "/person/11817/pictures", response);

		Pictures pictures = jikan.query().person().pictures(11817).execute().block();

		assertNotNull(pictures);
		assertNotNull(pictures.toString());
		assertNotNull(pictures.pictures);

		/* Pictures */
		Iterator<Picture> picturesIt = pictures.pictures.iterator();

		Picture p1 = picturesIt.next();
		assertNotNull(p1.toString());
		assertEquals("https://cdn.myanimelist.net/images/voiceactors/3/21725.jpg", p1.large);
		assertEquals("https://cdn.myanimelist.net/images/voiceactors/3/21725.jpg", p1.small);

		Picture p2 = picturesIt.next();
		assertNotNull(p2.toString());
		assertEquals("https://cdn.myanimelist.net/images/voiceactors/2/40132.jpg", p2.large);
		assertEquals("https://cdn.myanimelist.net/images/voiceactors/2/40132.jpg", p2.small);
	}

	@Test
	void fetchSearch() throws UnsupportedEncodingException, JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/search/person?q=asuna&page=1&limit=2
		String response = "{\n" +
						  "    \"results\": [\n" +
						  "        {\n" +
						  "            \"mal_id\": 2209,\n" +
						  "            \"url\": \"https://myanimelist.net/people/2209/Kei_Yasunaga\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/questionmark_23.gif\",\n" +
						  "            \"name\": \"Yasunaga, Kei\",\n" +
						  "            \"alternative_names\": []\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"mal_id\": 41501,\n" +
						  "            \"url\": \"https://myanimelist.net/people/41501/Asuna_Tomari\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/voiceactors/3/54025.jpg?s=f04a9fb60e828c9fbbdd66890029b06c\",\n" +
						  "            \"name\": \"Tomari, Asuna\",\n" +
						  "            \"alternative_names\": []\n" +
						  "        }\n" +
						  "    ],\n" +
						  "    \"last_page\": 1\n" +
						  "}";

		mock(mockServer, "/search/person", response,
				Parameter.param("q", "asuna"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1"));

		PersonSearch search = jikan.query().person().search()
				.query("asuna")
				.page(1)
				.limit(1)
				.execute().block();

		assertNotNull(search);
		assertNotNull(search.toString());
		assertNotNull(search.results);
		assertEquals(1, search.lastPage);

		/* Results */
		Iterator<PersonSub> resultsIt = search.results.iterator();

		PersonSub r1 = resultsIt.next();
		assertNotNull(r1.toString());
		assertEquals(2209, r1.malId);
		assertEquals("https://myanimelist.net/people/2209/Kei_Yasunaga", r1.url);
		assertEquals("https://cdn.myanimelist.net/images/questionmark_23.gif", r1.imageUrl);
		assertEquals("Yasunaga, Kei", r1.name);
		assertTrue(r1.alternativeNames.isEmpty());

		PersonSub r2 = resultsIt.next();
		assertNotNull(r2.toString());
		assertEquals(41501, r2.malId);
		assertEquals("https://myanimelist.net/people/41501/Asuna_Tomari", r2.url);
		assertEquals("https://cdn.myanimelist.net/images/voiceactors/3/54025.jpg?s=f04a9fb60e828c9fbbdd66890029b06c", r2.imageUrl);
		assertEquals("Tomari, Asuna", r2.name);
		assertTrue(r2.alternativeNames.isEmpty());

		assertFalse(resultsIt.hasNext());
	}

}