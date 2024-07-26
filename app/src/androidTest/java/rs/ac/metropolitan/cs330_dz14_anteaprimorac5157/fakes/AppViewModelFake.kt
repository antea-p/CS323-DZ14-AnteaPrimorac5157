package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.fakes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes.CompanyFake
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModel

class AppViewModelFake : AppViewModel {
    private val _companies = MutableLiveData<List<Company>>()
    override val companies: LiveData<List<Company>> = _companies

    private val _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> = _isLoading

    private val _internetPermissionGranted = MutableLiveData<Boolean>()
    override val internetPermissionGranted: LiveData<Boolean> = _internetPermissionGranted

    private var currentType: CompanyType? = null

    override fun loadCompanies() {
        _isLoading.value = true
        _companies.value = CompanyFake.companies
        _isLoading.value = false
    }

    override fun setTabCompanyType(companyType: CompanyType) {
        currentType = companyType
        _companies.value = CompanyFake.companies.filter { it.type == companyType }
    }

    override fun setInternetPermissionGranted(granted: Boolean) {
        _internetPermissionGranted.value = granted
    }
}