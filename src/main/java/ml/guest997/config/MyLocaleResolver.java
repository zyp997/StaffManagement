package ml.guest997.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//注意：这里的组件名一定要是 localeResolver，否则就是不生效的。
@Configuration("localeResolver")
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String language = httpServletRequest.getParameter("l");
        Locale locale = Locale.getDefault();        //通过下面的非空判断，没有获取到用户配置的就使用 SpringBoot 默认的配置。
        if(!StringUtils.isEmpty(language)){
            //分割请求参数，语言_地区
            String[] split = language.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
