package producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.osgi.framework.ServiceRegistration;


public class Activator implements BundleActivator {

	private ServiceRegistration<?> registration;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Start Producer!");
		
        //Order Confirmation & Pickup
		OrderService orderService = new OrderServiceImpl();
        registration = context.registerService(OrderService.class, orderService, null);
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Producer!");
		registration.unregister();
	}
}
