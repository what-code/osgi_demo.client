package osgi_demo.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import osgi_demo.Hello;

public class HelloUser implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		HelloUser.context = bundleContext;
		ServiceReference ref = bundleContext.getServiceReference(Hello.class.getName());
	    if (ref != null) {
	        Hello hello = null;
	        try {
	            hello = (Hello) bundleContext.getService(ref);
	            if (hello != null)
	                hello.sayHello();
	            else
	                System.out.println("Service:Hello---object null");
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	        } finally {
	        	bundleContext.ungetService(ref);
	            hello = null;
	        }
	    } else {
	        System.out.println("Service:Hello---not exists");
	    }
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		HelloUser.context = null;
	}

}
