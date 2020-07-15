/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character.person;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.character.Character;
import net.sandrohc.jikan.query.Query;

public class CharacterQuery extends Query<Character> {

	private final int id;

	public CharacterQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/character/" + id;
	}

	@Override
	public Class<Character> getRequestClass() {
		return Character.class;
	}

}
