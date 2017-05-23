package MyParcer;

import MyParcer.model.HHStrategy;
import MyParcer.model.Model;
import MyParcer.model.Provider;
import MyParcer.view.HtmlView;

/**
 * Created by Aero on 23.05.2017.
 */
public class Aggregator {
    public static void main(String[] args) {
        HHStrategy strategy = new HHStrategy();
        Provider provider = new Provider(strategy);
        HtmlView htmlView = new HtmlView();
        Model model = new Model(htmlView, provider);
        htmlView.setController(new Controller(model));
        htmlView.userCitySelectEmulationMethod();
    }
}
