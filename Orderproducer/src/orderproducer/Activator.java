package orderproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Coffee Producer Bundle started.");
        context.registerService(CoffeeService.class.getName(), new CoffeeServiceImpl(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Coffee Producer Bundle stopped.");
    }
}
