package producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration registration;

    @Override
    public void start(BundleContext context) throws Exception {
        registration = context.registerService(CoffeeCustomizationService.class.getName(), new CoffeeCustomizationServiceImpl(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        registration.unregister();
    }
}