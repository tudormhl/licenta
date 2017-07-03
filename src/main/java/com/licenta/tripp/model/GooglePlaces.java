package com.licenta.tripp.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Tudor on 6/28/2017.
 */
public class GooglePlaces {

    private static final String AUTOCOMPLETE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json";

    private static final String DETAIL_URL = "https://maps.googleapis.com/maps/api/place/details/json";

    private static final String PHOTO_URL = "https://maps.googleapis.com/maps/api/place/photo";

    private static final String NEARBY_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

    private static final String TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";

    private String apikey;

    private HttpClient client;

    private Gson gson;

    public GooglePlaces( String apikey ) {
        this( HttpClientBuilder.create( ).useSystemProperties( ).build( ), apikey );
    }

    public GooglePlaces( HttpClient client, String apikey ) {
        this.apikey = apikey;

        GsonBuilder gb = new GsonBuilder( );
        gb.setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES );
        this.gson = gb.create( );

        this.client = client;
    }



    private PlacesResult parseSearchResponse( HttpResponse response ) throws IOException {
        return this.gson.fromJson( new InputStreamReader( response.getEntity( ).getContent( ) ), PlacesResult.class );
    }


    public PlacesResult searchText( String query ) {
        return searchText( query, null );
    }

    public PlacesResult searchText( String query, PlacesQueryOptions options ) {

        try {
            URIBuilder url = new URIBuilder( TEXT_SEARCH_URL );
            url.addParameter( "key", this.apikey );
            url.addParameter( "query", query );

            if ( options != null )
                for ( String param : options.params( ).keySet( ) )
                    url.addParameter( param, options.param( param ) );

            HttpGet get = new HttpGet( url.build( ) );
            return this.parseSearchResponse( this.client.execute( get ) );

        } catch( Exception e ) {
            throw new PlacesException( e );
        }

    }
}
