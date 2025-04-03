/**
 * Vehicle represents a single car listing within the automotive catalog system.
 *
 * This model holds all relevant details that define a vehicle, including:
 * - Make and model (e.g., Toyota Corolla)  
 * - Manufacturing year  
 * - Fuel type (e.g., Gasoline, Electric)  
 * - Image URL for display in the UI  
 * - A short review (description or highlight)  
 * - Price as a formatted string (e.g., "$19,999")  
 *
 * This class is used throughout the app in UI rendering, search, sorting,
 * comparisons, and data persistence.
 */
public class Vehicle {

  private String make, model, fuelType, imageUrl, review, price;
  private int year;

  /**
   * Constructs a new Vehicle with all relevant attributes.
   *
   * @param make      the manufacturer of the vehicle (e.g., "Honda")
   * @param model     the specific model (e.g., "Civic")
   * @param year      the manufacturing year
   * @param fuelType  the type of fuel (e.g., "Gasoline", "Electric")
   * @param imageUrl  a string path or URL pointing to the vehicle image
   * @param review    a brief textual description or user review
   * @param price     the vehicle's price as a string (includes currency formatting)
   */
  public Vehicle(String make, String model, int year, String fuelType, String imageUrl, String review, String price) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.fuelType = fuelType;
    this.imageUrl = imageUrl;
    this.review = review;
    this.price = price;
  }

  /** @return the manufacturer of the vehicle */
  public String getMake() { 
    return make; 
  }

  /** @return the model name of the vehicle */
  public String getModel() { 
    return model; 
  }

  /** @return the manufacturing year */
  public int getYear() { 
    return year; 
  }

  /** @return the fuel type used by the vehicle */
  public String getFuelType() { 
    return fuelType; 
  }

  /** @return the image URL or path used in the UI */
  public String getImageUrl() { 
    return imageUrl; 
  }

  /** @return a short review or highlight of the vehicle */
  public String getReview() { 
    return review; 
  }

  /** @return the price of the vehicle as a formatted string */
  public String getPrice() { 
    return price; 
  }
}