package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.theme.CS330DZ14AnteaPrimorac5157Theme

@Composable
fun CompanyListPage(
    companies: List<Company>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier
        .fillMaxSize()
        .testTag("CompanyList")) {
        items(companies) { company ->
            CompanyItem(company)
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
        CompanyListPage(
            companies = listOf(
                Company(
                    "1",
                    "TechCorp",
                    "Leading IT solutions",
                    5000000.0,
                    "https://example.com/logo1.png",
                    CompanyType.IT,
                    true
                ),
                Company(
                    "2",
                    "CodeMasters",
                    "Software development experts",
                    4000000.0,
                    "https://example.com/logo2.png",
                    CompanyType.IT,
                    false
                ),
                Company(
                    "3",
                    "DataDriven",
                    "Big data solutions",
                    7000000.0,
                    "https://example.com/logo3.png",
                    CompanyType.IT,
                    true
                ),
            )
        )
    }
}
