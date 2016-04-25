package com.toppr.web.previousPapers;

import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 03-Mar-2016.
 */
public class PreviousPapers extends PageBase
{
    private static final String domFile = getProperties().get("PREVIOUSPAPERS_DOM_FILE");
    public PreviousPapers()
    {
        super(domFile);
    }

    /**
     * Function for storing previous papers into list
     */
    public void storeAllPreviousPapersTitles()
    {

    }
}
