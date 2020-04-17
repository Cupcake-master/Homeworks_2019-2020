package ru.bulat.config;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.Locale;

public class MyViewResolver extends UrlBasedViewResolver {
    private static final String URL_ERROR = "error: ";

    @Override
    protected View createView(String viewName, Locale locale) throws Exception {
        if (viewName.startsWith(URL_ERROR)){
            viewName = "error";
            super.createView(viewName, locale);
        }
        return super.createView(viewName, locale);
    }
}
