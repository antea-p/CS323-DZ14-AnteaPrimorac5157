package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model

data class Company(
    val id: String,
    val name: String,
    val description: String,
    val turnover: Double,
    val logoUrl: String,
    val type: CompanyType,
    val isVatPayer: Boolean
)

