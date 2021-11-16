package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {
    @Test
    public void news_instantiatesCorrectly_true() {
        News newNews = new News("Fun day","We will be having fun as a family","General");
        assertEquals(true, newNews instanceof News);
    }
    @Test
    public void name_returnsTitle_newName(){
        News news = createNews();
        assertEquals("Fun day", news.getName());
    }
    @Test
    public void description_returnsDescription() {
        News news = createNews();
        assertEquals("We will be having fun as a family", news.getDescription());
    }
    @Test
    public void description_returnsType() {
        News news = createNews();
        assertEquals("General", news.getType());
    }
    @Test
    public void description_setsType() {
        News news = createNews();
        news.setType("specific");
        assertEquals("specific", news.getType());
    }
    @Test
    public void title_setsTitle() {
        News news = createNews();
        news.setName("Salary increment");
        assertEquals("Salary increment", news.getName());
    }



//helper
public News createNews(){
    return new News("Fun day","We will be having fun as a family","General");
}

}