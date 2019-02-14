package com.freeletics.www.common.models;

import org.openqa.selenium.WebElement;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobPostingDto {

    private String title;
    private String jobLocation;
    private String employmentType;
    private WebElement url;

}
