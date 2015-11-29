package cards.controllers;

import cards.SessionManager;
import cards.models.CardSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandra on 11/21/15.
 */
@Controller
public class PageController {

    @Autowired
    private SessionManager sessionManager;

    @RequestMapping(value = "/")
    public String index(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {

            List<CardSet> cardsets = new LinkedList<>();
            cardsets.add(new CardSet(1, "Первый набор", false, "you", "now", "Это первый альбом", "/style/images/albums/1.jpg"));
            cardsets.add(new CardSet(2, "Ололо", false, "you", "now", "Это еще один альбом", "/style/images/albums/2.jpg"));
            cardsets.add(new CardSet(3, "Мамонты", false, "you", "now", "И это альбом", "/style/images/albums/3.jpg"));
            cardsets.add(new CardSet(4, "Я люблю малинку", false, "you", "now", "Такой вот альбом", "/style/images/albums/4.jpg"));

            cardsets.add(new CardSet(5, "Сколько же тут наборов...", false, "you", "now", "Такой вот альбом", "/style/images/albums/5.jpg"));
            cardsets.add(new CardSet(6, "Не могу уже тут придумывать названия", false, "you", "now", "Такой вот альбом", "/style/images/albums/6.jpg"));
            cardsets.add(new CardSet(7, "Как дела?", false, "you", "now", "Такой вот альбом", "/style/images/albums/7.jpg"));
            cardsets.add(new CardSet(8, "Пыщ-пыщ", false, "you", "now", "Такой вот альбом", "/style/images/albums/8.jpg"));
            cardsets.add(new CardSet(9, "Чешет спину", false, "you", "now", "Такой вот альбом", "/style/images/albums/9.jpg"));

            model.addAttribute("cardsets", cardsets);

            return "userpage";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/add")
    public String add(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "add";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/create_set", method = RequestMethod.GET)
    public String create_set(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "create_set";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/edit_set")
    public String edit_set(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "edit_set";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/category")
    public String category(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "category";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/learn")
    public String learn(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "learn";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/learn2")
    public String learn2(ModelMap model, @CookieValue(value = "sid", defaultValue = "") String sessionId){
        if (sessionManager.sessionExists(sessionId)) {
            return "learn2";
        } else {
            return "index";
        }
    }


    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(@CookieValue("sid") String sessionId){
        try {
            sessionManager.deleteSession(sessionId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "index";
    }

}

