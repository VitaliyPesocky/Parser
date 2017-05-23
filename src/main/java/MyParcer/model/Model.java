package MyParcer.model;

import MyParcer.view.View;
import MyParcer.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aero on 23.05.2017.
 */
public class Model {
    View view;
    Provider[] providers;

    public Model(View view, Provider... providers) {
        if(providers.length == 0){
            throw new IllegalArgumentException();
        }else if(view == null || providers == null){
            throw new IllegalArgumentException();
        }else{
            this.view = view;
            this.providers = providers;
        }
    }

    public void selectCity(String city){
        List<Vacancy> vacancies = new ArrayList<Vacancy>();
        for (Provider provider : providers) {
            vacancies.addAll(provider.getJavaVacancies(city));
        }
        view.update(vacancies);
    }
}
