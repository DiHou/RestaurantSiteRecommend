package RestClean;

public class Restaurant {

	  private String zip;
	  private String cuisine;
	  private String price;

	  public enum FastFood {
	    FASTFOOD, PIZZA, BURGER, SANDWICHE, DELIS, STREETVENDOR, FOODTRUCK, SOUP, SALAD, HOTDOGS, FOODSTANDS, FALAFEL, DONUTS, CHEESESTEAKS
	  }

	  public enum Cafe {
	    CAFE, LOUNGES, TEA, TAPAS, COFFEE, SMOOTHIES, JUICE, ICECREAM, DESSERTS, CREPERIES
	  }

	  public enum Bar {
	    BEER, WINE, BAR, PUB, CLUB
	  }

	  public enum Asian {
	    ASIAN, CHINESE, THAI, VIETNAMESE, CANTONESE, TAIWANESE, SZECHUAN, SUSHI, KOREAN, JAPANESE, RAMEN, MALAYSIAN, INDONESIAN, INDIAN, FILIPINO, DIMSUM
	  }
	  
	  public enum Latin {
	    CARIBBEAN, VENEZUELAN, MEXICAN, PUERTORICAN, PERUVIAN, LATIN, DOMINICAN, CUBAN, COLOMBIAN
	  }
	  
	  public enum Mediterranean {
	    MEDITERRANEAN, ITALY, GREEK, SPANISH
	  }
	  
	  public enum American {
	    AMERICAN, FRENCH, AFRAICAN, BARBEQUE, STEAKHOUSES
	  }
	  
	  public enum Halal {
	    MIDDLEEASTERN, HALAL, AFGHAN, PAKISTANI, TURKISH, IRANIAN, PERSIAN
	  }
	  
	  public enum Vegan {
	    VEGETARIAN, VEGAN, GLUTEN
	  }


	  public Restaurant(String s) {
	    String[] data = s.split(",");
	    zip = data[0].replaceAll(" ", "");
	    cuisine = data[1];
	    price = data[2].replaceAll(" ", "");
	  }

	  public Restaurant(String z, String c, String p) {
	    zip = z;
	    cuisine = c;
	    price = p.replaceAll(" ", "");
	  }

	  public String getZip() {
	    return zip;
	  }

	  public String getCuisine() {
	    return cuisine;
	  }

	  public String getPrice() {
	    return price;
	  }

	  public String toString() {
	    return zip + "," + cuisine + "," + price;
	  }
	  
	  public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {   
	    for (E e : enumClass.getEnumConstants()) {
	      if(value.contains(e.name())) { 
	        return true; 
	      }
	    }
	    return false;
	  }

	  public static String classify (String c) {
	    String cui;
	    if (c == null || c.length() == 0){
	    	return "Other";
	    }
	    String str = c.toUpperCase().replaceAll(" ", "");
	    if (isInEnum(str, FastFood.class)) {
	      cui = "FastFood";
	    } else if (isInEnum(str, Cafe.class)){
	      cui = "Cafe";
	    } else if (!str.contains("SUSHI")&&isInEnum(str, Bar.class)) {
	      cui = "Bar";
	    } else if (isInEnum(str, Asian.class)) {
	      cui = "Asian";
	    } else if (isInEnum(str, Vegan.class)) {
	      cui = "Vegan";
	    } else if (isInEnum(str, Latin.class)) {
	      cui = "Latin";
	    } else if (isInEnum(str, Mediterranean.class)) {
	      cui = "Mediterranean";
	    } else if (isInEnum(str, American.class)) {
	      cui = "American";
	    } else if (isInEnum(str, Halal.class)) {
	      cui = "Halal"; 
	    } else {
	      cui = "Other";
	    }
	    return cui;
	  }

	}

