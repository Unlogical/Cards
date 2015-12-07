package cards.controllers;

import cards.CardSets;
import cards.Covers;
import cards.SessionManager;
import cards.models.CardSet;
import cards.models.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandra on 11/29/15.
 */

@RestController
public class RestSetController {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private Covers covers;

    @Autowired
    private CardSets cardSets;

    @RequestMapping(value = "create_set", method = RequestMethod.POST)
    public ResultMessage createSet(
            @CookieValue(value = "sid", defaultValue = "") String sessionId,
                                                           String title,
                                                           boolean privacy,
                                                           String description,
                                                           String[] tags,
                                    @RequestParam("cover") MultipartFile cover) {
//        try {
//            byte[] coverBytes = cover.getBytes();
//            covers.saveCover(coverBytes);
//        } catch (IOException e) {
//            return new ResultMessage("fail", "failed to create set", null);
//
//        }

        try {
            System.out.println("Creating cardset");
            long userId = sessionManager.sessionToId(sessionId);
            CardSet cardSet = cardSets.createCardSet(userId, privacy, title, description);
            System.out.println("Cardset created");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }
}
