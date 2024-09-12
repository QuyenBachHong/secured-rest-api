package me.quyen;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SpringBootTest
public class StartupWebsiteTemplateTest {

    /**
     * Elements links = doc.select("a[href]");
     * Elements media = doc.select("[src]"); //<script src="..." />, <img src="..." />
     * Elements imports = doc.select("link[href]");
     * @throws IOException
     */
    @Test
    public void parserHtmlStartupWebsiteTemplateByJsoupLibraryTest() throws IOException {
        String baseInputPath = "src/main/resources/templates/origins/startup-website-template";
        String baseOutputPath = "src/main/resources/templates/startup-website-template";
        Map<String,String> ioPaths = new LinkedHashMap<>();
        Stream.of(
                        "/about.html"
                        , "/blog.html"
                        , "/contact.html"
                        , "/detail.html"
                        , "/feature.html"
                        , "/index.html"
                        , "/price.html"
                        , "/quote.html"
                        , "/service.html"
                        , "/team.html"
                        , "/testimonial.html"
                )
                .map(it -> Map.entry(baseInputPath.trim() + it.trim(), baseOutputPath.trim() + it.trim()))
                .forEach(e -> ioPaths.put(e.getKey(),e.getValue()));


        ioPaths.forEach((inputPath,outputPath) -> {
            File input = new File(inputPath);
            Document doc = null;
            try {
                doc = Jsoup.parse(input, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            doc.select("html").stream()
                    .forEach(html -> {
                        html.attr("xmlns:th","http://www.thymeleaf.org");
                    });
            doc.getElementsByTag("script").stream()
                    .filter(el -> el.attr("src") != null)
                    .forEach(script -> {
                        String src = script.attr("src").trim();
                        String modLink = thymeleafLink(src);
                        script.attr("th:src",modLink);
                        script.removeAttr("src");

                    });
            doc.select("link[href]").stream()
                    .forEach(cssLink -> {
                        String href = cssLink.attr("href");
                        String modLink = thymeleafLink(href);
                        cssLink.attr("th:href",modLink);
                        cssLink.removeAttr("href");
                    });
            doc.select("a[href]").stream()
                    .forEach(aLink -> {
                        String href = aLink.attr("href");
                        if(href.contains(".html")){
                            href = "startup-website-template/" + href;
                        }
                        String modLink = thymeleafLink(href);
                        aLink.attr("th:href",modLink);
                        aLink.removeAttr("href");
                    });
            doc.select("img[src]").stream()
                    .forEach(imgLink -> {
                        String href = imgLink.attr("src");
                        String modLink = thymeleafLink(href);
                        imgLink.attr("th:src",modLink);
                        imgLink.removeAttr("src");
                    });

            File output = new File(outputPath);
            try {
                FileUtils.writeStringToFile(output,doc.outerHtml(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });//forEach(inputPath ->{......});


    }

    /**
     * For <script src="..."></script>
     * For <link href="..."/>
     * For <a href="..."></a>
     * For <img src="..."/>
     * Elements imports = doc.select("link[href]")
     * Elements imports = doc.select("script[src]")
     * Elements links = doc.select("a[href]");
     * Elements imports = doc.select("img[src]")
     * Elements media = doc.select("[src]"); //<script src="..." />, <img src="..." />
     *
     */
    public String thymeleafLink(String htmlLink){
        Objects.requireNonNull(htmlLink);
        String trimmedLink = htmlLink.trim();
        boolean m1 = Pattern.compile("^\\./.*").matcher(trimmedLink).matches();
        boolean m2 = Pattern.compile("^http.*").matcher(trimmedLink).matches();
        boolean m3 = Pattern.compile("^#.*").matcher(trimmedLink).matches();
        String result = "";
        if(m1){
            result = "@{" + trimmedLink.substring(2) + "}";
        }else if (m2 || m3){
            result = "@{" + trimmedLink + "}";
        }else {
            result = "@{/" + trimmedLink + "}";
        }
        return result;
    }
}
