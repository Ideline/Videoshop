package se.video_shop.rental_system.rental_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.video_shop.rental_system.rental_system.entities.Film;
import se.video_shop.rental_system.rental_system.entities.FilmInfo;
import se.video_shop.rental_system.rental_system.formmodels.FilmInfoForm;
import se.video_shop.rental_system.rental_system.formmodels.FilmSearchForm;
import se.video_shop.rental_system.rental_system.formmodels.NewFilmForm;
import se.video_shop.rental_system.rental_system.repositories.FilmInfoRepository;
import se.video_shop.rental_system.rental_system.repositories.FilmRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/film")
public class FilmController {

    private final FilmInfoRepository filmInfoRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public FilmController(FilmInfoRepository filmInfoRepository, FilmRepository filmRepository) {
        this.filmInfoRepository = filmInfoRepository;
        this.filmRepository = filmRepository;
    }

    @GetMapping("/{id}")
    public String getFilmInfo(@PathVariable int id, Model model, NewFilmForm newFilmForm) {

        List<String> format = new ArrayList<>();
        format.add("DVD");
        format.add("Blu-Ray");

        List<Film> films;

        if (filmInfoRepository.getOne(id).getFilms().size() > 0) {
            films = filmInfoRepository.getOne(id).getFilms();
            model.addAttribute("films", films);
        }

        model.addAttribute("filmInfo", filmInfoRepository.getOne(id));
        model.addAttribute("allFormats", format);
        model.addAttribute("nextFilmID", id + 1);
        model.addAttribute("prevFilmID", id - 1);
        return "film/info";
    }

    @GetMapping("/{productNumber}/delete/{filmID}")
    public String deleteFilm(@PathVariable int productNumber, @PathVariable int filmID) {

        //TODO: Fixa så att man hanterar rentalInfo också om filmen är uthyrd
        filmRepository.deleteById(filmID);

        return "redirect:/film/{productNumber}";
    }

    @PostMapping("/{id}")
    public String register(@PathVariable int id, @Valid NewFilmForm newFilmForm, BindingResult result) {

        newFilmForm.validate(newFilmForm, result);

        // TODO: fixa så att man  får felmeddelande om man inte valt ett format!!
        if (result.hasErrors()) {
            return "film/{id}";
        }
        Film film = new Film(filmInfoRepository.getOne(id), newFilmForm.getFormat());
        filmRepository.saveAndFlush(film);

        return "redirect:/film/{id}";
    }

    @GetMapping("/search")
    public String search(FilmSearchForm filmSearchForm, Model model) {


        List<String> format = new ArrayList<>();
        format.add("DVD");
        format.add("Blu-Ray");

        List<String> genres = new ArrayList<>();
        List<String> genres2 = new ArrayList<>();
        genres.add("Komedi");
        genres.add("Drama");
        genres.add("Action");
        genres.add("Thriller");
        genres.add("Krig");
        genres.add("Rysare");
        genres2.add("Science Fiction");
        genres2.add("Dokumentär");
        genres2.add("Musik");
        genres2.add("Äventyr");
        genres2.add("Familj");
        genres2.add("Barn");

        model.addAttribute("allFormats", format);
        model.addAttribute("allGenres", genres);
        model.addAttribute("allGenres2", genres2);
        return "film/search";
    }

    @GetMapping("/searchresult")
    public String getSearchResult(@RequestParam(required = false) Integer productNumber,
                                  @RequestParam(required = false) String genre,
                                  @RequestParam(required = false) String title,
                                  @RequestParam(required = false) Integer page,
                                  Model model, HttpServletRequest httpServletRequest) {

        String searchWord;

        if (productNumber != null) {

            searchWord = "Produktnummer: " + productNumber;
            Page<FilmInfo> filmInfoPage = getResultList(genre, title, productNumber, page, 1);
            addToModel(filmInfoPage, searchWord, model, httpServletRequest);

        } else if (!title.equals("") && genre != null) {

            searchWord = getSearchWords(genre, title);
            Page<FilmInfo> filmInfoPage = getResultList(genre, title, productNumber, page, 2);
            addToModel(filmInfoPage, searchWord, model, httpServletRequest);


        } else if (genre != null) {

            searchWord = getSearchWords(genre, title);
            Page<FilmInfo> filmInfoPage = getResultList(genre, title, productNumber, page, 3);
            addToModel(filmInfoPage, searchWord, model, httpServletRequest);

        } else if (!title.equals("")) {

            searchWord = getSearchWords(genre, title);
            Page<FilmInfo> filmInfoPage = getResultList(genre, title, productNumber, page, 4);
            addToModel(filmInfoPage, searchWord, model, httpServletRequest);
        }
        else{
            return "film/search";
        }
        return "/film/searchResult";
    }

