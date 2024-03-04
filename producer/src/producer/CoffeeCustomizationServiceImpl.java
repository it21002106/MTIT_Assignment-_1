package producer;

public class CoffeeCustomizationServiceImpl implements CoffeeCustomizationService {
    public String customizeCoffee(String orderNumber, int customizationOption) {
        String coffeeType = identifyCoffeeType(orderNumber);
        String customizedCoffee = customize(coffeeType, customizationOption);
        return customizedCoffee;
    }
    
//    public String getCustomizationOptions(String coffeeType) {
//        switch (coffeeType) {
//            case "Espresso":
//                return "Available customization options for * Espresso -> 1.Single shot, 2.Double shot, 3.Ristretto, 4.Lungo, 5.Macchiato, 6.Americano *";
//            case "Latte":
//                return "Available customization options for * Latte -> 1.Milk type, 2.Milk texture, 3.Flavorings, 4.Sweeteners *";
//            case "Cappuccino":
//                return "Available customization options for * Cappuccino -> 1.Milk type, 2.Milk texture, 3.Flavorings, 4.Sweeteners, 5.Toppings *";
//            default:
//                return "Unknown Coffee Type";
//        }
//    }
    
    public String getCustomizationOptions(String coffeeType) {
        switch (coffeeType) {
            case "Espresso":
                return "Available customization options for Espresso:\n" +
                       "1. Single shot\n" +
                       "2. Double shot\n" +
                       "3. Ristretto\n" +
                       "4. Lungo\n" +
                       "5. Macchiato\n" +
                       "6. Americano";
            case "Latte":
                return "Available customization options for Latte:\n" +
                       "1. Milk type\n" +
                       "2. Milk texture\n" +
                       "3. Flavorings\n" +
                       "4. Sweeteners";
            case "Cappuccino":
                return "Available customization options for Cappuccino:\n" +
                       "1. Milk type\n" +
                       "2. Milk texture\n" +
                       "3. Flavorings\n" +
                       "4. Sweeteners\n" +
                       "5. Toppings";
            default:
                return "Unknown Coffee Type";
        }
    }

    private String identifyCoffeeType(String orderNumber) {
        if (orderNumber.startsWith("E")) {
            return "Espresso";
        } else if (orderNumber.startsWith("L")) {
            return "Latte";
        } else if (orderNumber.startsWith("C")) {
            return "Cappuccino";
        } else {
            return "Unknown Order";
        }
    }

    private String customize(String coffeeType, int customizationOption) {
        switch (coffeeType) {
            case "Espresso":
                return customizeEspresso(customizationOption);
            case "Latte":
                return customizeLatte(customizationOption);
            case "Cappuccino":
                return customizeCappuccino(customizationOption);
            default:
                return "Unknown Order";
        }
    }

    private String customizeEspresso(int option) {
        switch (option) {
            case 1:
                return "Single shot";
            case 2:
                return "Double shot";
            case 3:
                return "Ristretto";
            case 4:
                return "Lungo";
            case 5:
                return "Macchiato";
            case 6:
                return "Americano";
            default:
                return "Unknown Option";
        }
    }

    private String customizeLatte(int option) {
        switch (option) {
        	case 1:
            	return "Milk type";
        	case 2:
            	return "Milk texture";
        	case 3:
            	return "Flavorings";
        	case 4:
            	return "Sweeteners";

            default:
                return "Unknown Option";
        }
    }

    private String customizeCappuccino(int option) {
        switch (option) {
    		case 1:
        		return "Milk type";
    		case 2:
        		return "Milk texture";
    		case 3:
        		return "Flavorings";
    		case 4:
        		return "Sweeteners";
    		case 5:
    			return "Toppings";
            default:
                return "Unknown Option";
        }
    }
}