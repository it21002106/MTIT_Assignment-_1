package consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import producer.PaymentService;

public class Activator implements BundleActivator {

	private ServiceReference<?> reference;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Start Payment Consumer!");

		reference = context.getServiceReference(PaymentService.class.getName());
		PaymentService paymentService = (PaymentService) context.getService(reference);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Payment Consumer!");
		if (reference != null) {
			context.ungetService(reference);
		}
	}
}