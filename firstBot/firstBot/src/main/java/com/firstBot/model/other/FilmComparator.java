package com.firstBot.model.other;

import java.util.Comparator;

import com.firstBot.entity.Film;

public class FilmComparator implements Comparator<Film> {

	@Override
	public int compare(Film f1, Film f2) {
		return f1.getAvgRating() > f2.getAvgRating() ? -1 : (f1.getAvgRating() < f2.getAvgRating() ? 1 : 0);
	}

}
