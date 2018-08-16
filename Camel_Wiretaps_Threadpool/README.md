# Camel, Tomcat, Thread usage example

This sample demonstrates how a shared thread pool can be used to minimize the number of threads used for WireTaps.

Without the pool, thread usage jumps by an extra thread for every route that uses a WireTap.  With the pool, the incremental cost of deploying another route is confined to the cost of the route (in this example, a timer thread.)

To visualize:  

- Start Tomcat
- Remove RouteContexts from the Camel route so you have few of them
- Build and Deploy the App
- Monitor thread usage (perhaps by watching Tomcat through a tool like jvisualvm)
- Add another RouteContext, rebuild and redeploy.  Continue to watch Tomcat.
- Note that there is some 'settle down' time required, as initially the application will fire a lot of threads at once.  Given time to settle down, the cost of a new RouteContext (several routes) is no more than the cost of the threads the route requires.  (Without the pool, the cost is also +1 for every WireTap in use.)
