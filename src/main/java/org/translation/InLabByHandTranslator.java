package org.translation;

import java.util.ArrayList;
import java.util.List;

// Extra Task: if your group has extra time, you can add support for another country code in this class.

/**
 * An implementation of the Translator interface which translates
 * the country code "can" to several languages.
 */
public class InLabByHandTranslator implements Translator {
    public static final String CANADA = "can";
    public static final String SPANISH = "es";
    public static final String FRENCH = "fr";

    /**
     * Returns the language abbreviations for all languages whose translations are
     * available for the given country.
     *
     * @param country the country
     * @return list of language abbreviations which are available for this country
     */
    @Override
    public List<String> getCountryLanguages(String country) {
        if (CANADA.equals(country)) {
            return new ArrayList<>(List.of("de", "en", "zh"));
        }else if (SPANISH.equals(country)) {
            return new ArrayList<>(List.of("fr", "es", "zh"));
        }
        else if (FRENCH.equals(country)) {
            return new ArrayList<>(List.of("fr", "en", "zh"));
        }
        return new ArrayList<>();
    }

    /**
     * Returns the country abbreviations for all countries whose translations are
     * available from this Translator.
     *
     * @return list of country abbreviations for which we have translations available
     */
    @Override
    public List<String> getCountries() {
        return new ArrayList<>(List.of(CANADA));
    }

    public List<String> getCountries(String language) {
        switch (language) {
            case CANADA:
                return new ArrayList<>(List.of(CANADA));
            case SPANISH:
                return new ArrayList<>(List.of(SPANISH));
            case FRENCH:
                return new ArrayList<>(List.of(FRENCH));
        }
        return new ArrayList<>();
    }


    /**
     * Returns the name of the country based on the specified country abbreviation and language abbreviation.
     *
     * @param country  the country
     * @param language the language
     * @return the name of the country in the given language or null if no translation is available
     */
    @Override
    public String translate(String country, String language) {
        String returnValue = null;
        if (country.equals(CANADA)) {
            if ("de".equals(language)) {
                returnValue = "Kanada";
            } else if ("en".equals(language)) {
                returnValue = "Canada";
            } else if ("zh".equals(language)) {
                returnValue = "加拿大";
            }
        }
        else if (country.equals(SPANISH)) {
            if ("fr".equals(language)) {
                returnValue = "Espagnol";
            }else if ("es".equals(language)) {
                returnValue = "Spanish";
            } else if ("zh".equals(language)) {
                returnValue = "西班牙语";
            }

        }
        else if (country.equals(FRENCH)) {
            if ("fr".equals(language)) {
                returnValue = "Français";
            }else if ("es".equals(language)) {
                returnValue = "Francés";
            }else if ("zh".equals(language)) {
                returnValue = "法语";
            }
        }
        return returnValue;
    }
}
