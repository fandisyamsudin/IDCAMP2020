package com.jetpack.module012.utils

import com.jetpack.module012.R
import com.jetpack.module012.data.Movie
import com.jetpack.module012.data.TVShow

object DataDummy {
    fun dummyMovie(): ArrayList<Movie> {
        val movies = ArrayList<Movie>()

        movies.add(
            Movie(
                1,
                R.drawable.poster_bohemian,
                "Bohemian Rhapsody",
                "80%",
                "02 Okt 2018",
                "2h 15m",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Drama"
            ))

        movies.add(
            Movie(
                2,
                R.drawable.poster_how_to_train,
                "How to Train Your Dragon: The Hidden World",
                "77%",
                "22 Feb 2019",
                "1h 44m",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "Animation, Family, Adventure"
            ))

        movies.add(
            Movie(
                3,
                R.drawable.poster_infinity_war,
                "Avengers: Infinity War",
                "83%",
                "27 Apr 2018",
                "2h 22m",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Adventure, Action, Science Fiction"
            ))

        movies.add(
            Movie(
                4,
                R.drawable.poster_ralph,
                "Ralph Breaks the Internet",
                "72%",
                "21 Okt 2018",
                "1h 52m",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "Family, Animation, Comedy, Adventure"
            ))

        movies.add(
            Movie(
                5,
                R.drawable.poster_cold_persuit,
                "Cold Pursuit",
                "55%",
                "08 Feb 2019",
                "1h 59m",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Action, Crime, Thriller"
            ))

        movies.add(
            Movie(
                6,
                R.drawable.poster_creed,
                "Creed",
                "74%",
                "05 Okt 2015",
                "2h 13m",
                "The former World Heavyweight Champion Rocky Balboa serves as a trainer and mentor to Adonis Johnson, the son of his late friend and former rival Apollo Creed.",
                "Action, Crime, Thriller"
            ))

        movies.add(
            Movie(
                7,
                R.drawable.poster_crimes,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "69%",
                "16 Okt 2018",
                "2h 00m",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "Adventure, Fantasy"
            ))

        movies.add(
            Movie(
                8,
                R.drawable.poster_glass,
                "Glass",
                "66%",
                "18 Jan 2019",
                "2h 9m",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "Thriller, Drama, Science Fiction"
            ))

        movies.add(
            Movie(
                9,
                R.drawable.poster_alita,
                "Alita",
                "70%",
                "02 Feb 2019",
                "2h 2m",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Action, Science Fiction, Adventure"
            ))

        movies.add(
            Movie(
                10,
                R.drawable.poster_aquaman,
                "Aquaman",
                "68%",
                "21 Des 2018",
                "2h 24m",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Action, Adventure, Fantasy"
            ))
        return movies
    }

    fun dummyTvShow(): ArrayList<TVShow> {
        val tv = ArrayList<TVShow>()

        tv.add(
            TVShow(
                1,
                R.drawable.poster_arrow,
                "Arrow",
                "62%",
                "10 Okt 2012",
                "44m",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Crime, Drama, Mystery, Action & Adventure"
            ))

        tv.add(
            TVShow(
                2,
                R.drawable.poster_doom_patrol,
                "Doom Patrol",
                "71%",
                "15 Feb 2019",
                "49m",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Sci-Fi & Fantasy, Action & Adventure"
            ))

        tv.add(
            TVShow(
                3,
                 R.drawable.poster_dragon_ball,
                "Dragon Ball",
                "77%",
                "26 Feb 1986",
                "44m",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "Comedy, Sci-Fi & Fantasy, Animation, Action & Adventure"
            ))

        tv.add(
            TVShow(
                4,
                R.drawable.poster_fairytail,
                "Fairy Tail",
                "68%",
                "6 Mei 2017",
                "25m",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "Action, Adventure, Comedy, Fantasy, Animation"
            ))

        tv.add(
            TVShow(
                5,
                R.drawable.poster_family_guy,
                "Family Guy",
                "66%",
                "31 Jan 1999",
                "22m",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "Animation, Comedy"
            ))

        tv.add(
            TVShow(
                6,
                R.drawable.poster_flash,
                "The Flash",
                "73%",
                "7 Okt 2014",
                "44m",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "Drama, Sci-Fi & Fantasy"
            ))

        tv.add(
            TVShow(
                7,
                R.drawable.poster_gotham,
                "Gotham",
                "72%",
                "22 Sept 2014",
                "43m",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "Drama, Fantasy, Crime"
            ))

        tv.add(
            TVShow(
                8,
                R.drawable.poster_grey_anatomy,
                "Grey's Anatomy",
                "76%",
                "22 Mar 2005",
                "43m",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Drama"
            ))

        tv.add(
            TVShow(
                9,
                R.drawable.poster_hanna,
                "Hanna",
                "70%",
                "28 Mar 2019 ",
                "50m",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "Action & Adventure, Drama"
            ))

        tv.add(
            TVShow(
                10,
                R.drawable.poster_supergirl,
                "Supergirl",
                "67",
                "26 Okt 2015 ",
                "42m",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "Action, Adventure, Drama, Science Fiction"
            ))
        return tv
    }



}