/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryMono;

public class AnimeRecommendationsQuery extends QueryMono<Recommendations> {

	/** The anime ID. */
	private final int id;

	public AnimeRecommendationsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/anime/" + id + "/recommendations";
	}

	@Override
	public Class<Recommendations> getRequestClass() {
		return Recommendations.class;
	}

}