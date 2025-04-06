import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VehicleTest {

    @Test
    public void testToyotaCorolla() {
        Vehicle car = new Vehicle("Toyota", "Corolla", 2022, "Gasoline", "images/corolla.jpg", "Reliable and fuel-efficient. A great choice!", "$24,450");
        assertEquals("Toyota", car.getMake());
        assertEquals("Corolla", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/corolla.jpg", car.getImageUrl());
        assertEquals("Reliable and fuel-efficient. A great choice!", car.getReview());
        assertEquals("$24,450", car.getPrice());
    }

    @Test
    public void testTeslaModel3() {
        Vehicle car = new Vehicle("Tesla", "Model 3", 2023, "Electric", "images/model3.jpg", "A fantastic EV with cutting-edge technology.", "$63,790");
        assertEquals("Tesla", car.getMake());
        assertEquals("Model 3", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/model3.jpg", car.getImageUrl());
        assertEquals("A fantastic EV with cutting-edge technology.", car.getReview());
        assertEquals("$63,790", car.getPrice());
    }

    @Test
    public void HondaCivic() {
        Vehicle car = new Vehicle("Honda", "Civic", 2021, "Gasoline", "images/civic.jpg", "A durable sedan with excellent mileage and handling.", "$25,750");
        assertEquals("Honda", car.getMake());
        assertEquals("Civic", car.getModel());
        assertEquals(2021, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/civic.jpg", car.getImageUrl());
        assertEquals("A durable sedan with excellent mileage and handling.", car.getReview());
        assertEquals("$25,750", car.getPrice());
    }

    @Test
    public void testFordMustang() {
        Vehicle car = new Vehicle("Ford", "Mustang", 2022, "Gasoline", "images/mustang.jpg", "An American muscle car icon with a powerful engine.", "$35,900");
        assertEquals("Ford", car.getMake());
        assertEquals("Mustang", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/mustang.jpg", car.getImageUrl());
        assertEquals("An American muscle car icon with a powerful engine.", car.getReview());
        assertEquals("$35,900", car.getPrice());
    }

    @Test
    public void testBMWM3() {
        Vehicle car = new Vehicle("BMW", "M3", 2023, "Gasoline", "images/m3.jpg", "A luxury sports sedan with high performance.", "$115,910");
        assertEquals("BMW", car.getMake());
        assertEquals("M3", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/m3.jpg", car.getImageUrl());
        assertEquals("A luxury sports sedan with high performance.", car.getReview());
        assertEquals("$115,910", car.getPrice());
    }

    @Test
    public void testMercedesCClass() {
        Vehicle car = new Vehicle("Mercedes", "C-Class", 2022, "Hybrid", "images/cclass.jpg", "A stylish luxury sedan with cutting-edge features.", "$66,350");
        assertEquals("Mercedes", car.getMake());
        assertEquals("C-Class", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/cclass.jpg", car.getImageUrl());
        assertEquals("A stylish luxury sedan with cutting-edge features.", car.getReview());
        assertEquals("$66,350", car.getPrice());
    }

    @Test
    public void testChevroletCamaro() {
        Vehicle car = new Vehicle("Chevrolet", "Camaro", 2021, "Gasoline", "images/camaro.jpg", "A bold design with aggressive performance.", "$48,844");
        assertEquals("Chevrolet", car.getMake());
        assertEquals("Camaro", car.getModel());
        assertEquals(2021, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/camaro.jpg", car.getImageUrl());
        assertEquals("A bold design with aggressive performance.", car.getReview());
        assertEquals("$48,844", car.getPrice());
    }

    @Test
    public void testNissanLeaf() {
        Vehicle car = new Vehicle("Nissan", "Leaf", 2023, "Electric", "images/leaf.jpg", "An affordable and practical electric vehicle.", "$43,27hyu0");
        assertEquals("Nissan", car.getMake());
        assertEquals("Leaf", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/leaf.jpg", car.getImageUrl());
        assertEquals("An affordable and practical electric vehicle.", car.getReview());
        assertEquals("$43,27hyu0", car.getPrice());
    }

    @Test
    public void testHyundaiElantra() {
        Vehicle car = new Vehicle("Hyundai", "Elantra", 2022, "Hybrid", "images/elantra.jpg", "A compact sedan with modern features and hybrid efficiency.", "$22,099");
        assertEquals("Hyundai", car.getMake());
        assertEquals("Elantra", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/elantra.jpg", car.getImageUrl());
        assertEquals("A compact sedan with modern features and hybrid efficiency.", car.getReview());
        assertEquals("$22,099", car.getPrice());
    }

    @Test
    public void testVolkswagenGolfGTI() {
        Vehicle car = new Vehicle("Volkswagen", "Golf GTI", 2021, "Gasoline", "images/golfgti.jpg", "A sporty hatchback with a fun driving experience.", "$36,595");
        assertEquals("Volkswagen", car.getMake());
        assertEquals("Golf GTI", car.getModel());
        assertEquals(2021, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/golfgti.jpg", car.getImageUrl());
        assertEquals("A sporty hatchback with a fun driving experience.", car.getReview());
        assertEquals("$36,595", car.getPrice());
    }

    @Test
    public void testAudiA4() {
        Vehicle car = new Vehicle("Audi", "A4", 2022, "Gasoline", "images/a4.jpg", "A refined European sedan with advanced features.", "$47,200");
        assertEquals("Audi", car.getMake());
        assertEquals("A4", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/a4.jpg", car.getImageUrl());
        assertEquals("A refined European sedan with advanced features.", car.getReview());
        assertEquals("$47,200", car.getPrice());
    }

    @Test
    public void testKiaEV6() {
        Vehicle car = new Vehicle("Kia", "EV6", 2023, "Electric", "images/ev6.jpg", "Stylish electric crossover with impressive range.", "$64,995");
        assertEquals("Kia", car.getMake());
        assertEquals("EV6", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/ev6.jpg", car.getImageUrl());
        assertEquals("Stylish electric crossover with impressive range.", car.getReview());
        assertEquals("$64,995", car.getPrice());
    }

    @Test
    public void testSubaruOutback() {
        Vehicle car = new Vehicle("Subaru", "Outback", 2022, "Gasoline", "images/outback.jpg", "Great for adventurous drivers with AWD and comfort.", "$39,595");
        assertEquals("Subaru", car.getMake());
        assertEquals("Outback", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/outback.jpg", car.getImageUrl());
        assertEquals("Great for adventurous drivers with AWD and comfort.", car.getReview());
        assertEquals("$39,595", car.getPrice());
    }

    @Test
    public void testMazdaCX5() {
        Vehicle car = new Vehicle("Mazda", "CX-5", 2023, "Gasoline", "images/cx5.jpg", "Sporty crossover with excellent handling and interior quality.", "$43,250");
        assertEquals("Mazda", car.getMake());
        assertEquals("CX-5", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/cx5.jpg", car.getImageUrl());
        assertEquals("Sporty crossover with excellent handling and interior quality.", car.getReview());
        assertEquals("$43,250", car.getPrice());
    }

    @Test
    public void testVolvoXC60() {
        Vehicle car = new Vehicle("Volvo", "XC60", 2023, "Hybrid", "images/xc60.jpg", "Safe and luxurious SUV with hybrid efficiency.", "$61,600");
        assertEquals("Volvo", car.getMake());
        assertEquals("XC60", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/xc60.jpg", car.getImageUrl());
        assertEquals("Safe and luxurious SUV with hybrid efficiency.", car.getReview());
        assertEquals("$61,600", car.getPrice());
    }

    @Test
    public void testLexusRX350() {
        Vehicle car = new Vehicle("Lexus", "RX 350", 2022, "Hybrid", "images/rx350.jpg", "A smooth ride with reliable hybrid performance.", "$60,500");
        assertEquals("Lexus", car.getMake());
        assertEquals("RX 350", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/rx350.jpg", car.getImageUrl());
        assertEquals("A smooth ride with reliable hybrid performance.", car.getReview());
        assertEquals("$60,500", car.getPrice());
    }

    @Test
    public void testJeepWrangler() {
        Vehicle car = new Vehicle("Jeep", "Wrangler", 2023, "Gasoline", "images/wrangler.jpg", "An off-road beast with iconic rugged styling.", "$56,095");
        assertEquals("Jeep", car.getMake());
        assertEquals("Wrangler", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/wrangler.jpg", car.getImageUrl());
        assertEquals("An off-road beast with iconic rugged styling.", car.getReview());
        assertEquals("$56,095", car.getPrice());
    }

    @Test
    public void testPorscheTaycan() {
        Vehicle car = new Vehicle("Porsche", "Taycan", 2023, "Electric", "images/taycan.jpg", "Electric performance car with thrilling speed.", "$154,700");
        assertEquals("Porsche", car.getMake());
        assertEquals("Taycan", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/taycan.jpg", car.getImageUrl());
        assertEquals("Electric performance car with thrilling speed.", car.getReview());
        assertEquals("$154,700", car.getPrice());
    }

    @Test
    public void testGenesisG80() {
        Vehicle car = new Vehicle("Genesis", "G80", 2022, "Gasoline", "images/g80.jpg", "Luxury and value combined in a premium sedan.", "$73,500");
        assertEquals("Genesis", car.getMake());
        assertEquals("G80", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/g80.jpg", car.getImageUrl());
        assertEquals("Luxury and value combined in a premium sedan.", car.getReview());
        assertEquals("$73,500", car.getPrice());
    }

    @Test
    public void testAcuraIntegra() {
        Vehicle car = new Vehicle("Acura", "Integra", 2023, "Gasoline", "images/integra.jpg", "A revived classic with sporty appeal.", "$42,550");
        assertEquals("Acura", car.getMake());
        assertEquals("Integra", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/integra.jpg", car.getImageUrl());
        assertEquals("A revived classic with sporty appeal.", car.getReview());
        assertEquals("$42,550", car.getPrice());
    }

    @Test
    public void testToyotaPriusPrime() {
        Vehicle car = new Vehicle("Toyota", "Prius Prime", 2022, "Hybrid", "images/priusprime.jpg", "One of the most fuel-efficient hybrids around.", "$35,750");
        assertEquals("Toyota", car.getMake());
        assertEquals("Prius Prime", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/priusprime.jpg", car.getImageUrl());
        assertEquals("One of the most fuel-efficient hybrids around.", car.getReview());
        assertEquals("$35,750", car.getPrice());
    }

    @Test
    public void testFordF150() {
        Vehicle car = new Vehicle("Ford", "F-150 Lightning", 2023, "Electric", "images/f150lightning.jpg", "America’s top truck goes electric with power and utility.", "$69,000");
        assertEquals("Ford", car.getMake());
        assertEquals("F-150 Lightning", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/f150lightning.jpg", car.getImageUrl());
        assertEquals("America’s top truck goes electric with power and utility.", car.getReview());
        assertEquals("$69,000", car.getPrice());
    }

    @Test
    public void testChevroletBoltEUV() {
        Vehicle car = new Vehicle("Chevrolet", "Bolt EUV", 2023, "Electric", "images/bolteuv.jpg", "Compact electric SUV with solid range and value.", "$42,550");
        assertEquals("Chevrolet", car.getMake());
        assertEquals("Bolt EUV", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/bolteuv.jpg", car.getImageUrl());
        assertEquals("Compact electric SUV with solid range and value.", car.getReview());
        assertEquals("$42,550", car.getPrice());
    }

    @Test
    public void testHondaAccord() {
        Vehicle car = new Vehicle("Honda", "Accord Hybrid", 2022, "Hybrid", "images/accordhybrid.jpg", "Blends comfort, efficiency, and classic sedan style.", "$44,090");
        assertEquals("Honda", car.getMake());
        assertEquals("Accord Hybrid", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/accordhybrid.jpg", car.getImageUrl());
        assertEquals("Blends comfort, efficiency, and classic sedan style.", car.getReview());
        assertEquals("$44,090", car.getPrice());
    }

    @Test
    public void testRivianR1T() {
        Vehicle car = new Vehicle("Rivian", "R1T", 2023, "Electric", "images/r1t.jpg", "Futuristic electric truck with off-road capability.", "$78,800");
        assertEquals("Rivian", car.getMake());
        assertEquals("R1T", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/r1t.jpg", car.getImageUrl());
        assertEquals("Futuristic electric truck with off-road capability.", car.getReview());
        assertEquals("$78,800", car.getPrice());
    }
    
    @Test
    public void testToyotaCamry() {
        Vehicle car = new Vehicle("Toyota", "Camry", 2020, "Gasoline", "images/camry.jpg", "Smooth drive and reliable performance.", "$31,550");
        assertEquals("Toyota", car.getMake());
        assertEquals("Camry", car.getModel());
        assertEquals(2020, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/camry.jpg", car.getImageUrl());
        assertEquals("Smooth drive and reliable performance.", car.getReview());
        assertEquals("$31,550", car.getPrice());
    }

    @Test
    public void testTeslaModelY() {
        Vehicle car = new Vehicle("Tesla", "Model Y", 2022, "Electric", "images/modely.jpg", "Spacious electric SUV with autopilot features.", "$87,500");
        assertEquals("Tesla", car.getMake());
        assertEquals("Model Y", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/modely.jpg", car.getImageUrl());
        assertEquals("Spacious electric SUV with autopilot features.", car.getReview());
        assertEquals("$87,500", car.getPrice());
    }

    @Test
    public void testHondaFit() {
        Vehicle car = new Vehicle("Honda", "Fit", 2019, "Gasoline", "images/fit.jpg", "Compact yet versatile hatchback.", "$18,990");
        assertEquals("Honda", car.getMake());
        assertEquals("Fit", car.getModel());
        assertEquals(2019, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/fit.jpg", car.getImageUrl());
        assertEquals("Compact yet versatile hatchback.", car.getReview());
        assertEquals("$18,990", car.getPrice());
    }

    @Test
    public void testFordEscape() {
        Vehicle car = new Vehicle("Ford", "Escape", 2021, "Hybrid", "images/escape.jpg", "A compact SUV with solid fuel efficiency.", "$30,649");
        assertEquals("Ford", car.getMake());
        assertEquals("Escape", car.getModel());
        assertEquals(2021, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/escape.jpg", car.getImageUrl());
        assertEquals("A compact SUV with solid fuel efficiency.", car.getReview());
        assertEquals("$30,649", car.getPrice());
    }

    @Test
    public void testBMWX5() {
        Vehicle car = new Vehicle("BMW", "X5", 2022, "Gasoline", "images/x5.jpg", "Luxury SUV with premium features.", "$94,800");
        assertEquals("BMW", car.getMake());
        assertEquals("X5", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/x5.jpg", car.getImageUrl());
        assertEquals("Luxury SUV with premium features.", car.getReview());
        assertEquals("$94,800", car.getPrice());
    }

    @Test
    public void testMercedesEClass() {
        Vehicle car = new Vehicle("Mercedes", "E-Class", 2023, "Hybrid", "images/eclass.jpg", "Elegant and comfortable executive sedan.", "$95,100");
        assertEquals("Mercedes", car.getMake());
        assertEquals("E-Class", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/eclass.jpg", car.getImageUrl());
        assertEquals("Elegant and comfortable executive sedan.", car.getReview());
        assertEquals("$95,100", car.getPrice());
    }

    @Test
    public void testChevroletSilverado() {
        Vehicle car = new Vehicle("Chevrolet", "Silverado", 2023, "Gasoline", "images/silverado.jpg", "A powerful truck with rugged capabilities.", "$55,200");
        assertEquals("Chevrolet", car.getMake());
        assertEquals("Silverado", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/silverado.jpg", car.getImageUrl());
        assertEquals("A powerful truck with rugged capabilities.", car.getReview());
        assertEquals("$55,200", car.getPrice());
    }

    @Test
    public void testNissanAltima() {
        Vehicle car = new Vehicle("Nissan", "Altima", 2020, "Gasoline", "images/altima.jpg", "Mid-size sedan with a comfy interior.", "$33,100");
        assertEquals("Nissan", car.getMake());
        assertEquals("Altima", car.getModel());
        assertEquals(2020, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/altima.jpg", car.getImageUrl());
        assertEquals("Mid-size sedan with a comfy interior.", car.getReview());
        assertEquals("$33,100", car.getPrice());
    }

    @Test
    public void testHyundaiKona() {
        Vehicle car = new Vehicle("Hyundai", "Kona", 2023, "Electric", "images/kona.jpg", "Stylish EV with good range.", "$32,390");
        assertEquals("Hyundai", car.getMake());
        assertEquals("Kona", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/kona.jpg", car.getImageUrl());
        assertEquals("Stylish EV with good range.", car.getReview());
        assertEquals("$32,390", car.getPrice());
    }

    @Test
    public void testVolkswagenPassat() {
        Vehicle car = new Vehicle("Volkswagen", "Passat", 2021, "Gasoline", "images/passat.jpg", "Smooth ride with lots of cabin space.", "$34,495");
        assertEquals("Volkswagen", car.getMake());
        assertEquals("Passat", car.getModel());
        assertEquals(2021, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/passat.jpg", car.getImageUrl());
        assertEquals("Smooth ride with lots of cabin space.", car.getReview());
        assertEquals("$34,495", car.getPrice());
    }

    @Test
    public void testAudiQ5() {
        Vehicle car = new Vehicle("Audi", "Q5", 2022, "Hybrid", "images/q5.jpg", "Stylish SUV with quattro all-wheel drive.", "$60,450");
        assertEquals("Audi", car.getMake());
        assertEquals("Q5", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/q5.jpg", car.getImageUrl());
        assertEquals("Stylish SUV with quattro all-wheel drive.", car.getReview());
        assertEquals("$60,450", car.getPrice());
    }

    @Test
    public void testKiaTelluride() {
        Vehicle car = new Vehicle("Kia", "Telluride", 2023, "Gasoline", "images/telluride.jpg", "Spacious family SUV with upscale feel.", "$55,195");
        assertEquals("Kia", car.getMake());
        assertEquals("Telluride", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/telluride.jpg", car.getImageUrl());
        assertEquals("Spacious family SUV with upscale feel.", car.getReview());
        assertEquals("$55,195", car.getPrice());
    }

    @Test
    public void testSubaruForester() {
        Vehicle car = new Vehicle("Subaru", "Forester", 2022, "Gasoline", "images/forester.jpg", "Reliable AWD crossover with ample cargo space.", "$33,095");
        assertEquals("Subaru", car.getMake());
        assertEquals("Forester", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/forester.jpg", car.getImageUrl());
        assertEquals("Reliable AWD crossover with ample cargo space.", car.getReview());
        assertEquals("$33,095", car.getPrice());
    }

    @Test
    public void testMazdaMazda3() {
        Vehicle car = new Vehicle("Mazda", "Mazda3", 2021, "Gasoline", "images/mazda3.jpg", "Sporty compact car with premium styling.", "$34,100");
        assertEquals("Mazda", car.getMake());
        assertEquals("Mazda3", car.getModel());
        assertEquals(2021, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/mazda3.jpg", car.getImageUrl());
        assertEquals("Sporty compact car with premium styling.", car.getReview());
        assertEquals("$34,100", car.getPrice());
    }

    @Test
    public void testVolvoXC90() {
        Vehicle car = new Vehicle("Volvo", "XC90", 2023, "Hybrid", "images/xc90.jpg", "Family-oriented SUV with luxurious touches.", "$76,700");
        assertEquals("Volvo", car.getMake());
        assertEquals("XC90", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/xc90.jpg", car.getImageUrl());
        assertEquals("Family-oriented SUV with luxurious touches.", car.getReview());
        assertEquals("$76,700", car.getPrice());
    }

    @Test
    public void testLexusES300() {
        Vehicle car = new Vehicle("Lexus", "ES 300h", 2022, "Hybrid", "images/es300h.jpg", "Comfortable hybrid sedan with elegant design.", "$49,900");
        assertEquals("Lexus", car.getMake());
        assertEquals("ES 300h", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/es300h.jpg", car.getImageUrl());
        assertEquals("Comfortable hybrid sedan with elegant design.", car.getReview());
        assertEquals("$49,900", car.getPrice());
    }

    @Test
    public void testJeepGrandCherokee() {
        Vehicle car = new Vehicle("Jeep", "Grand Cherokee", 2023, "Gasoline", "images/grandcherokee.jpg", "Strong off-road capability and spacious interior.", "$63,545");
        assertEquals("Jeep", car.getMake());
        assertEquals("Grand Cherokee", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/grandcherokee.jpg", car.getImageUrl());
        assertEquals("Strong off-road capability and spacious interior.", car.getReview());
        assertEquals("$63,545", car.getPrice());
    }

    @Test
    public void testPorscheMacan() {
        Vehicle car = new Vehicle("Porsche", "Macan", 2022, "Gasoline", "images/macan.jpg", "Sporty and luxurious compact SUV.", "$70,600");
        assertEquals("Porsche", car.getMake());
        assertEquals("Macan", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/macan.jpg", car.getImageUrl());
        assertEquals("Sporty and luxurious compact SUV.", car.getReview());
        assertEquals("$70,600", car.getPrice());
    }

    @Test
    public void testGenesisGV70() {
        Vehicle car = new Vehicle("Genesis", "GV70", 2023, "Gasoline", "images/gv70.jpg", "Luxury compact SUV with bold styling.", "$61,500");
        assertEquals("Genesis", car.getMake());
        assertEquals("GV70", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/gv70.jpg", car.getImageUrl());
        assertEquals("Luxury compact SUV with bold styling.", car.getReview());
        assertEquals("$61,500", car.getPrice());
    }

    @Test
    public void testAcuraTLX() {
        Vehicle car = new Vehicle("Acura", "TLX", 2022, "Gasoline", "images/tlx.jpg", "Performance sedan with premium features.", "$53,400");
        assertEquals("Acura", car.getMake());
        assertEquals("TLX", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/tlx.jpg", car.getImageUrl());
        assertEquals("Performance sedan with premium features.", car.getReview());
        assertEquals("$53,400", car.getPrice());
    }

    @Test
    public void testRam1500() {
        Vehicle car = new Vehicle("Ram", "1500", 2023, "Gasoline", "images/ram1500.jpg", "Comfortable ride with high towing capacity.", "$54,045");
        assertEquals("Ram", car.getMake());
        assertEquals("1500", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/ram1500.jpg", car.getImageUrl());
        assertEquals("Comfortable ride with high towing capacity.", car.getReview());
        assertEquals("$54,045", car.getPrice());
    }

    @Test
    public void testMiniCooperSE() {
        Vehicle car = new Vehicle("Mini", "Cooper SE", 2021, "Electric", "images/cooperse.jpg", "Fun EV with iconic design.", "$27,790");
        assertEquals("Mini", car.getMake());
        assertEquals("Cooper SE", car.getModel());
        assertEquals(2021, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/cooperse.jpg", car.getImageUrl());
        assertEquals("Fun EV with iconic design.", car.getReview());
        assertEquals("$27,790", car.getPrice());
    }

    @Test
    public void testLucidAir() {
        Vehicle car = new Vehicle("Lucid", "Air", 2023, "Electric", "images/air.jpg", "Luxury electric sedan with impressive range.", "$121,500");
        assertEquals("Lucid", car.getMake());
        assertEquals("Air", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Electric", car.getFuelType());
        assertEquals("images/air.jpg", car.getImageUrl());
        assertEquals("Luxury electric sedan with impressive range.", car.getReview());
        assertEquals("$121,500", car.getPrice());
    }

    @Test
    public void testToyotaGR86() {
        Vehicle car = new Vehicle("Toyota", "GR86", 2023, "Gasoline", "images/gr86.jpg", "Lightweight sports coupe with RWD fun.", "$34,490");
        assertEquals("Toyota", car.getMake());
        assertEquals("GR86", car.getModel());
        assertEquals(2023, car.getYear());
        assertEquals("Gasoline", car.getFuelType());
        assertEquals("images/gr86.jpg", car.getImageUrl());
        assertEquals("Lightweight sports coupe with RWD fun.", car.getReview());
        assertEquals("$34,490", car.getPrice());
    }

    @Test
    public void testFordMaverick() {
        Vehicle car = new Vehicle("Ford", "Maverick", 2022, "Hybrid", "images/maverick.jpg", "Compact pickup with great efficiency.", "$30,350");
        assertEquals("Ford", car.getMake());
        assertEquals("Maverick", car.getModel());
        assertEquals(2022, car.getYear());
        assertEquals("Hybrid", car.getFuelType());
        assertEquals("images/maverick.jpg", car.getImageUrl());
        assertEquals("Compact pickup with great efficiency.", car.getReview());
        assertEquals("$30,350", car.getPrice());
    }
}