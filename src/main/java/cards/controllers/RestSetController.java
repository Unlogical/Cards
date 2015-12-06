package cards.controllers;

import cards.Covers;
import cards.SessionManager;
import cards.models.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandra on 11/29/15.
 */

@RestController
public class RestSetController {

    @Autowired
    private SessionManager sessionManager;

    private Covers covers;

    @RequestMapping(value = "create_set", method = RequestMethod.POST)
    public ResultMessage createSet(@CookieValue(value = "sid", defaultValue = "") String sessionId,
                                                                                  String title,
                                                                                  boolean privacy,
                                                                                  String description,
                                                                                  String[] tags,
                                                           @RequestParam("cover") MultipartFile cover) {


        try {
            byte[] coverBytes = cover.getBytes();
            covers.saveCover(coverBytes);
        } catch (IOException e) {
            return new ResultMessage("fail", "failed to create set", null);

        }

        return null;
    }
}
