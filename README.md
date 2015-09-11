# klm-dev-case
Just a showcase of a Java project built upon Spring Framework.

Underneath an excerpt of the Development Case requirements and goals.

## Functional requirements

Our mobile applications would like to consume the destination-finder application as a web view. To facilitate all clients, they would like to consume a fully server side rendered html presentation. This also means that the call to the destination-finder service has to be performed server side as well.

Next to duplicating the destination-finder web interface there are some additional requirements. There should be an additional field present where the minimum budget can be set as well. And the results have to be paged. This means that some form of pagination has to be introduced.

The results that are being displayed should be sorted. The user should be able to select whether the results are sorted by destination (alphabetically) or by price (from lowest to highest).

## Technical requirements

Our operations departments demands from us that the destination-finder services endpoint has to be configured in a properties file. Add a properties and set a property key service.df.endpoint with the value of the service url.

For a monitoring tool you are required to count the number of visitors and expose this information as a restful resource.

Introduce an endpoint to retrieve this information from (you may define the url yourself)
Return a JSON response to consumers visiting the endpoint, you may define a model for the result yourself however the minimum amount of information that must be in the result is version of the application, number of consumer invocations and average request processing time.
Summary

* Render the destination-finder web interface server side.
* Introduce pagination.
* Sort results (by either destination or price, user selectable)
* The destination-finder endpoint must be configurable.
* Introduce a new endpoint that exposes a JSON view of application version, number of consumer invocations and average * request processing time.
* The implementation must be done using spring, spring-webmvc and any other components that you might need.

You are allowed to use internet.
Details about the required service can be found here.
Front end code is not the main priority of this assignment, we will be focusing on your backend implementation.
Bonus assignment 01: The destination-finder service unfortunately does not return a description of the origin. Add another call but this time to the airports endpoint to retrieve the description of the airport and add it to your server side rendered result. Again details for this service can be found here.
Bonus assignment 02: Calling two services sequentially is increasing the time it takes to return the result to the client. Now that we are rendering the page server side we want to limit the processing time to a minimum. Implement the solution in such a way that both the call to retrieve the fares as well as the call to retrieve the origin details is invoked in parallel.

## Conclusion

By no means is the goal to get a solution covering all special cases in a 100% robust way. Requiring this would be naive. At least the functions described shall be error free when used correctly, how everything else is handled is subject to creativity and ambition.

