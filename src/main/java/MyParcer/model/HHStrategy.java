package MyParcer.model;

import MyParcer.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aero on 23.05.2017.
 */
public class HHStrategy implements Strategy {
    private final static String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<Vacancy>();

        try {
            int pageNumber = 0;
            Document doc;

            while (true) {
                doc = getDocument(searchString, pageNumber++);
                if (doc == null) break;

                Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0) break;

                for (Element element : elements) {
                    //title
                    Element titleElement = element.select("[data_qa=vacancy-serp__vacancy-title]").first();
                    String title = titleElement.text();

                    //salary
                    Element salaryElement = element.select("[data_qa=vacancy-serp__vacancy-compensation]").first();
                    String salary = "";
                    if (salaryElement != null) {
                        salary = salaryElement.text();
                    }

                    //city
                    String city = element.select("[data_qa=vacancy-serp__vacancy-address]").first().text();

                    //company
                    String company = element.select("[data_qa=vacancy-serp__vacancy-employer]").first().text();

                    //site
                    String siteName = "http://hh.ua/";

                    //url
                    String url = titleElement.attr("href");

                    //add vacancy to the list
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(title);
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(company);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);
                    vacancies.add(vacancy);


                }

            }
        } catch (Exception e) {

        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("none")
                .get();

        return document;
    }
}