    private void addToModel(Page<FilmInfo> filmInfoPage, String searchWord, Model model, HttpServletRequest httpServletRequest) {

        String query = httpServletRequest.getQueryString();
        String url = httpServletRequest.getRequestURI() + "?" + query;
        url = url.replaceAll("[&?]page=\\d*", "");

        final String PAGE = "page=";
        int queryIndex = query.lastIndexOf(PAGE);
        int pageIndex = 0;
        if(queryIndex > 0) {
            queryIndex += PAGE.length();
            String pageIndexStr = query.substring(queryIndex);
            if(!pageIndexStr.equals("")) {
                pageIndex = Integer.parseInt(pageIndexStr);
            }
        }


        model.addAttribute("currentPage", pageIndex);
        model.addAttribute("nextPage", pageIndex >= filmInfoPage.getTotalPages()-1 ? pageIndex : pageIndex+1);
        model.addAttribute("prevPage", pageIndex < 1 ? pageIndex : pageIndex-1);
        model.addAttribute("currentUrl", url);
        model.addAttribute("searchResults", filmInfoPage);
        model.addAttribute("searchWord", searchWord);
    }

    public String getSearchWords(String genre, String title) {
        String searchText = (genre != null ? "Kategori: " + genre.replace(",", ", ") : "");
        if(!title.equals("")) {
            searchText = searchText + (searchText == "" ? title : " + Sökord: " + title);
        }
        return searchText;
    }

    public Page<FilmInfo> getResultList(String genre, String title, Integer productNumber, Integer page, int nr) {

        String[] list = (genre == null ? null : genre.split(","));
        List<String> genres = new ArrayList<>();

        if(list != null){
            for(String s : list){
                genres.add(s);
            }
        }

        switch (nr) {
            case 1:
                Page<FilmInfo> filmInfoPage1 = filmInfoRepository.findByProductNumber(productNumber, PageRequest.of(page != null ? page : 0, 10));
                return filmInfoPage1;
            case 2:
                Page<FilmInfo> filmInfoPage2 = filmInfoRepository.findAllByTitleContainsAndGenreIn(title, genres, PageRequest.of(page != null ? page : 0, 10));
                return filmInfoPage2;
            case 3:
                Page<FilmInfo> filmInfoPage3 = filmInfoRepository.findAllByGenreIn(genres, PageRequest.of(page != null ? page : 0, 10));
                return filmInfoPage3;
            case 4:
                Page<FilmInfo> filmInfoPage4 = filmInfoRepository.findByTitleContains(title, PageRequest.of(page != null ? page : 0, 10));
                return filmInfoPage4;
        }
        return null;
    }

    @PostMapping("/register")
    public String register(@Valid FilmInfoForm filmInfoForm, BindingResult result) {

        filmInfoForm.validate(filmInfoForm, result);

        if (result.hasErrors()) {
            return "film/registration";
        }
        FilmInfo fi = new FilmInfo(filmInfoForm.getTitle(), filmInfoForm.getGenre(),
                filmInfoForm.getDescription(), filmInfoForm.getReleaseDate(),
                filmInfoForm.getAgeLimit(), filmInfoForm.getImageUrl());
        FilmInfo newFi = filmInfoRepository.saveAndFlush(fi);

        return "redirect:/film/" + newFi.getProductNumber();
    }

    @GetMapping("/register")
    public String register(Model model, FilmInfoForm filmInfoForm) {

        List<String> genres = getGenreSelectOptions();

        model.addAttribute("allGenres", genres);

        return "film/registration";
    }

    public List<String> getGenreSelectOptions(){

        List<String> genres = new ArrayList<>();
        genres.add("Komedi");
        genres.add("Drama");
        genres.add("Action");
        genres.add("Thriller");
        genres.add("Science Fiction");
        genres.add("Rysare");
        genres.add("Krig");
        genres.add("Dokumentär");
        genres.add("Musik");
        genres.add("Äventyr");
        genres.add("Familj");
        genres.add("Barn");

        return genres;
    }
}
