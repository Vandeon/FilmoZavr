package com.firstBot.content;

import java.util.ArrayList;
import java.util.List;

import com.firstBot.entity.Film;
import com.firstBot.entity.Genre;
import com.firstBot.service.FilmService;
import com.firstBot.service.GenreService;

public class ContentMaker {
	
	public void makeContent(FilmService fs, GenreService gs) {
		
		String genres[] = {"Crime","Drama", "Fantasy", "Horror", "Action", "Adventure", "Animation", "Family", "Comedy"};
		for(int i = 0; i < genres.length; i++) {
			Genre genre = new Genre();
			genre.setName(genres[i]);
			gs.save(genre);
		}
		
		String filmsName[] = {"Black Panther", "The greatest showman", "Wonder", "Coco", "Lady Bird", "Dunkirk", "Wonder women", "Baywatch", "Guardians of the Galaxy 2", "Deadpool"};
		int filmsYear[] = {2018, 2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017, 2016};
		String filmUrls[] = {"https://i.pinimg.com/originals/68/da/8f/68da8f27eb19060ed577cd7055b8c20b.jpg","https://i.pinimg.com/originals/94/45/b0/9445b09aef507f060d1338cd417d9d65.jpg",
				"https://s-media-cache-ak0.pinimg.com/originals/86/55/bf/8655bf8877b751b5d6a137ed2f41ec38.jpg","https://i.ytimg.com/vi/ImutnoiBixY/maxresdefault.jpg",
				"https://static1.squarespace.com/static/5890fda486e6c0dcab5025fe/5890fff2ebbd1a33c53d0e4a/5aa06e38e2c4839d2e53418c/1521053298481/Lady+Bird+Ho.jpg?format=500w","https://s-media-cache-ak0.pinimg.com/originals/42/38/4c/42384c0ea4f232d884ff62245486b9cc.jpg",
				"https://thumbor.forbes.com/thumbor/960x0/smart/https%3A%2F%2Fblogs-images.forbes.com%2Fscottmendelson%2Ffiles%2F2017%2F08%2FWonder-Woman-Tank-Poster-HD-1200x811.jpg","https://i.ytimg.com/vi/NNOz2xuYXXs/maxresdefault.jpg",
				"https://imagesvc.timeincapp.com/v3/fan/image?url=https%3A%2F%2Fbamsmackpow.com%2Ffiles%2F2017%2F04%2FGuardians_Of_The_Galaxy_Vol_2_Official_Poster_Landscape.jpg&c=sc&w=850&h=560","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPCcenJh-79AQFMFTnH_WWOzVBExY6s8HIxQjWYMvHo6Sk8YLZ"};
		String trailerUrls[] = {"https://www.youtube.com/watch?v=xjDjIWPwcPU","https://www.youtube.com/watch?v=4zOeiLhcgPo",
				"https://www.youtube.com/watch?v=Ob7fPOzbmzE","https://www.youtube.com/watch?v=Ga6RYejo6Hk",
				"https://www.youtube.com/watch?v=cNi_HC839Wo","https://www.youtube.com/watch?v=F-eMt3SrfFU",
				"https://www.youtube.com/watch?v=VSB4wGIdDwo","https://www.youtube.com/watch?v=TDteZ0YrhSU",
				"https://www.youtube.com/watch?v=duGqrYw4usE","https://www.youtube.com/watch?v=FyKWUTwSYAs"};
		String filmsGenres[] = {"Action Adventure", "Drama", "Drama Family", "Animation Adventure Comedy", "Drama Comedy", "Action Drama", "Action Adventure Fantasy", "Action Comedy Crime", "Action Adventure", "Action Adventure Comedy"};
		String descriptions[] = {"\"Black Panther\" follows T'Challa who, after the events of \"Captain America: Civil War,\" returns home to the isolated, technologically advanced African nation of Wakanda to take his place as King. However, when an old enemy reappears on the radar, T'Challa's mettle as King and Black Panther is tested when he is drawn into a conflict that puts the entire fate of Wakanda and the world at risk.",
				"Celebrates the birth of show business, and tells of a visionary who rose from nothing to create a spectacle that became a worldwide sensation. ..... Crazy Credits. If you look closely at the early end title cards, the small graphics in the corners of the cards reflect the roles of both the cast and crew. ",
				"The film follows a child with Treacher Collins syndrome trying to fit in. Wonder was released in the United States on November 17, 2017, by Lionsgate.",
				"Coco is a 2017 American 3D computer-animated fantasy film produced by Pixar Animation Studios and released by Walt Disney Pictures. Based on an original idea by Lee Unkrich, it is directed by Unkrich and co-directed by Adrian Molina. The story follows a 12-year-old boy named Miguel Rivera who is accidentally ...",
				"At the behest of her mother, Lady Bird takes a job at a coffee shop, where she meets a young musician, Kyle. He and Lady Bird begin a romantic relationship, and she and Julie drift apart. After Jenna, one of the popular girls at the school, is reprimanded by a teacher, Sister Sarah, for wearing a short skirt, Lady Bird suggests ...",
				"The enemy forces have cornered hundreds of thousands of British and French soldiers on the French coast at Dunkirk. A small number of primarily-French soldiers are guarding the perimeter, where the British and French soldiers are amassed, which is getting increasingly smaller as enemy forces advance. Most of those ...",
				"Wonder Woman is a 2017 American superhero film based on the DC Comics character of the same name, distributed by Warner Bros. Pictures. It is the fourth installment in the DC Extended Universe (DCEU). The film is directed by Patty Jenkins, with a screenplay by Allan Heinberg, from a story by Heinberg, Zack Snyder, ...",
				"American action comedy film directed by Seth Gordon and based on the television series of the same name. Written by Mark Swift and Damian Shannon, the film stars Dwayne Johnson, Zac Efron, Jon Bass, Alexandra Daddario, Kelly Rohrbach and Priyanka Chopra. The plot follows lifeguard Mitch ...",
				"American superhero film based on the Marvel Comics superhero team Guardians of the Galaxy, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures. It is the sequel to 2014's Guardians of the Galaxy and the fifteenth film in the Marvel Cinematic ...",
				"This occurred during a line-wide revamp of X-Men related comics, with Cable becoming Soldier X and X-Force becoming X-Statix. Simone notes that \"When I took the Deadpool job, the revamp hadn't been planned, so it was a complete surprise. Thankfully, we heard about it in time to make adjustments to the early scripts."};
		for(int i = 0; i < filmsName.length; i++) {
			Film film = new Film();
			film.setName(filmsName[i]);
			film.setYear(filmsYear[i]);
			film.setFilmUrl(filmUrls[i]);
			film.setTrailerUrl(trailerUrls[i]);
			film.setDescription(descriptions[i]);
			String temp[] = filmsGenres[i].split(" ");
			List<Genre> filmGenres = new ArrayList<Genre>();
			for(int j = 0; j < temp.length; j++) {
				System.out.println("temp"+ j +" = " + temp[j]);
				Genre genre = new Genre();
				genre = gs.findByName(temp[j]);
				filmGenres.add(genre);
				System.out.println("genre - " + genre);
			}
			System.out.println(filmGenres);
			film.setGenre(filmGenres);
			fs.save(film);
		}
	}
	
}