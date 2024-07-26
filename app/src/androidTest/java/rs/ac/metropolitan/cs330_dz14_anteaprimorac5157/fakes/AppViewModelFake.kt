package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.fakes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.fakes.CompanyFake
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel.AppViewModel

class AppViewModelFake : AppViewModel {
    // MutableStateFlow je laksi za testirati od LiveData
    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    override val companies: LiveData<List<Company>> = _companies.asLiveData()

    private val _isLoading = MutableStateFlow(false)
    override val isLoading: LiveData<Boolean> = _isLoading.asLiveData()

    private val _internetPermissionGranted = MutableStateFlow(false)
    override val internetPermissionGranted: LiveData<Boolean> =
        _internetPermissionGranted.asLiveData()

    private var _currentCompanyType: CompanyType? = null
    override val currentCompanyType: CompanyType?
        get() = _currentCompanyType

    override fun loadCompanies() {
        _isLoading.value = true
        _companies.value = CompanyFake.companies
        _isLoading.value = false
    }

    override fun setTabCompanyType(companyType: CompanyType) {
        _currentCompanyType = companyType
        _companies.value = CompanyFake.companies.filter { it.type == companyType }
    }

    override fun setInternetPermissionGranted(granted: Boolean) {
        Log.d("AppViewModelFake", "GRANTING ACCESS...")
        _internetPermissionGranted.value = granted
    }
}