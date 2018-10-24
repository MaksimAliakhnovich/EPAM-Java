package main.java.edu.epam.selectioncommittee.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by mascon on 24.10.2018.
 */
public enum LocaleManager {
    INSTANCE;
    private ResourceBundle resourceBundle;
    private final String resourceName = "messages";

    LocaleManager() {
        this.resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    }

    public String getString(String string) {
        return resourceBundle.getString(string);
    }

    public LocaleManager getInstance() {
        return INSTANCE;
    }
}
