package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    // TODO Task: pick appropriate instance variables for this class
    private Map<String, List<String>> countryLanguages;
    private Map<String, Map<String, String>> translations;
    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */
    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        countryLanguages = new HashMap<>();
        translations = new HashMap<>();
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            // TODO Task: use the data in the jsonArray to populate your instance variables
            //            Note: this will likely be one of the most substantial pieces of code you write in this lab.
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject countryData = jsonArray.getJSONObject(i);
                String alpha3 = countryData.getString("alpha3");

                // Extract language translations
                List<String> languages = new ArrayList<>(countryData.keySet());
                languages.remove("id");
                languages.remove("alpha2");
                languages.remove("alpha3");

                // Store languages for the country
                countryLanguages.put(alpha3, new ArrayList<>(languages));

                // Create a map of language translations for the country
                Map<String, String> countryTranslationMap = new HashMap<>();
                for (String lang : languages) {
                    countryTranslationMap.put(lang, countryData.getString(lang));
                }

                // Store the translation map
                translations.put(alpha3, countryTranslationMap);
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        // TODO Task: return an appropriate list of language codes,
        //            but make sure there is no aliasing to a mutable object
        if (countryLanguages.containsKey(country)) {
            return new ArrayList<>(countryLanguages.get(country));
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getCountries() {
        // TODO Task: return an appropriate list of country codes,
        //            but make sure there is no aliasing to a mutable object
        return new ArrayList<>(countryLanguages.keySet());
    }

    @Override
    public String translate(String country, String language) {
        if (translations.containsKey(country) && translations.get(country).containsKey(language)) {
            return translations.get(country).get(language);
        }
        return "Translation not available";
    }
}
