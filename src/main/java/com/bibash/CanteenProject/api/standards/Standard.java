package com.bibash.CanteenProject.api.standards;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="standard")
public class Standard {

    @Column(name = "Name")
    private String standardName;

    @Column(name = "standard_code")
    private String standardCode;



}
