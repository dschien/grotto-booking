package ac.uk.bristol.cs.santa.grottobooking.web;

/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import ac.uk.bristol.cs.santa.grottobooking.business.GeoLookup;
import ac.uk.bristol.cs.santa.grottobooking.route.Location;
import ac.uk.bristol.cs.santa.grottobooking.route.LocationRoutePlanning;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Joe Grandja
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin() {
        // TODO Enable form login with Spring Security (trigger error for now)
        return "redirect:/login-error";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @Autowired
    GeoLookup geoLookup;


    @RequestMapping(value = "/geolookup", method = RequestMethod.POST)
    @ResponseBody
    public String geolookup(@RequestBody String address) throws InterruptedException, ApiException, IOException {
        return String.valueOf(geoLookup.latLngFromAddress(address));
    }

    @Autowired
    LocationRoutePlanning routePlanning;

    @RequestMapping(value = "/grottoroute", method = RequestMethod.POST)
    @ResponseBody
    public String grottoRoute(@RequestBody GrottoDTO[] grottos) throws InterruptedException, ApiException, IOException {
        ArrayList<Location> locations = new ArrayList<>();
        for (GrottoDTO grotto : grottos) {
            Pair<Double, Double> latLong = geoLookup.latLngFromAddress(grotto.getAddress());
            locations.add(new Location(grotto.name, latLong.getFirst(), latLong.getSecond()));
        }
        ArrayList<Location> tour = routePlanning.computeOptimalTour(locations, 1000);
        return tour.stream()
                .map(Location::getName)
                .collect(Collectors.joining(","));
    }

}