package com.wenzeasy.models;

import java.util.ArrayList;
import java.util.List;

public class Language {

    private String code;
    private String englishName;
    private String localName;
    private String flag;
    private boolean selected;

    public Language() {
    }

    public Language(String code, String englishName, String localName, String flag, boolean selected) {
        this.code = code;
        this.englishName = englishName;
        this.localName = localName;
        this.flag = flag;
        this.selected = selected;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    class LanguagesList {

        List<Language> languages;

        public LanguagesList() {
            this.languages = new ArrayList<>();
        }

        public List<Language> languageList() {
            this.languages.add(new Language("en", "English", "English", "assets/img/united-states-of-america.png", true));
            this.languages.add(new Language("ar", "Arabic", "العربية", "assets/img/united-arab-emirates.png", false));
            this.languages.add(new Language("es", "Spanish", "Spana", "assets/img/spain.png", false));
            this.languages.add(new Language("fr", "French (France)", "Français - France", "assets/img/france.png", false));
            this.languages.add(new Language("fr", "French (Canada)", "Français - Canadien", "assets/img/canada.png", false));
            this.languages.add(new Language("pr", "Brazilian", "Brazilian", "assets/img/brazil.png", false));

            return this.languages;
        }
    }
}
