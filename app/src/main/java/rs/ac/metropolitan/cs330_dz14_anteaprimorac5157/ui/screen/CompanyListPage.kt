package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes.CompanyFake
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.theme.CS330DZ14AnteaPrimorac5157Theme

@Composable
fun CompanyListPage(
    companies: List<Company>,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(CompanyType.ENTERTAINMENT) }

    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTab.ordinal,
            modifier = Modifier.testTag("TabRow")
        ) {
            CompanyType.values().forEach { companyType ->
                Tab(
                    selected = selectedTab == companyType,
                    onClick = { selectedTab = companyType },
                    text = { Text(companyType.name) },
                    modifier = Modifier.testTag("Tab_${companyType.name}")
                )
            }
        }

        val filteredCompanies = companies.filter { it.type == selectedTab }
            .sortedWith(
                compareByDescending<Company> { it.isVatPayer }
                    .thenByDescending { it.turnover }
            )

        LazyColumn(modifier = Modifier.testTag("CompanyList")) {
            items(filteredCompanies) { company ->
                CompanyItem(company)
            }
        }
    }
}

@Composable
fun CompanyItem(company: Company) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .testTag("CompanyItem_${company.id}")
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO: kopirati generiranje slike
            // AsyncImage(
            //     model = company.logoUrl,
            //     contentDescription = null,
            //     modifier = Modifier.size(64.dp)
            // )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = company.name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.testTag("CompanyName_${company.id}")
                )
                Text(
                    text = company.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.testTag("CompanyDescription_${company.id}")
                )
                Text(
                    text = "Turnover: ${company.turnover}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.testTag("CompanyTurnover_${company.id}")
                )
                if (company.isVatPayer) {
                    Text(
                        text = "VAT Payer",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.testTag("CompanyVatStatus_${company.id}")
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompanyListPagePreview() {
    CS330DZ14AnteaPrimorac5157Theme {
        Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
            CompanyListPage(companies = CompanyFake.companies)
        }
    }
}