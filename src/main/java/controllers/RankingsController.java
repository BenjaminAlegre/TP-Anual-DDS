package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RankingsController {

    public ModelAndView pantallaRankings(Request req, Response res){
       return new ModelAndView(null, "pantallaRankings.hbs");
    }

}
