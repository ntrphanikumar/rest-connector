package com.ntrphanikumar.restconnect.controllers;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntrphanikumar.restconnect.model.RequestModel;
import com.ntrphanikumar.restconnect.model.User;

@Controller
@RequestMapping("/translate")
public class TranslatorController {

    private final HttpClient client;

    @Autowired
    public TranslatorController(DefaultHttpClient client) {
        this.client = client;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {
        return "request";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String consume(@ModelAttribute RequestModel requestModel, Model model) {
        try {
            HttpGet httpGet = new HttpGet(requestModel.getUrl());
            httpGet.addHeader("accept", "application/json");
            HttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != 200) {
                return gotoError(new RuntimeException("Got error code: " + response.getStatusLine().getStatusCode()),
                        model);
            }
            User user = new ObjectMapper().readValue(response.getEntity().getContent(), User.class);
            model.addAttribute(user);
            return "User-response";
        } catch (Exception e) {
            e.printStackTrace();
            return gotoError(e, model);
        }
    }

    private String gotoError(Exception e, Model model) {
        model.addAttribute("error", e);
        return "error";
    }
}
