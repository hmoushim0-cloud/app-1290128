package sa.factory.app1290128.data

import sa.factory.app1290128.model.PhysicsCurriculumAuditBatch
import sa.factory.app1290128.model.CurriculumInteractiveLesson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface PhysicsCurriculumAuditBatchRepository {
    fun getAvailableCatalog(): Flow<List<CurriculumInteractiveLesson>>
    fun getActiveRequest(): Flow<PhysicsCurriculumAuditBatch?>
    suspend fun submitRequest(request: PhysicsCurriculumAuditBatch): Result<String>
}

class InMemoryPhysicsCurriculumAuditBatchRepository : PhysicsCurriculumAuditBatchRepository {
    private val _catalog = MutableStateFlow<List<CurriculumInteractiveLesson>>(listOf(
        CurriculumInteractiveLesson("1", "حزمة تدقيق أسئلة فيزياء 1 و2", "مراجعة 80 سؤالا تخصصيا مع التحقق من صحة صياغة المفاهيم والخيارات والمراجع", 5000L, "verified"),
        CurriculumInteractiveLesson("2", "حزمة مراجعة الدروس التفاعلية", "تدقيق علمي وتربوي لـ 36 درسا تفاعليا شاملا الشرح المفهومي والأمثلة المحلولة", 9500L, "biotech")
    ))
    private val _active = MutableStateFlow<PhysicsCurriculumAuditBatch?>(null)

    override fun getAvailableCatalog(): Flow<List<CurriculumInteractiveLesson>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<PhysicsCurriculumAuditBatch?> = _active.asStateFlow()

    override suspend fun submitRequest(request: PhysicsCurriculumAuditBatch): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
