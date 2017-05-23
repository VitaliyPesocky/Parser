package MyParcer;

import MyParcer.model.Model;
import MyParcer.model.Provider;

import java.util.Arrays;

/**
 * Created by Aero on 23.05.2017.
 */
public class Controller {
    Model model;

    public Controller(Model model) {
        if(model == null){throw new IllegalArgumentException();}
        this.model = model;
    }

    public void onCitySelect(String cityName){
        model.selectCity(cityName);
    }
}
