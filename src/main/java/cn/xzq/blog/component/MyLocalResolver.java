package cn.xzq.blog.component;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AbstractLocaleContextResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @program: blog
 * @description:
 * @author: XZQ
 * @create: 2019-10-24 18:12
 **/
public class MyLocalResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");
        Locale locale = Locale.getDefault();
        if (session.getAttribute("lang") != null && StringUtils.isEmpty(lang)) {
            lang = (String) session.getAttribute("lang");
        }
        if (!StringUtils.isEmpty(lang)) {
            String[] split = lang.split("_");
            locale = new Locale(split[0], split[1]);
            session.setAttribute("lang", lang);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }


}
