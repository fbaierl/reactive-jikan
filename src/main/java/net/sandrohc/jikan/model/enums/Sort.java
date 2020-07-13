/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Sort {
	@JsonProperty("ascending")
	@JsonAlias("asc")
	ASCENDING,

	@JsonProperty("descending")
	@JsonAlias("desc")
	DESCENDING,
}
