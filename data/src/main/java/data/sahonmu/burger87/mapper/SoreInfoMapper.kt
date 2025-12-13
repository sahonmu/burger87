package data.sahonmu.burger87.mapper

import data.sahonmu.burger87.dto.score.ScoreInfoDto
import domain.sahonmu.burger87.vo.score.ScoreInfo

fun ScoreInfoDto.toDomain(): ScoreInfo = ScoreInfo(
    id = this.id,
    description = this.description,
    score = this.score,
)
