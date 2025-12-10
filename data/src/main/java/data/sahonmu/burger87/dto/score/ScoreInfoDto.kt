package data.sahonmu.burger87.dto.score

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScoreInfoDto(
    @SerialName("id")val id: Long,
    @SerialName("description")val description: String,
    @SerialName("score")val score: Float,
)