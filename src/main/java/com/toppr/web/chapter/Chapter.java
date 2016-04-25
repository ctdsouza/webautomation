package com.toppr.web.chapter;

import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 01-Mar-2016.
 */
public class Chapter extends PageBase
{
    private static final String domFile = getProperties().get("CHAPTER_DOM_FILE");
    public Chapter()
    {
        super(domFile);
    }

    /**
     * Function for clicking first goal from chapter
     */
    public void clickFirstGoal()
    {
        Mapper.find(domFile, "firstGoalContineButton").click();
    }
}
