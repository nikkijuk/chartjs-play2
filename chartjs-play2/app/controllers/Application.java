package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.Logger;
import play.libs.Yaml;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dynamic;
import views.html.index;
import app.models.Metric;
import app.models.MetricSet;

public class Application extends Controller {
  
	private static MetricSet expected = null;
	
	static {
		try {
			if (expected == null) {
				// load yaml data
				Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml
						.load("default-data.yml");
				// Insert employees
				expected = (MetricSet) all.get("expectations").get(0);
				Logger.info("Defaults added");

			}
		} catch (Exception e) {
			Logger.error("Defaults couldn't be added "+e.getMessage(), e);
		}
	}
	
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

//    var data = {
//    		labels : ["Eating","Drinking","Sleeping","Designing","Coding","Partying","Running"],
//    		datasets : [
//    			{
//    				fillColor : "rgba(220,220,220,0.5)",
//    				strokeColor : "rgba(220,220,220,1)",
//    				pointColor : "rgba(220,220,220,1)",
//    				pointStrokeColor : "#fff",
//    				data : [65,59,90,81,56,55,40]
//    			},
//    			{
//    				fillColor : "rgba(151,187,205,0.5)",
//    				strokeColor : "rgba(151,187,205,1)",
//    				pointColor : "rgba(151,187,205,1)",
//    				pointStrokeColor : "#fff",
//    				data : [28,48,40,19,96,27,100]
//    			}
//    		]
//    	}
//    
    public static Result dynamic() {
 
    	List<String> labels = new ArrayList <String>();
    	Collections.addAll(labels, "ace", "boom", "crew", "dog", "eon");

    	
    	Map<String, Object> dataset1 = new HashMap<String, Object>();
    	dataset1.put("fillColor", "rgba(220,220,220,0.5)");
      	dataset1.put("strokeColor", "rgba(220,220,220,1)");
      	dataset1.put("pointStrokeColor", "#fff");
      	dataset1.put("data", new int[] {65,59,90,81,56}); 	
    	
      	List<Map<String, Object>> datasets = new ArrayList<Map<String, Object>>();
      	datasets.add(dataset1);
      	
    	Map<String, Object> radarMetrics = new HashMap<String, Object>();
    	radarMetrics.put("labels", labels);
    	radarMetrics.put("datasets", datasets);
    	
        return ok(dynamic.render("Your new application is ready.", radarMetrics));
    }

    public static Result dynamic2() {
    	 
    	// note: to make sure this works without flaws one should use comparator
    	// note: lists which are always ordered by name (or other ordering type) would be printed in right order
    	
    	List<String> labels = new ArrayList <String>();
    	for (Metric  metric: expected.metrics) {
    		labels.add(metric.name);
    	}

    	List<Long> values = new ArrayList <Long>();
    	for (Metric  metric: expected.metrics) {
    		values.add(new Long(metric.value));
    	}

    	
    	Map<String, Object> dataset1 = new HashMap<String, Object>();
    	dataset1.put("fillColor", "rgba(220,220,220,0.5)");
      	dataset1.put("strokeColor", "rgba(220,220,220,1)");
      	dataset1.put("pointStrokeColor", "#fff");
      	dataset1.put("data", values); 	
    	
      	List<Map<String, Object>> datasets = new ArrayList<Map<String, Object>>();
      	datasets.add(dataset1);
      	
    	Map<String, Object> radarMetrics = new HashMap<String, Object>();
    	radarMetrics.put("labels", labels);
    	radarMetrics.put("datasets", datasets);
    	
        return ok(dynamic.render("Your new application is ready.", radarMetrics));
    }

    
}
