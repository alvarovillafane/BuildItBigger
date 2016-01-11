/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.alvaro.joke.gcmobile;

import com.alvarovm.provider.JokeProvider;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "gcmobile.joke.alvaro.com",
    ownerName = "gcmobile.joke.alvaro.com",
    packagePath=""
  )
)
public class MyEndpoint {


    @ApiMethod(name = "retrieveJoke")
    public MyBean retrieveJoke(){
        MyBean response = new MyBean();
        JokeProvider jProvider = new JokeProvider();
        response.setData(jProvider.getJoke());
        return response;
    }



}
