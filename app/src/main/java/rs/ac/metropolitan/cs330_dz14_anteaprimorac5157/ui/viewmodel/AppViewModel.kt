package rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.Company
import rs.ac.metropolitan.cs330_dz14_anteaprimorac5157.data.model.CompanyType

interface AppViewModel {
    val companies: LiveData<List<Company>>
    val isLoading: LiveData<Boolean>
    val internetPermissionGranted: LiveData<Boolean>
    fun loadCompanies()
    fun setTabCompanyType(companyType: CompanyType)
    fun setInternetPermissionGranted(granted: Boolean)
}