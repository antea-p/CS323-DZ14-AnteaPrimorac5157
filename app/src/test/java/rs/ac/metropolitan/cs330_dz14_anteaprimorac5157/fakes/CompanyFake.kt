package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes

import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType

object CompanyFake {
    val companies = listOf(
        // IT
        Company("1", "TechCorp", "Leading IT solutions", 5000000.0, "https://example.com/logo1.png", CompanyType.IT, true),
        Company("2", "CodeMasters", "Software development experts", 4000000.0, "https://example.com/logo2.png", CompanyType.IT, false),
        Company("3", "DataDriven", "Big data solutions", 7000000.0, "https://example.com/logo3.png", CompanyType.IT, true),
        Company("4", "CloudNine", "Cloud computing services", 9000000.0, "https://example.com/logo4.png", CompanyType.IT, false),
        Company("5", "CyberShield", "Cybersecurity experts", 3000000.0, "https://example.com/logo5.png", CompanyType.IT, true),
        Company("6", "SmartInnovate", "Home IoT solutions", 6000000.0, "https://example.com/logo6.png", CompanyType.IT, true),

        // Entertainment Companies
        Company("7", "FunZone", "Entertainment for all ages", 3000000.0, "https://example.com/logo7.png", CompanyType.ENTERTAINMENT, false),
        Company("8", "GameWorld", "Immersive gaming experiences", 2000000.0, "https://example.com/logo8.png", CompanyType.ENTERTAINMENT, true),
        Company("9", "CineMagic", "Movie production company", 8000000.0, "https://example.com/logo9.png", CompanyType.ENTERTAINMENT, true),
        Company("10", "MusicMasters", "Record label and production", 5000000.0, "https://example.com/logo10.png", CompanyType.ENTERTAINMENT, false),
        Company("11", "VirtualReality", "VR entertainment solutions", 4000000.0, "https://example.com/logo11.png", CompanyType.ENTERTAINMENT, true),

        // Trade Companies
        Company("12", "MegaMart", "Your one-stop shop", 8000000.0, "https://example.com/logo12.png", CompanyType.TRADE, true),
        Company("13", "TradeMax", "Global trading solutions", 10000000.0, "https://example.com/logo13.png", CompanyType.TRADE, false),
        Company("14", "FreshFoods", "Organic food distributor", 3000000.0, "https://example.com/logo14.png", CompanyType.TRADE, true),
        Company("15", "FashionForward", "Trendy clothing retailer", 6000000.0, "https://example.com/logo15.png", CompanyType.TRADE, true),
        Company("16", "AutoTraders", "Vehicle import/export", 9000000.0, "https://example.com/logo16.png", CompanyType.TRADE, false),
        Company("17", "GreenEnergy", "Renewable energy products", 4000000.0, "https://example.com/logo17.png", CompanyType.TRADE, true),
        Company("18", "TechGadgets", "Electronics retailer", 5000000.0, "https://example.com/logo18.png", CompanyType.TRADE, false)
    )
}