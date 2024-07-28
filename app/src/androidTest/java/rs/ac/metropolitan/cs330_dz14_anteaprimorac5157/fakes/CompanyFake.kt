package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.fakes

import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.Common

object CompanyFake {
    val companies = listOf(
        Company("1", "TechCorp", "Leading IT solutions", 5000000.0, Common.generateAvatarImage("TechCorp").toString(), CompanyType.IT, true),
        Company("2", "CodeMasters", "Software development experts", 4000000.0, Common.generateAvatarImage("CodeMasters").toString(), CompanyType.IT, false),
        Company("3", "DataDriven", "Big data solutions", 7000000.0, Common.generateAvatarImage("DataDriven").toString(), CompanyType.IT, true),
        Company("4", "CloudNine", "Cloud computing services", 9000000.0, Common.generateAvatarImage("CloudNine").toString(), CompanyType.IT, false),
        Company("5", "CyberShield", "Cybersecurity experts", 3000000.0, Common.generateAvatarImage("CyberShield").toString(), CompanyType.IT, true),
        Company("6", "SmartInnovate", "Home IoT solutions", 6000000.0, Common.generateAvatarImage("SmartInnovate").toString(), CompanyType.IT, true),

        Company("7", "FunZone", "Entertainment for all ages", 3000000.0, Common.generateAvatarImage("FunZone").toString(), CompanyType.ENTERTAINMENT, false),
        Company("8", "GameWorld", "Immersive gaming experiences", 2000000.0, Common.generateAvatarImage("GameWorld").toString(), CompanyType.ENTERTAINMENT, true),
        Company("9", "CineMagic", "Movie production company", 8000000.0, Common.generateAvatarImage("CineMagic").toString(), CompanyType.ENTERTAINMENT, true),
        Company("10", "MusicMasters", "Record label and production", 5000000.0, Common.generateAvatarImage("MusicMasters").toString(), CompanyType.ENTERTAINMENT, false),
        Company("11", "VirtualReality", "VR entertainment solutions", 4000000.0, Common.generateAvatarImage("VirtualReality").toString(), CompanyType.ENTERTAINMENT, true),

        Company("12", "MegaMart", "Your one-stop shop", 8000000.0, Common.generateAvatarImage("MegaMart").toString(), CompanyType.TRADE, true),
        Company("13", "TradeMax", "Global trading solutions", 10000000.0, Common.generateAvatarImage("TradeMax").toString(), CompanyType.TRADE, false),
        Company("14", "FreshFoods", "Organic food distributor", 3000000.0, Common.generateAvatarImage("FreshFoods").toString(), CompanyType.TRADE, true),
        Company("15", "FashionForward", "Trendy clothing retailer", 6000000.0, Common.generateAvatarImage("FashionForward").toString(), CompanyType.TRADE, true),
        Company("16", "AutoTraders", "Vehicle import/export", 9000000.0, Common.generateAvatarImage("AutoTraders").toString(), CompanyType.TRADE, false),
        Company("17", "GreenEnergy", "Renewable energy products", 4000000.0, Common.generateAvatarImage("GreenEnergy").toString(), CompanyType.TRADE, true),
        Company("18", "TechGadgets", "Electronics retailer", 5000000.0, Common.generateAvatarImage("TechGadgets").toString(), CompanyType.TRADE, false)
    )
}
