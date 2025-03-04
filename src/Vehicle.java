public class Vehicle {
  private String make, model, fuelType, imageUrl, review;
  private int year;

  public Vehicle(String make, String model, int year, String fuelType, String imageUrl, String review) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.fuelType = fuelType;
    this.imageUrl = imageUrl;
    this.review = review;
  }

  public String getMake() { return make; }
  public String getModel() { return model; }
  public int getYear() { return year; }
  public String getFuelType() { return fuelType; }
  public String getImageUrl() { return imageUrl; }
  public String getReview() { return review; }
}