package sa.factory.app1290128.model

import kotlinx.serialization.Serializable

@Serializable
data class CurriculumInteractiveLesson(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class PhysicsCurriculumAuditBatch(
    val requestId: String,
    val items: List<CurriculumInteractiveLesson>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: PhysicsCurriculumAuditBatchStatus = PhysicsCurriculumAuditBatchStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class PhysicsCurriculumAuditBatchStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
