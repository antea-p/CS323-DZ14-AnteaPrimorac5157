package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.domain.UseCases
import javax.inject.Inject

@HiltViewModel
class AppViewModelImpl @Inject constructor(
    private val useCases: UseCases
) : ViewModel(), AppViewModel {
    private var _currentType: CompanyType? = null
    private var _companies = MutableLiveData<List<Company>>(emptyList())
    private var _isLoading = MutableLiveData<Boolean>(false)
    private var _internetPermissionGranted = MutableLiveData<Boolean>(false)

    override val currentCompanyType: CompanyType?
        get() = _currentType
    override val companies: LiveData<List<Company>>
        get() = _companies
    override val isLoading: LiveData<Boolean>
        get() = _isLoading
    override val internetPermissionGranted: LiveData<Boolean>
        get() = _internetPermissionGranted

    override fun loadCompanies() {
        if (_internetPermissionGranted.value == true) {
            viewModelScope.launch {
                _isLoading.value = true
                val companiesFlow = _currentType?.let { useCases.getCompaniesByType(it) } ?: useCases.getCompanies()
                companiesFlow.collect { companies ->
                    _companies.value = companies
                    _isLoading.value = false
                }
            }
        }
    }

    override fun setTabCompanyType(companyType: CompanyType) {
        _currentType = companyType
        loadCompanies()
    }

    override fun setInternetPermissionGranted(granted: Boolean) {
        _internetPermissionGranted.value = granted
        if (granted) {
            loadCompanies()
        }
    }

}