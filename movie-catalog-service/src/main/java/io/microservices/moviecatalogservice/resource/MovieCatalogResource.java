package io.microservices.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.microservices.moviecatalogservice.model.CatalogItem;
import io.microservices.moviecatalogservice.model.Movie;
import io.microservices.moviecatalogservice.model.Rating;
import io.microservices.moviecatalogservice.model.UserRating;
import io.microservices.moviecatalogservice.repsitory.MovieCatalogRepository;
import io.microservices.moviecatalogservice.services.MovieInfo;
import io.microservices.moviecatalogservice.services.UserRatingsInfo;



@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingsInfo userRatingsInfo;
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getMovieCatalog(@PathVariable("userId") String userId){
		
		UserRating userRating = userRatingsInfo.getUserRating(userId);
		return userRating.getUserRating().stream().map(rating ->  movieInfo.getcatalogueItem(rating))
				.collect(Collectors.toList());
				 
	}



	
	

}
