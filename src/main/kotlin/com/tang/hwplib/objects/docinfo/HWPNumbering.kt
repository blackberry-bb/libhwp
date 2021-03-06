package com.tang.hwplib.objects.docinfo
import com.tang.hwplib.objects.etc.NUMBERING
import com.tang.hwplib.objects.docinfo.numbering.HWPExtendNumbering
import com.tang.hwplib.objects.docinfo.numbering.HWPLevelNumbering
import com.tang.hwplib.util.extension.contentEquals

/**
 * 문단 번호를 나타내는 객체
 * Tag ID: HWPTAG_NUMBERING [NUMBERING]
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [levelNumberingList] [ArrayList], 수준(1-7)에 해당하는 숫자 또는 문자 또는 기호를 표시 (WCHAR array - unsigned 4 bytes)
 * @property [startNumber] [Int], 시작 번호 (UINT16 - unsigned 2 bytes)
 * @property [startNumberForLevel] [LongArray], 수준별 시작번호 [>=5.0.2.5] (UINT - unsigned 4 bytes)
 * @property [extendLevelNumberingList] [ArrayList], 수준 (8-10) 번호문단 머리의 형식 제어한다.
 * @property [extendStartNumberForLevel] [LongArray] 확장 수준별 시작번호 [>=5.1.0.0] (UINT - unsigned 4 bytes)
 */
class HWPNumbering : HWPDocInfoElement() {
    var levelNumberingList: ArrayList<HWPLevelNumbering> = Unit.run {
        val temp: ArrayList<HWPLevelNumbering> = ArrayList()
        for (index in 0 until 7) HWPLevelNumbering().let { temp.add(it) }
        temp
    }
    var startNumber: Int = 0
    var startNumberForLevel: LongArray = LongArray(7)
    var extendLevelNumberingList: ArrayList<HWPExtendNumbering> = Unit.run {
        val temp: ArrayList<HWPExtendNumbering> = ArrayList()
        for (index in 0 until 3) HWPExtendNumbering().let { temp.add(it) }
        temp
    }
    var extendStartNumberForLevel: LongArray = LongArray(3)

    /**
     * 수준 번호를 반환하는 함수
     *
     * @throws [Exception] [level]이 1-7사이에 없을 때
     * @param [level] [Int], 가져올 데이터 인덱스
     * @return [HWPLevelNumbering], 수준 번호 반환
     */
    fun getLevelNumbering(level: Int) : HWPLevelNumbering {
        if (level in 1..7)
            return levelNumberingList[level - 1]
        else
            throw Exception("invalid level: $level")
    }

    /**
     * 수준별 시작 번호를 반환하는 함수
     *
     * @throws [Exception] [level]이 1-7사이에 없을 때
     * @param [level] [Int], 가져올 데이터 인덱스
     * @return [Long], 시작 번호 반환
     */
    fun getStartNumberForLevel(level: Int) : Long {
        if (level in 1..7)
            return startNumberForLevel[level - 1]
        else
            throw Exception("invalid level: $level")
    }

    /**
     * 수준별 시작 번호를 설정하는 함수
     *
     * @throws [Exception] [level]이 1-7사이에 없을 때
     * @param [startNumber] [Long], 시작 번호를 가진 데이터
     * @param [level] [Int], 추가할 데이터 인덱스
     */
    fun setStartNumberForLevel(startNumber: Long, level: Int) {
        if (level in 1..7)
            startNumberForLevel[level - 1] = startNumber
        else
            throw Exception("invalid level: $level")
    }

    /**
     * 확장 번호 형식을 반환하는 함수
     *
     * @throws [Exception] [level]이 8-10사이에 없을 때
     * @param [level] [Int], 가져올 데이터 인덱스
     *
     * @return [HWPExtendNumbering] 확장 수준 번호 반환
     */
    fun getExtendStartLevelNumbering(level: Int): HWPExtendNumbering {
        if (level in 8..10)
            return extendLevelNumberingList[level - 8]
        else throw Exception("invalid level: $level")
    }

    /**
     * 확장 수준별 번호를 반환하는 함수
     *
     * @throws [Exception], [level]이 8-10사이에 없을 때
     * @param [level] [Int], 가져올 데이터 인덱스
     *
     * @return [Long], 반환된 확장 수준별 번호
     */
    fun getExtendStartNumberForLevel(level: Int): Long {
        if (level in 8..10)
            return extendStartNumberForLevel[level - 8]
        else throw Exception("invalid level: $level")
    }

    /**
     * 확장 수준별 번호를 설정하는 함수
     *
     * @throws [Exception] [level]이 8-10사이에 없을 때
     * @param [level] [Int], 접근할 데이터 인덱스
     * @param [extendStartNumber] [Long], 확장 수준별 번호값을 가진 데이터
     */
    fun setExtendStartNumberForLevel(extendStartNumber: Long, level: Int) {
        if (level in 8..10)
            extendStartNumberForLevel[level - 8] = extendStartNumber
        else throw Exception("invalid level: $level")
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPNumbering] 복사된 객체 반환
     */
    override fun copy() : HWPNumbering = HWPNumbering().also {
        for ((index, levelNumbering)in this.levelNumberingList.withIndex())
            it.levelNumberingList[index] = levelNumbering.copy()
        it.startNumber = this.startNumber
        it.startNumberForLevel = this.startNumberForLevel.copyOf()
        for ((index, extendLevelNumbering) in this.extendLevelNumberingList.withIndex())
            it.extendLevelNumberingList[index] = extendLevelNumbering.copy()
        it.extendStartNumberForLevel = this.extendStartNumberForLevel.copyOf()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPLevelNumbering] 생성된 객체 반환
         */
        fun build(levelNumberingGenerator: () -> ArrayList<HWPLevelNumbering> = { createEmptyLevelNumberingList() },
                  startNumber: Int = 0,
                  startNumberForLevel: LongArray = LongArray(7),
                  extendLevelNumberingGenerator: () -> ArrayList<HWPExtendNumbering> = { createEmptyExtendLevelNumberingList() },
                  extendStartNumberForLevel: LongArray = LongArray(3))
                : HWPNumbering = HWPNumbering().apply {
            this.levelNumberingList = levelNumberingGenerator()
            this.startNumber = startNumber
            this.startNumberForLevel = startNumberForLevel
            this.extendLevelNumberingList = extendLevelNumberingGenerator()
            this.extendStartNumberForLevel = extendStartNumberForLevel
        }

        private fun createEmptyLevelNumberingList(): ArrayList<HWPLevelNumbering> {
            val temp: ArrayList<HWPLevelNumbering> = ArrayList()
            for (index in 0 until 7) HWPLevelNumbering.build().let { temp.add(it) }
            return temp
        }

        private fun createEmptyExtendLevelNumberingList(): ArrayList<HWPExtendNumbering> {
            val temp: ArrayList<HWPExtendNumbering> = ArrayList()
            for (index in 0 until 3) HWPExtendNumbering.build().let { temp.add(it) }
            return temp
        }
    }

    override fun equals(other: Any?): Boolean = (other as HWPNumbering).let {
        return levelNumberingList contentEquals it.levelNumberingList
                && startNumber == it.startNumber
                && startNumberForLevel contentEquals it.startNumberForLevel
                && extendLevelNumberingList contentEquals it.extendLevelNumberingList
                && extendStartNumberForLevel contentEquals it.extendStartNumberForLevel
    }
}