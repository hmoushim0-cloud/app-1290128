package sa.factory.app1290128.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1290128.data.PhysicsCurriculumAuditBatchRepository
import sa.factory.app1290128.model.PhysicsCurriculumAuditBatch
import sa.factory.app1290128.model.CurriculumInteractiveLesson
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PhysicsCurriculumAuditBatchViewModel(
    private val repository: PhysicsCurriculumAuditBatchRepository
) : ViewModel() {
    val catalog: StateFlow<List<CurriculumInteractiveLesson>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<PhysicsCurriculumAuditBatch?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: PhysicsCurriculumAuditBatch) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
