package com.tci.rest.webservices.restfullwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.tci.rest.webservices.restfullwebservices.beans.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering")
    //public SomeBean retrieveSomeBean(){

    public MappingJacksonValue retrieveSomeBean(){

        SomeBean someBean = new SomeBean ("value1","value2","value3");

        /*Dinamico*/
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);

        mapping.setFilters(filters);
        return mapping;

        //return new SomeBean("value1","value2","value3");
    }

    @GetMapping(path = "/filtering-list")
    public MappingJacksonValue retrieveListSomeBean(){
        List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2","value3"),
                new SomeBean("value11","value22","value33") );

        /*Dinamico*/
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(list);

        mapping.setFilters(filters);

        return mapping;
    }
}
